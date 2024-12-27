package com.finalproject.service;
import com.finalproject.DTO.AccountDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import com.finalproject.util.JwtTokenUtil;
import com.finalproject.util.SnowflakeIdGenerator;
import jakarta.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.finalproject.repository.StoreRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Optional<Store> getStoreByAccountId(String accountId) {
        // 根据 accountId 查找对应的 Store
        return storeRepository.findByAccountId(accountId);
    }

}
