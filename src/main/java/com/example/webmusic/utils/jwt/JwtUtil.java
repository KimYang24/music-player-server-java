package com.example.webmusic.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String secret = "mySecretKey";  // JWT 密钥

    public static String generateToken(long ID) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 添加 JWT 的自定义声明
        Map<String,Object> claims = new HashMap<>();
        claims.put("ID",ID);

        // 设置 JWT 有效期
        long expMillis = nowMillis + 300 * 1000L;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

//验证token
    public  static Claims validateToken(String token) {
        System.out.println("进入验证函数");
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }
}
