package com.finalproject.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.finalproject.service.AccountService;
import com.finalproject.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AccountService userService;

    // 构造器注入 AccountService
    @Autowired // 确保依赖注入
    public JwtAuthenticationFilter(AccountService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 处理 OPTIONS 请求，设置跨域头
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 设置具体的允许跨域的域名（不能是 *）
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 修改为实际前端域名
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With");
            response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许携带凭证（Cookies、Authorization等）
            response.setHeader("Access-Control-Max-Age", "3600"); // 缓存时间（秒）
            response.setStatus(HttpServletResponse.SC_OK);
        }

        String token = request.getHeader("Authorization"); // 从请求头获取Token
        if (token == null || token.isEmpty()) {
            filterChain.doFilter(request, response); // 如果没有Token，直接放行（可能是公共API）
            return;
        }

        try {
            // 验证Token并获取声明
            DecodedJWT decodedJWT = JwtTokenUtil.verify(token);
            String userId = decodedJWT.getClaim("id").asString();
            String role = decodedJWT.getClaim("role").asString();

            // 验证用户 ID 是否在数据库中存在
            if (!userService.isUserExists(userId)) {
                throw new IllegalArgumentException("User does not exist");
            }

            // 创建Authentication对象并设置到SecurityContext
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority(role)));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"Invalid or expired token\"}");
            return;
        }

        // 调用下一个过滤器
        filterChain.doFilter(request, response);
    }
}
