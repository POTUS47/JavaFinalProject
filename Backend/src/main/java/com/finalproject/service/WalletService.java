package com.finalproject.service;

import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import com.finalproject.repository.WalletRepository;
import com.finalproject.util.SnowflakeIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class WalletService {

    @Resource
    private WalletRepository walletRepository;

    // 新建钱包
    public boolean createWallet(String userId) {
        Wallet wallet = new Wallet();
        wallet.setAccountId(userId);
        wallet.setBalance(BigDecimal.valueOf(0));
        walletRepository.save(wallet);
        return true;
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
        BigDecimal newBalance=wallet.get().getBalance();
        Map<String, String> data = new HashMap<>();
        data.put("message", "消费成功！");
        data.put("ID", userId);
        data.put("new_balance", newBalance.toString());
        return Result.success(data);
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
