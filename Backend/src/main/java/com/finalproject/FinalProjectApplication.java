package com.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finalproject.util.JwtTokenUtil;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(FinalProjectApplication.class, args);
//        System.out.println("Hello World");
//        JwtTokenUtil.testJWT();

    }


}
