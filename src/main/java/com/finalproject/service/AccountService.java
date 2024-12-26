package com.finalproject.service;
import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Administrator;
import com.finalproject.model.Buyer;
import com.finalproject.model.Store;
import com.finalproject.util.JwtTokenUtil;
import com.finalproject.util.SnowflakeIdGenerator;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.finalproject.model.Account;
import com.finalproject.repository.AccountRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//通过注解，标注这是Service层
@Service
public class AccountService {
    //因为在Service层中我们需要调用Dao层
    //所以将Dao层作为一个资源将其加进来
    @Resource
    private AccountRepository accountRepository;
    @Autowired
    private JavaMailSender mailSender;
    private Map<String, String> verificationCodes = new ConcurrentHashMap<>(); // 用于存储验证码
    // 发送验证码
    public Result<String> sendVerificationCode(String email) {
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

        return Result.success(verificationCode);
    }

    // 获取验证码（后期需要改成redis）
    public boolean verifyCode(String email, String code) {
        // 校验验证码
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }

    // 用户注册
    public Result<String> registerUser(AccountDTOs.UserRegisterDTO dto) {
        if(!this.verifyCode(dto.getEmail(), dto.getVerificationCode()))
            return Result.error(400, "验证码错误！");
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
        account.setUserName(dto.getUsername());
        account.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt())); // 加密密码
        account.setEmail(dto.getEmail());
        accountRepository.save(account);
        return Result.success();
    }


    // 用户登录
    public Result<String> login(String identifier, String password) {
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
                return Result.success(jwt);
            }
        }
        return Result.error(404, "用户名或密码错误");
    }






}