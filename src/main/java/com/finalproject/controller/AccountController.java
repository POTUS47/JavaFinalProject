package com.finalproject.controller;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finalproject.model.Account;
import com.finalproject.service.AccountService;
import com.finalproject.DTO.Result;
import java.util.List;

//通过RestController、RequestMapping注解来标注控制层

@RestController//表示一种规范，因为现在都是前后端分离，中间通过接口(API)来连接，API返回的是json格式的数据
@RequestMapping("/stu")//将url中的路径(该路径就是括号中的/stu)与下面的Controller，形成映射关系
public class AccountController {
    //在我们对Student表进行操作的时候，就比如说查找所有--findAll方法
    //而findAll方法是在Service层中，所以StudentService也需要作为资源传进来

    @Resource
    private AccountService studentService;


    //一般我们向服务器发起请求都是通过get方式
    //那么这边我们也用GetMapping
    @GetMapping("/all")//这个 ("/all") 可不加，因为我这边默认向服务器发起请求的方式是get(获取所有消息)
    public Result<List<Account>> findAll(){
        List<Account> list = studentService.findAll();
        return Result.success(list);
    }

}