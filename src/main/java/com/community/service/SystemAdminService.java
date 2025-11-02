package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.SystemAdmin;

public interface SystemAdminService extends IService<SystemAdmin> {
    
    /**
     * 分页查询系统管理员信息
     * @param page 分页对象
     * @param systemAdmin 查询条件
     * @return 系统管理员信息分页数据
     */
    IPage<SystemAdmin> selectSystemAdminPage(IPage<SystemAdmin> page, SystemAdmin systemAdmin);
}