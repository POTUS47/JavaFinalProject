package com.finalproject.service;
import jakarta.annotation.*;
import org.springframework.stereotype.Service;
import com.finalproject.model.Account;
import com.finalproject.repository.AccountRepository;

import java.util.List;

//通过注解，标注这是Service层
@Service
public class AccountService {
    //因为在Service层中我们需要调用Dao层
    //所以将Dao层作为一个资源将其加进来
    @Resource
    private AccountRepository accountRepository;

    //现在我们来做一下需求分析
    //可能我们需要找学生表中所以学生的信息
    //那么定义的findAll方法返回值应该是一个集合
    public List<Account> findAll() {
        //这里可以直接把studentRepository拉过来用
        //因为Dao层中StudentRepository有继承过来的功能
        return accountRepository.findAll();
    }

}