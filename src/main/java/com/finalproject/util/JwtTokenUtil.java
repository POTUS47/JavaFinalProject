package com.finalproject.util;

import java.security.Key;
import java.util.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtil {
    public static final String SECRET = "secureKey123"; // 更换更复杂的密钥

    // 生成JWT
    public static String generateJWT(String id, String role) {
        Map<String, String> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role);
        return JwtTokenUtil.getToken(claims); // 调用 JwtTokenUtil 来生成 token
    }

    // 生成Token
    public static String getToken(Map<String, String> claims) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); // 过期时间为7天

        JWTCreator.Builder builder = JWT.create();
        claims.forEach(builder::withClaim); // 添加自定义声明
        return builder.withExpiresAt(instance.getTime()) // 设置过期时间
                .sign(Algorithm.HMAC256(SECRET));  // 使用HMAC256签名
    }

    // 验证Token并返回解析结果
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static void testJWT() {

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        System.out.println("=============创建 JWT===========");
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString()) // 载荷-标准中注册的声明
                .setSubject("admin") // 载荷-标准中注册的声明
                .setIssuedAt(now) // 载荷-标准中注册的声明，表示签发时间
                .claim("id", "123456") // 载荷-公共的声明
                .claim("name", "MoonlightL") // 载荷-公共的声明
                .claim("sex", "male") // 载荷-公共的声明
                .signWith(key); // 签证

        String jwt = builder.compact();
        System.out.println("生成的 jwt :" + jwt);

        System.out.println("=============解析 JWT===========");

        try {
            Jws<Claims> result = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            // 以下步骤随实际情况而定，只要上一行代码执行不抛异常就证明 jwt 是有效的、合法的
            Claims body = result.getBody();

            System.out.println("载荷-标准中注册的声明 id:" + body.getId());
            System.out.println("载荷-标准中注册的声明 subject:" + body.getSubject());
            System.out.println("载荷-标准中注册的声明 issueAt:" + body.getIssuedAt());


            System.out.println("载荷-公共的声明的 id:" + result.getBody().get("id"));
            System.out.println("载荷-公共的声明的 name:" + result.getBody().get("name"));
            System.out.println("载荷-公共的声明的 sex:" + result.getBody().get("sex"));

        } catch (JwtException ex) { // jwt 不合法或过期都会抛异常
            ex.printStackTrace();
        }
    }
}



