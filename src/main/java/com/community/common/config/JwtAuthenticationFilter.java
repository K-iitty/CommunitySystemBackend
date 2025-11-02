package com.community.common.config;

import com.community.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 白名单路径直接放行
        if (isWhitelisted(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 从请求头中获取token
        String token = getTokenFromRequest(request);

        logger.debug("请求URI: " + requestURI + ", Token: " + token);

        if (token != null && !token.isEmpty()) {
            try {
                // 验证token
                String username = jwtUtil.getUsernameFromToken(token);
                if (username != null && !jwtUtil.isTokenExpired(token)) {
                    // 创建认证对象（简化版，不包含用户详细信息）
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    
                    // 设置到安全上下文
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    logger.debug("JWT认证成功: " + username);
                } else {
                    logger.debug("JWT Token已过期或无效");
                }
            } catch (Exception e) {
                logger.error("JWT认证失败: " + e.getMessage());
                // 认证失败时清除上下文
                SecurityContextHolder.clearContext();
            }
        } else {
            logger.debug("请求未包含JWT Token: " + requestURI);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization header: " + bearerToken);
        
        if (bearerToken != null) {
            if (bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            } else {
                // 如果没有Bearer前缀，直接返回token
                return bearerToken;
            }
        }
        return null;
    }

    private boolean isWhitelisted(String requestURI) {
        String[] whiteList = {
                "/api/systemAdmin/login",
                "/api/systemAdmin/register",
                "/api/systemAdmin/logout",
                "/doc.html",
                "/webjars/",
                "/swagger-resources",
                "/v3/api-docs",
                "/v3/api-docs/"
        };

        for (String pattern : whiteList) {
            if (requestURI.startsWith(pattern)) {
                return true;
            }
        }
        return false;
    }
}