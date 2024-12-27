package com.finalproject.controller;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Store;
import com.finalproject.service.AccountService;
import com.finalproject.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AccountService userService;
    private final StoreService storeService;

    public UserController(AccountService userService,
                          StoreService storeService) {
        this.storeService = storeService;
        this.userService = userService;
    }

    // 发送验证码 (要改要改！！！不可以直接返回前端)
    @PostMapping("/send-code")
    public ResponseEntity<Result<Map<String, String>>> sendVerificationCode(@RequestBody String email) {
        Result<Map<String, String>> response=userService.sendVerificationCode(email);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<Result<Map<String, String>>> register(@RequestBody AccountDTOs.UserRegisterDTO dto) {
        Result<Map<String, String>> response = userService.registerUser(dto);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<Result<Map<String, String>>>
    login(@RequestBody AccountDTOs.LoginDTO loginRequest) {
        Result<Map<String, String>> response= userService.login(loginRequest.getIdentifier(), loginRequest.getPassword());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 修改密码
    @PutMapping("/changePassword")
    public ResponseEntity<Result<Map<String, String>>>
    changePassword(@RequestParam String newPassword,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.changePassword(userId, newPassword);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 修改邮箱
    @PutMapping("/updateEmail")
    public ResponseEntity<Result<Map<String, String>>>
    updateEmail(@RequestParam String newEmail,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.updateEmail(userId, newEmail);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 修改用户名
    @PutMapping("/updateUsername")
    public ResponseEntity<Result<Map<String, String>>>
    updateUsername(@RequestParam String newUsername,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.updateUsername(userId, newUsername);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 修改用户简介
    @PutMapping("/updateDescription")
    public ResponseEntity<Result<Map<String, String>>>
    updateDescription(@RequestParam String newDescription,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.updateDescription(userId, newDescription);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取某用户全部信息接口
    @GetMapping("/info")
    public ResponseEntity<Result<AccountDTOs.UserInfoDTO>> getUserInfo(@RequestParam String userId) {
        Result<AccountDTOs.UserInfoDTO> result = userService.getUserInfo(userId);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取自身信息接口
    @GetMapping("/myInfo")
    public ResponseEntity<Result<AccountDTOs.UserInfoDTO>> getSelfInfo(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<AccountDTOs.UserInfoDTO> result = userService.getUserInfo(userId);
        return ResponseEntity.status(result.getCode()).body(result);
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


    // 根据 account_id 获取 Store 信息
    @GetMapping("/store/{accountId}")
    public ResponseEntity<Optional<Store>> getStoreByAccountId(@PathVariable String accountId) {
        return ResponseEntity.ok(storeService.getStoreByAccountId(accountId));
    }





}
