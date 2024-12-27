package com.finalproject.config;

import com.finalproject.interceptor.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //private static final String TRUSTED_IP = "47.97.59.189";  // 允许的 IP 地址

    @Value("${trusted.ip}")
    private String trustedIp;

    public String getTrustedIp() {
        return trustedIp;
    }

    public void setTrustedIp(String trustedIp) {
        this.trustedIp = trustedIp;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 禁用CSRF
                .authorizeHttpRequests(auth -> auth
                        // 配置允许的请求
                        .requestMatchers("/users/register", "/users/login", "/users/send-code").permitAll() // 注册、登录、验证码不需要验证
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // 允许访问 Swagger UI 和 API 文档
                        // 对来自白名单 IP 的请求进行放行，无需 JWT 认证
                        .requestMatchers(request -> isTrustedIp(request.getRemoteAddr())).permitAll() // IP 白名单
                        .anyRequest().authenticated() // 其他请求需要身份验证
                )
                // 添加 JWT 认证过滤器
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 配置BCrypt加密器
    }

    // 判断请求的 IP 是否在信任的白名单内
    private boolean isTrustedIp(String ip) {
        // 将信任的 IP 地址放到一个数组中
        String[] trustedIps = trustedIp.split(", ");
        for (String trustedIp : trustedIps) {
            if (trustedIp.equals(ip)) {
                return true;
            }
        }
        return false;
    }
}
