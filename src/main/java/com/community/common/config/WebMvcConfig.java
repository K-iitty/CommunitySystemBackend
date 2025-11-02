package com.community.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // JWT拦截器已移至Spring Security过滤器中处理
    // 保留此配置类以备将来可能的MVC配置需要
}