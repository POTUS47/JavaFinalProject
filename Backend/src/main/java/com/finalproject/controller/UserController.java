package com.finalproject.controller;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Product;
import com.finalproject.model.Store;
import com.finalproject.model.Buyer;
import com.finalproject.repository.BuyerRepository;
import com.finalproject.repository.WalletRepository;
import com.finalproject.service.AccountService;
import com.finalproject.service.StoreService;
import com.finalproject.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AccountService userService;
    private final StoreService storeService;
    private final WalletService walletService;

    public UserController(AccountService userService, StoreService storeService,
                          WalletService walletService) {
        this.storeService = storeService;
        this.userService = userService;
        this.walletService = walletService;
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

    // 发送验证码 (要改要改！！！不可以直接返回前端)
    @GetMapping("/send-code/{email}")
    public ResponseEntity<Result<Map<String, String>>> sendVerificationCode(@PathVariable String email) {
        Result<Map<String, String>> response=userService.sendVerificationCode(email);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<Result<Map<String, String>>> register(@RequestBody AccountDTOs.UserRegisterDTO dto) {
        Result<Map<String, String>> response = userService.registerUser(dto);
        if(response.getCode()!=200){
            return ResponseEntity.status(response.getCode()).body(response);
        }
        walletService.createWallet(response.getData().get("ID"));
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
    @PostMapping("/login/changePassword")
    public ResponseEntity<Result<Map<String, String>>>
    changePassword(@RequestParam String newPassword,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.changePassword(userId, newPassword);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 忘记密码（未登录修改密码）
    @PostMapping("/changePassword")
    public ResponseEntity<Result<Map<String, String>>>
    forgetPassword(@RequestBody AccountDTOs.ChangePasswordDTO dto) {
        String password = dto.getPassword();
        String email = dto.getEmail();
        String verificationCode = dto.getVerificationCode();
        Result<Map<String, String>> result = userService.forgetPassword(email,password, verificationCode);
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

    // 修改卖家地址
    @PutMapping("/store/updateAddress")
    public ResponseEntity<Result<Map<String, String>>>
    updateStoreAdress(@RequestParam String newAdress,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.updateStoreAdress(userId, newAdress);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 修改卖家店铺名
    @PutMapping("/store/updateStoreName")
    public ResponseEntity<Result<Map<String, String>>>
    updateStoreName(@RequestParam String newName,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = userService.updateStoreName(userId, newName);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取某用户基本信息接口
    @GetMapping("/info")
    public ResponseEntity<Result<AccountDTOs.UserInfoDTO>> getUserInfo(@RequestParam String userId) {
        Result<AccountDTOs.UserInfoDTO> result = userService.getUserInfo(userId);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取自身基本信息接口（买家）
    @GetMapping("/buyer/myInfo")
    public ResponseEntity<Result<AccountDTOs.BuyerInfoDTO>> getBuyerSelfInfo(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<AccountDTOs.BuyerInfoDTO> result = userService.getBuyerInfo(userId);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取自身基本信息接口（商家）
    @GetMapping("/store/myInfo")
    public ResponseEntity<Result<AccountDTOs.StoreInfoDTO>> getStoreSelfInfo(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<AccountDTOs.StoreInfoDTO> result = userService.getStoreInfo(userId);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取某商家基本信息接口
    @GetMapping("/store/Info/{store_id}")
    public ResponseEntity<Result<AccountDTOs.StoreInfoDTO>> getStoreInfo(@PathVariable String store_id) {
        Result<AccountDTOs.StoreInfoDTO> result = userService.getStoreInfo(store_id);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 根据 account_id 获取 Store 信息
    @GetMapping("/store/{accountId}")
    public ResponseEntity<Optional<Store>> getStoreByAccountId(@PathVariable String accountId) {
        return ResponseEntity.ok(storeService.getStoreByAccountId(accountId));
    }

    // 钱包充值（需要有沙盒逻辑！！！！）
    @PutMapping("/recharge")
    public ResponseEntity<Result<Map<String, String>>>
    recharge(@RequestParam BigDecimal amount, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = walletService.recharge(userId, amount);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 钱包支付
    @PutMapping("/charge")
    public ResponseEntity<Result<Map<String, String>>>
    charge(@RequestParam BigDecimal amount, String userId) {
        Result<Map<String, String>> result = walletService.subtract(userId, amount);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 查看余额
    @GetMapping("/balance")
    public ResponseEntity<Result<Map<String, String>>>
    getBalance(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String, String>> result = walletService.checkBalance(userId);
        return ResponseEntity.status(result.getCode()).body(result);
    }

    // 获取店铺信息
    @GetMapping("/getStore/{storeId}")
    public Optional<Store> getStoreById(@PathVariable String storeId) {
        return storeService.getStoreByAccountId(storeId);
    }

    // 获取买家信息
    @GetMapping("/buyer/{buyerId}")
    public Optional<Buyer> getBuyerById(@PathVariable String buyerId) {
        return userService.getBuyerByAccountId(buyerId);
    }

    // 买家支付
    @PutMapping("/{buyerId}/pay/{storeId}/{amount}")
    public Result<Map<String, String>>
    transferMoney(@PathVariable String buyerId,
                  @PathVariable String storeId,
                  @PathVariable BigDecimal amount){
        return walletService.transferMoney(buyerId, storeId,amount);
    }

    // 增加积分
    @PutMapping("/credit/add/{userId}/{amount}")
    public Integer addCredit(@PathVariable String userId,
                             @PathVariable Integer amount                             ){
        return userService.addCredit(userId,amount);
    }

    // 减少积分
    @PutMapping("/credit/reduce/{userId}/{amount}")
    public Integer reduceCredit(@PathVariable String userId,
                             @PathVariable Integer amount                             ){
        return userService.reduceCredit(userId,amount);
    }

    //加载头像和简介
    @PostMapping("/UserInfo/GetPhotoAndDescription")
    public ResponseEntity<Result<AccountDTOs.PandDDTO>> getPhotoAndDescription(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<AccountDTOs.PandDDTO>response=userService.getPandD(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //跨子系统调用，更新店铺评分
    @PutMapping("/UpdateStoreScore/{storeId}/{score}")
    public String updateStoreScore(@PathVariable String storeId, @PathVariable BigDecimal score) {
        return userService.updateStoreScore(storeId,score);
    }

    //添加头像和简介
    @PostMapping("/setPhotoAndDescription")
    public ResponseEntity<Result<String>> setPhotoandDescription(@RequestParam("Photo") MultipartFile productImages,
                                                          @RequestParam("Describtion") String description,Authentication auth){
        String userId = (String) auth.getPrincipal();
        String role= auth.getAuthorities().iterator().next().getAuthority();
        Result<String> response = null;
        try {
            response = userService.setPandD(productImages,description,userId,role);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(response.getCode()).body(response);

    }
}
