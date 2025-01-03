package com.finalproject.service;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Buyer;
import com.finalproject.repository.BuyerRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {
    @Resource
    private BuyerRepository buyerRepository;
    @Value("${api.base-url}")
    private String baseUrl;

    // 获取买家全部基本信息
    public Result<AccountDTOs.BuyerInfoDTO> getBuyerInfo(String userId) {
        // 查找用户
        Optional<Buyer> userOptional = buyerRepository.findByAccountId(userId);
        // 如果没有找到用户
        if (userOptional.isEmpty()) {
            return Result.error(404, "想获取基本信息的用户不存在");
        }
        Buyer user = userOptional.get();
        AccountDTOs.BuyerInfoDTO userInfo = new AccountDTOs.BuyerInfoDTO();
        userInfo.setAddress(user.getAddress());
        userInfo.setEmail(user.getEmail());
        userInfo.setAge(user.getAge());
        userInfo.setGender(user.getGender());
        userInfo.setUserName(user.getUserName());
        userInfo.setTotalCredits(user.getTotalCredits());
        userInfo.setPhotoId(user.getPhotoId());
        userInfo.setPhotoUrl(baseUrl + "/images/" + user.getPhotoId());
        userInfo.setUserId(userId);
        userInfo.setDescription(user.getDescription());
        // 返回成功结果
        return Result.success(userInfo);
    }

    public Optional<Buyer> getBuyerByAccountId(String buyerId) {
        return buyerRepository.findByAccountId(buyerId);
    }

    public Integer addCredit(String userId, Integer amount) {
        Optional<Buyer> buyerOptional = buyerRepository.findByAccountId(userId);
        if (buyerOptional.isEmpty()) {
            return 404;
        }
        Buyer buyer = buyerOptional.get();
        buyer.setTotalCredits(buyer.getTotalCredits() + amount);
        buyerRepository.save(buyer);
        return 200;
    }

    public Integer reduceCredit(String userId, Integer amount) {
        Optional<Buyer> buyerOptional = buyerRepository.findByAccountId(userId);
        if (buyerOptional.isEmpty()) {
            return 404;
        }
        Buyer buyer = buyerOptional.get();
        buyer.setTotalCredits(buyer.getTotalCredits() - amount);
        buyerRepository.save(buyer);
        return 200;
    }

}
