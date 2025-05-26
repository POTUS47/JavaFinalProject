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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:5173"); // 前端的域名
//        config.addAllowedHeader("*"); // 允许所有请求头，包括 Authorization
//        config.addExposedHeader("Authorization"); // 允许前端访问 Authorization 响应头
//        config.addAllowedMethod("OPTIONS"); // 显式允许 OPTIONS 请求
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//        config.setAllowCredentials(true); // 允许携带 Cookie 和凭证
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // 允许前端域名
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("Authorization")); // 如果需要暴露 token 到前端
        config.setAllowCredentials(true); // 允许携带 cookie/token

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config); // 只对 /api 开头的路径生效
        return source;
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // 禁用CSRF
//                .authorizeHttpRequests(auth -> auth
//                        // 配置允许的请求
//                        .requestMatchers("/api/users/register", "/api/users/login", "/api/users/send-code/**"
//                        ,"api/users/changePassword").permitAll() // 注册、登录、验证码、修改密码不需要验证
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // 允许访问 Swagger UI 和 API 文档
//                        // 对来自白名单 IP 的请求进行放行，无需 JWT 认证
//                        .requestMatchers(request -> isTrustedIp(request.getRemoteAddr())).permitAll() // IP 白名单
//                        .anyRequest().authenticated() // 其他请求需要身份验证
//                )
//                // 添加 JWT 认证过滤器
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 启用 CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/users/login", "/api/users/send-code/**", "/api/users/changePassword").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(request -> isTrustedIp(request.getRemoteAddr())).permitAll()
                        .anyRequest().authenticated()
                )
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
