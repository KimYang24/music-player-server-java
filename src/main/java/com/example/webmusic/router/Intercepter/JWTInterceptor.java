package com.example.webmusic.router.Intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.webmusic.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入鉴权");
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
        System.out.println("截取token成功");
            try {
                Claims claims = JwtUtil.validateToken(token);
                if (claims != null) {
                    System.out.println("claims非空");
                    // 将用户信息存入请求中，方便后续的操作
                    request.setAttribute("ID", claims.get("ID"));
                }
            } catch (Exception e) {
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                response.setStatus(200);
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));
                return false;
            }
        }
        return true;
    }


}
