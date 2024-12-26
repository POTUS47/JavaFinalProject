package com.finalproject.controller;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AccountService userService;

    public UserController(AccountService userService) {
        this.userService = userService;
    }

    // 发送验证码(要改要改！！！不可以直接返回前端)
    @PostMapping("/send-code")
    public ResponseEntity<String> sendVerificationCode(@RequestBody String email) {
        String code=userService.sendVerificationCode(email);
        System.out.println("Generated verification code for " + email + ": " + code);
        return ResponseEntity.ok("Verification code sent."+ code);
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTOs.UserRegisterDTO dto) {
        boolean success = userService.registerUser(dto);
        if (success) {
            return ResponseEntity.ok("Registration successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }

    // 用户登录
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AccountDTOs.LoginDTO loginRequest) {
        Map<String, Object> response = new HashMap<>();
        String token = userService.login(loginRequest.getIdentifier(), loginRequest.getPassword());
        if (token != null) {
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("token", token);  // 返回 JWT token
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }
        return response;
    }

    // 示例 访问保护数据
    @PostMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@RequestBody AccountDTOs.LoginDTO dto, Authentication authentication) {
        // 从 Authentication 获取用户信息
        String userId = (String) authentication.getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        // 假设更新逻辑
        // userService.updateUserProfile(userId, fullName, address);

        return ResponseEntity.ok(Map.of(
                "message", "Profile updated successfully.",
                "userId", userId,
                "role", role
        ));
    }





}
