package com.community.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.community.domain.entity.SystemAdmin;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface SystemAdminLoginService {
    
    /**
     * 系统管理员登录
     * @param username 用户名
     * @param password 密码
     * @param passwordEncoder 密码编码器
     * @return 登录成功的管理员信息
     */
    SystemAdmin login(String username, String password, PasswordEncoder passwordEncoder);
    
    /**
     * 系统管理员注册
     * @param admin 管理员信息
     * @param passwordEncoder 密码编码器
     * @return 注册结果
     */
    boolean register(SystemAdmin admin, PasswordEncoder passwordEncoder);
    /**
     * 根据用户名获取管理员信息
     * @param username 用户名
     * @return 管理员信息
     */
    SystemAdmin getAdminByUsername(String username);

}
