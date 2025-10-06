package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.RoleDao;
import com.community.domain.entity.Role;
import com.community.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public IPage<Role> selectRolePage(IPage<Role> page, Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据角色名称模糊查询
        if (StringUtils.isNotBlank(role.getRoleName())) {
            queryWrapper.like(Role::getRoleName, role.getRoleName());
        }
        
        // 根据角色编码查询
        if (StringUtils.isNotBlank(role.getRoleCode())) {
            queryWrapper.eq(Role::getRoleCode, role.getRoleCode());
        }
        
        // 根据角色类型查询
        if (StringUtils.isNotBlank(role.getRoleType())) {
            queryWrapper.eq(Role::getRoleType, role.getRoleType());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(role.getStatus())) {
            queryWrapper.eq(Role::getStatus, role.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Role::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<Role> selectRolePageByMultiple(IPage<Role> page, String roleName, String roleCode, String roleType, String status) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据角色名称模糊查询
        if (StringUtils.isNotBlank(roleName)) {
            queryWrapper.like(Role::getRoleName, roleName);
        }
        
        // 根据角色编码查询
        if (StringUtils.isNotBlank(roleCode)) {
            queryWrapper.eq(Role::getRoleCode, roleCode);
        }
        
        // 根据角色类型查询
        if (StringUtils.isNotBlank(roleType)) {
            queryWrapper.eq(Role::getRoleType, roleType);
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(status)) {
            queryWrapper.eq(Role::getStatus, status);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Role::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}