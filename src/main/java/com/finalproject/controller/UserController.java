package com.finalproject.controller;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
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

    // 发送验证码 (要改要改！！！不可以直接返回前端)
    @PostMapping("/send-code")
    public ResponseEntity<Result<String>> sendVerificationCode(@RequestBody String email) {
        Result<String> response=userService.sendVerificationCode(email);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<Result<String>> register(@RequestBody AccountDTOs.UserRegisterDTO dto) {
        Result<String> response = userService.registerUser(dto);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<Result<String>> login(@RequestBody AccountDTOs.LoginDTO loginRequest) {
        Result<String> response= userService.login(loginRequest.getIdentifier(), loginRequest.getPassword());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 修改密码



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
