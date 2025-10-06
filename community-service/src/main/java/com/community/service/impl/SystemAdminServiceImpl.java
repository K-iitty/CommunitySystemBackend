package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.SystemAdminDao;
import com.community.domain.entity.SystemAdmin;
import com.community.service.SystemAdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminDao, SystemAdmin> implements SystemAdminService {

    @Override
    public IPage<SystemAdmin> selectSystemAdminPage(IPage<SystemAdmin> page, SystemAdmin systemAdmin) {
        LambdaQueryWrapper<SystemAdmin> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据用户名模糊查询
        if (StringUtils.isNotBlank(systemAdmin.getUsername())) {
            queryWrapper.like(SystemAdmin::getUsername, systemAdmin.getUsername());
        }
        
        // 根据真实姓名模糊查询
        if (StringUtils.isNotBlank(systemAdmin.getRealName())) {
            queryWrapper.like(SystemAdmin::getRealName, systemAdmin.getRealName());
        }
        
        // 根据手机号查询
        if (StringUtils.isNotBlank(systemAdmin.getPhone())) {
            queryWrapper.eq(SystemAdmin::getPhone, systemAdmin.getPhone());
        }
        
        // 根据邮箱查询
        if (StringUtils.isNotBlank(systemAdmin.getEmail())) {
            queryWrapper.eq(SystemAdmin::getEmail, systemAdmin.getEmail());
        }
        
        // 根据角色类型查询
        if (StringUtils.isNotBlank(systemAdmin.getRoleType())) {
            queryWrapper.eq(SystemAdmin::getRoleType, systemAdmin.getRoleType());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(systemAdmin.getStatus())) {
            queryWrapper.eq(SystemAdmin::getStatus, systemAdmin.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(SystemAdmin::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}