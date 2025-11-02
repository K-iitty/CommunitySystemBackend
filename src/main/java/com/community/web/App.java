package com.community.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

// Knife4j增强注解
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

@SpringBootApplication
@ComponentScan(basePackages = {"com.community"})
@MapperScan(basePackages = {"com.community.dao"})
@EnableCaching
@EnableKnife4j
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}