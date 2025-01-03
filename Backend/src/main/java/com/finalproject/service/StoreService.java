package com.finalproject.service;
import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import com.finalproject.util.JwtTokenUtil;
import com.finalproject.util.SnowflakeIdGenerator;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.finalproject.repository.StoreRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class StoreService {

    @Value("${api.base-url}")
    private String baseUrl;
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Optional<Store> getStoreByAccountId(String accountId) {
        // 根据 accountId 查找对应的 Store
        return storeRepository.findByAccountId(accountId);
    }

    // 修改卖家地址
    public Result<Map<String, String>> updateStoreAdress(String userId, String newAddress) {
        Optional<Store> userOptional= storeRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改地址的卖家账号不存在");
        }
        Store user = userOptional.get();
        user.setAddress(newAddress);
        storeRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"地址修改成功！");
        return Result.success(data);
    }

    // 修改卖家店铺名
    public Result<Map<String, String>> updateStoreName(String userId, String newName) {
        Optional<Store> userOptional= storeRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404, "想要修改店铺名的卖家账号不存在");
        }
        Store user = userOptional.get();
        user.setStoreName(newName);
        storeRepository.save(user);
        Map<String, String> data = new HashMap<>();
        data.put("message", userId+"店铺名修改成功！");
        return Result.success(data);
    }

    // 获取卖家地址
    public Result<Map<String, String>> getStoreAddress(String userId) {
        Optional<Store> userOptional= storeRepository.findByAccountId(userId);
        if (userOptional.isEmpty()) {
            return Result.error(404,"想获取地址的店铺不存在");
        }
        Store user = userOptional.get();
        Map<String, String> data = new HashMap<>();
        data.put("address", user.getAddress());
        return Result.success(data);
    }

    // 获取商家全部基本信息
    public Result<AccountDTOs.StoreInfoDTO> getStoreInfo(String userId) {
        // 查找用户
        Optional<Store> userOptional = storeRepository.findByAccountId(userId);
        // 如果没有找到用户
        if (userOptional.isEmpty()) {
            return Result.error(404, "想获取基本信息的用户不存在");
        }
        Store user = userOptional.get();
        String Id=user.getPhotoId();
        String imageUrl=baseUrl+"/images/"+Id;
        AccountDTOs.StoreInfoDTO userInfo = new AccountDTOs.StoreInfoDTO();
        userInfo.setAddress(user.getAddress());
        userInfo.setEmail(user.getEmail());
        userInfo.setStoreName(user.getStoreName());
        userInfo.setStoreScore(user.getStoreScore().floatValue());
        userInfo.setUserName(user.getUserName());
        userInfo.setHasCertificate(user.isCertification());
        userInfo.setPhotoId(imageUrl);
        userInfo.setUserId(userId);
        userInfo.setDescription(user.getDescription());
        // 返回成功结果
        return Result.success(userInfo);
    }

    public String updateStoreScore(String userId, BigDecimal score) {
        Optional<Store> storeOptional = storeRepository.findByAccountId(userId);
        Store store = storeOptional.get();
        store.setStoreScore(score);
        storeRepository.save(store);
        return store.getStoreName();
    }





}
