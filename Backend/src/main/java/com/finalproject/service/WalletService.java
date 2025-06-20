package com.finalproject.service;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import com.finalproject.repository.WalletRepository;
import com.finalproject.util.SnowflakeIdGenerator;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class WalletService {

    @Resource
    private WalletRepository walletRepository;

    @Resource
    private RestTemplate restTemplate;

    // 新建钱包
    public boolean createWallet(String userId) {
        Wallet wallet = new Wallet();
        wallet.setAccountId(userId);
        wallet.setBalance(BigDecimal.valueOf(0));
        walletRepository.save(wallet);
        return true;
    }

    @Value("${api.base-url}")
    private String baseUrl;

    public Optional<Store> getStoreById(String storeId) {
        String url = baseUrl + "/api/users/getStore/" + storeId;
        ResponseEntity<Optional<Store>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    // 充值
    public Result<Map<String, String>> recharge(String userId, BigDecimal amount) {
        Optional<Wallet> wallet=walletRepository.findByAccountId(userId);
        if (wallet.isEmpty()) {
            return Result.error(404, "要充值的钱包不存在！");
            // 需要退款操作！！！！！
        }
        wallet.get().setBalance(wallet.get().getBalance().add(amount));
        BigDecimal newBalance=wallet.get().getBalance();
        walletRepository.save(wallet.get());
        Map<String, String> data = new HashMap<>();
        data.put("message", "充值成功！");
        data.put("ID", userId);
        data.put("new_balance", newBalance.toString());
        return Result.success(data);
    }

    // 消费
    public Result<Map<String, String>> subtract(String userId, BigDecimal amount) {
        Optional<Wallet> wallet=walletRepository.findByAccountId(userId);
        if (wallet.isEmpty()) {
            return Result.error(404, "钱包不存在！");
        }
        BigDecimal oldBalance=wallet.get().getBalance();
        if(oldBalance.subtract(amount).compareTo(BigDecimal.ZERO)<0) {
            return Result.error(400, "钱包余额不足！");
        }
        wallet.get().setBalance(wallet.get().getBalance().subtract(amount));
        walletRepository.save(wallet.get());
//        Optional<Wallet> wallet1=walletRepository.findByAccountId(userId);
//        System.out.println("找BUG"+wallet1.get().getBalance());
        BigDecimal newBalance=wallet.get().getBalance();
        Map<String, String> data = new HashMap<>();
        data.put("message", "消费成功！");
        data.put("ID", userId);
        data.put("new_balance", newBalance.toString());
        return Result.success(data);
    }

    // 买家支付，商家得利
    public Result<Map<String, String>> transferMoney(String userId, String storeId, BigDecimal amount) {
        System.out.println("进入进入进入");
        Result<Map<String, String>> payResult = subtract(userId, amount);
        System.out.println("成功信息"+payResult.getData());

        if(payResult.getCode()!=200){
            System.out.println("失败了");
            return payResult;
        }
        Optional<Store> storeOpt=getStoreById(storeId);
        if (storeOpt.isEmpty()) {
            return Result.error(404,"商家不存在");
        }
        Result<Map<String, String>> recharge = recharge(storeId, amount);
        if(recharge.getCode()!=200){
            return recharge;
        }
        return Result.success(200,"支付成功");
    }

    // 查询余额
    public Result<Map<String, String>> checkBalance(String userId) {
        Optional<Wallet> wallet=walletRepository.findByAccountId(userId);
        if (wallet.isEmpty()) {
            return Result.error(404, "钱包所属的用户不存在！");
        }
        BigDecimal balance=wallet.get().getBalance();
        Map<String, String> data = new HashMap<>();
        data.put("message", "查询余额成功！");
        data.put("ID", userId);
        data.put("balance", balance.toString());
        return Result.success(data);
    }


}
