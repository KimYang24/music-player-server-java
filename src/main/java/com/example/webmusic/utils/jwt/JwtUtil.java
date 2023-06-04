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

    /***
     * 生成 JWT
     * @param subject 主题
     * @param claims 自定义声明
     * @param expiration 过期时间，单位为秒
     * @return JWT
     */
    public String generateToken(String subject, int ID, int expiration) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 添加 JWT 的自定义声明
        Map<String,Object> claims = new HashMap<>();
        claims.put("ID",ID);

        // 设置 JWT 有效期
        long expMillis = nowMillis + expiration * 1000L;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    /***
     * 验证 JWT
     * @param token JWT
     * @return
     */
    public Claims validateToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }

}
