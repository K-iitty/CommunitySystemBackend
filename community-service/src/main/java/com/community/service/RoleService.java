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
}