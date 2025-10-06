package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Role;

public interface RoleService extends IService<Role> {
    
    /**
     * 分页查询角色信息
     * @param page 分页对象
     * @param role 查询条件
     * @return 角色信息分页数据
     */
    IPage<Role> selectRolePage(IPage<Role> page, Role role);

    /**
     * 根据角色名称或角色编码或角色类型或状态分页查询角色信息
     * @param page 分页对象
     * @param roleName 角色名称
     * @param roleCode 角色编码
     * @param roleType 角色类型
     * @param status 状态
     * @return 角色信息分页数据
     */
    IPage<Role> selectRolePageByMultiple(IPage<Role> page, String roleName, String roleCode, String roleType, String status);
}