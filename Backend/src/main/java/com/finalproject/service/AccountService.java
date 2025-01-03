package com.finalproject.service;
import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import com.finalproject.repository.*;
import com.finalproject.util.JwtTokenUtil;
import com.finalproject.util.SnowflakeIdGenerator;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//通过注解，标注这是Service层
@Service
public class AccountService {

    @Resource
    private AccountRepository accountRepository;
    @Resource
    private StoreRepository storeRepository;
    @Value("${api.base-url}")
    private String baseUrl;

    private final JavaMailSender mailSender;
    private final Map<String, String> verificationCodes; // 用于存储验证码
    private final ImageService imageService;

    public AccountService(JavaMailSender mailSender, ImageService imageService) {
        this.mailSender = mailSender;
        this.imageService = imageService;
        this.verificationCodes = new ConcurrentHashMap<>();
    }

    // 发送验证码
    public Result<Map<String, String>> sendVerificationCode(String email) {
        // 生成 6 位随机验证码
        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1473030672@qq.com");
        message.setTo(email);
        message.setSubject("Your Verification Code");
        message.setText("Your verification code is: " + verificationCode);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            return Result.error(400, "邮件发送失败");
        }
        // 保存验证码到缓存中（可结合 Redis 使用）
        // 注意：若email 在 Map 中已经存在，则 put 会覆盖该键当前的值
        verificationCodes.put(email, verificationCode);

        Map<String, String> data = new HashMap<>();
        data.put("verificationCode",verificationCode);
        return Result.success(data);
    }

    // 获取验证码（后期需要改成redis缓存）
    public boolean verifyCode(String email, String code) {
        // 校验验证码
        System.out.println("VerificationCodes content: " + verificationCodes);///////
        String storedCode = verificationCodes.get("\""+email+"\"");
        if(storedCode==null){
            storedCode = verificationCodes.get(email);
        }
        System.out.println("根据"+email+"提取内容" + storedCode);///////
        return storedCode != null && storedCode.equals(code);
    }

    // 用户注册
    public Result<Map<String, String>> registerUser(AccountDTOs.UserRegisterDTO dto) {
        if (!this.verifyCode(dto.getEmail(), dto.getVerificationCode()))
            return Result.error(400, "验证码错误！");
        Optional<Account> possible_account = accountRepository.findByEmail(dto.getEmail());
        if (possible_account.isPresent()) {
            return Result.error(400, "该邮箱已被使用过！");
        }
        // 生成唯一 ID
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator();
        String generatedId = generator.nextId();
        Account account;
        switch (dto.getIdentity()) {
            case "管理员":
                account = new Administrator();
                account.setType(Account.Type.管理员);
                break;
            case "商家":
                account = new Store();
                account.setType(Account.Type.商家);
                break;
            case "买家":
                account = new Buyer();
                account.setType(Account.Type.买家);
                break;
            default:
                return Result.error(400, "注册类型无效,必须是买家商家管理员");
        }
        account.setAccountId(generatedId);
        account.setUserName(generatedId);
        account.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt())); // 加密密码
        account.setEmail(dto.getEmail());
        accountRepository.save(account);
        Map<String, String> data = new HashMap<>();
        data.put("message", "登录成功！");
        data.put("ID", generatedId);
        return Result.success(data);
    }

    // 用户登录
    public Result<Map<String, String>> login(String identifier, String password) {
        Optional<Account> userOptional = accountRepository.findByAccountId(identifier);
        if (userOptional.isEmpty()) {
            userOptional = accountRepository.findByEmail(identifier);
        }

        if (userOptional.isPresent()) {
            Account user = userOptional.get();
            // 接下来需要调用数据库的方法，通过用户ID查找用户身份
            if (BCrypt.checkpw(password, user.getPassword())) {
                // 登录成功，生成JWT并返回
                String jwt=JwtTokenUtil.generateJWT(user.getAccountId(), user.getType().toString());
                Map<String, String> data = new HashMap<>();
                data.put("JwtToken", jwt);
                data.put("type", user.getType().toString());
                data.put("id", user.getAccountId());
                return Result.success(data);
            }
        }
        return Result.error(404, "用户名或密码错误");
    }

    // 修改密码
    public Result<Map<String, String>> changePassword(String userId, String newPassword) {
        Optional<Account> userOptional= accountRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改密码的账号不存在");
        }
        Account user = userOptional.get();
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        accountRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"密码修改成功！");
        return Result.success(data);
    }

    // 忘记密码
    public Result<Map<String, String>> forgetPassword(String email, String password,String verificationCode) {
        if (!this.verifyCode(email,verificationCode))
            return Result.error(400, "验证码错误！");
        Optional<Account> userOptional= accountRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改密码的账号不存在");
        }
        Account user = userOptional.get();
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        accountRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", "密码修改成功！");
        return Result.success(data);
    }

    // 修改邮箱
    public Result<Map<String, String>> updateEmail(String userId, String newEmail) {
        Optional<Account> userOptional= accountRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改邮箱的账号不存在");
        }
        Account user = userOptional.get();
        user.setEmail(newEmail);
        accountRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"邮箱修改成功！");
        return Result.success(data);
    }

    // 修改用户名
    public Result<Map<String, String>> updateUsername(String userId, String newUsername) {
        Optional<Account> userOptional= accountRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改用户名的账号不存在");
        }
        Account user = userOptional.get();
        user.setUserName(newUsername);
        accountRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"昵称修改成功！");
        return Result.success(data);
    }

    // 修改用户简介
    public Result<Map<String, String>> updateDescription(String userId, String newDescription) {
        Optional<Account> userOptional= accountRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改简介的账号不存在");
        }
        Account user = userOptional.get();
        user.setDescription(newDescription);
        accountRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"简介修改成功！");
        return Result.success(data);
    }

    // 上传用户头像
    public Result<Map<String, String>> updatePhoto(String userId, String photoId){
        Optional<Account> userOptional= accountRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改头像的账号不存在");
        }
        Account user = userOptional.get();
        user.setPhotoId(photoId);
        accountRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"头像修改成功！");
        data.put("photo_id", photoId);
        return Result.success(data);
    }

    // 获取用户全部基本信息
    public Result<AccountDTOs.UserInfoDTO> getUserInfo(String userId) {
        // 查找用户
        Optional<Account> userOptional = accountRepository.findByAccountId(userId);
        // 如果没有找到用户
        if (userOptional.isEmpty()) {
            return Result.error(404, "想获取基本信息的用户不存在");
        }
        // 获取用户信息
        Account user = userOptional.get();
        // 创建 UserInfoDTO 对象
        AccountDTOs.UserInfoDTO userInfo = new AccountDTOs.
                UserInfoDTO(user.getAccountId(), user.getUserName(),
                user.getEmail(),user.getPhotoId(),user.getType().toString());
        // 返回成功结果
        return Result.success(userInfo);
    }

    // 检查用户信息是否存在（JWT用）
    public boolean isUserExists(String userId) {
        Optional<Account> userOptional = accountRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return false;
        }
        return true;
    }

    // 获取用户的头像和简介
    public Result<AccountDTOs.PandDDTO> getPandD(String userId) {
        Optional<Account>temp= accountRepository.findByAccountId(userId);
        if (temp.isEmpty()) {
            return Result.error(404,"该用户不存在");
        }
        Account user = temp.get();
        String avartarId=user.getPhotoId();
        String des=user.getDescription();
        AccountDTOs.PandDDTO re=new AccountDTOs.PandDDTO();
        String url = baseUrl;
        if(avartarId==null){
            String defaultId="1";
            url = url + "/images/" + defaultId;
        }
        else{
            url = url + "/images/" + avartarId;
        }
        re.setDescribtion(des);
        re.setImageUrl(url);
        return Result.success(re);
    }

    // 修改用户的头像和简介
    public Result<String> setPandD(MultipartFile productImages, String description,String userId,String role) throws IOException {
        Optional<Account> userOption=accountRepository.findByAccountId(userId);
        if (userOption.isEmpty()) {
            return Result.error(404,"用户不存在！");
        }
        Account user= userOption.get();
        String oldImageUrl=user.getPhotoId();
        Result<String> result=imageService.saveImage(productImages,"头像");
        if(result.getCode()==200) {
            String picId = result.getData();
            user.setPhotoId(picId);
            user.setDescription(description);
            accountRepository.save(user);
        }

        if(oldImageUrl!=null){
            imageService.deleteImage(oldImageUrl);
        }
        return result;
    }



}