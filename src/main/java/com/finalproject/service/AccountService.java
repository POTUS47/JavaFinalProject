package com.finalproject.service;
import com.finalproject.DTO.AccountDTOs;
import com.finalproject.model.Administrator;
import com.finalproject.model.Buyer;
import com.finalproject.model.Store;
import com.finalproject.util.JwtTokenUtil;
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
    public String sendVerificationCode(String email) {
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
            throw new RuntimeException("Failed to send email. Please try again later.", e);
        }

        // 保存验证码到缓存中（可结合 Redis 使用）
        verificationCodes.put(email, verificationCode);

        return verificationCode; // 返回验证码供前端使用
    }

    // 获取验证码（后期需要改成redis）
    public boolean verifyCode(String email, String code) {
        // 校验验证码
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }

    //现在我们来做一下需求分析
    //可能我们需要找学生表中所以学生的信息
    //那么定义的findAll方法返回值应该是一个集合
    public List<Account> findAll() {
        //这里可以直接把studentRepository拉过来用
        //因为Dao层中StudentRepository有继承过来的功能
        return accountRepository.findAll();
    }

    // 用户注册
    public boolean registerUser(AccountDTOs.UserRegisterDTO dto) {
        //String generatedId = IdGeneratorA.generate(); // 调用函数生成 ID
        String generatedId="111111";

        Account account;
        switch (dto.getIdentity().toUpperCase()) {
            case "ADMIN":
                account = new Administrator();
                break;
            case "SELLER":
                account = new Store();
                break;
            case "BUYER":
                account = new Buyer();
                break;
            default:
                throw new IllegalArgumentException("Invalid identity type");
        }

        account.setAccountId(generatedId);
        account.setUserName(dto.getUsername());
        account.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt())); // 加密密码
        account.setEmail(dto.getEmail());

        accountRepository.save(account);
        return true;
    }


    // 用户登录
    public String login(String accountId, String password) {
        Optional<Account> userOptional = accountRepository.findByAccountId(accountId);
        // 要加上用邮箱验证
        if (userOptional.isPresent()) {
            Account user = userOptional.get();
            // 接下来需要调用数据库的方法，通过用户ID查找用户身份
            // 我目前只是写死了用户身份为买家
            if (BCrypt.checkpw(password, user.getPassword())) {
                // 登录成功，生成JWT并返回
                return JwtTokenUtil.generateJWT(user.getUserName(), "buyer");
            }
        }
        return null; // 用户名或密码错误
    }






}