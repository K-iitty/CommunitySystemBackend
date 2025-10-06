package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.community.dao.SystemAdminDao;
import com.community.domain.entity.SystemAdmin;
import com.community.service.SystemAdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminLoginServiceImpl implements SystemAdminLoginService {
    
    @Autowired
    private SystemAdminDao systemAdminDao;
    
    @Override
    public SystemAdmin login(String username, String password, PasswordEncoder passwordEncoder) {
        // 根据用户名查找管理员
        LambdaQueryWrapper<SystemAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemAdmin::getUsername, username);
        SystemAdmin admin = systemAdminDao.selectOne(queryWrapper);
        
        // 验证密码
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            // 检查账户状态
            if ("正常".equals(admin.getStatus())) {
                // 更新登录信息
                admin.setLastLoginTime(java.time.LocalDateTime.now());
                admin.setLoginCount(admin.getLoginCount() != null ? admin.getLoginCount() + 1 : 1);
                systemAdminDao.updateById(admin);
                return admin;
            }
        }
        
        return null;
    }
    
    @Override
    public boolean register(SystemAdmin admin, PasswordEncoder passwordEncoder) {
        try {
            // 检查用户名是否已存在
            LambdaQueryWrapper<SystemAdmin> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SystemAdmin::getUsername, admin.getUsername());
            Long count = systemAdminDao.selectCount(queryWrapper);
            if (count > 0) {
                return false; // 用户名已存在
            }
            
            // 对密码进行加密
            String encodedPassword = passwordEncoder.encode(admin.getPassword());
            admin.setPassword(encodedPassword);
            
            // 设置默认状态
            if (admin.getStatus() == null || admin.getStatus().isEmpty()) {
                admin.setStatus("正常");
            }
            
            // 设置默认角色类型
            if (admin.getRoleType() == null || admin.getRoleType().isEmpty()) {
                admin.setRoleType("operator"); // 默认为操作员
            }
            
            // 设置创建时间
            admin.setCreatedAt(java.time.LocalDateTime.now());
            
            // 保存到数据库
            return systemAdminDao.insert(admin) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("注册失败: " + e.getMessage());
        }
    }
    @Override
    public SystemAdmin getAdminByUsername(String username) {
        return systemAdminDao.selectOne(
                new LambdaQueryWrapper<SystemAdmin>().eq(SystemAdmin::getUsername, username)
        );
    }
}