package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Owner;

public interface OwnerService extends IService<Owner> {
    
    /**
     * 分页查询业主信息
     * @param page 分页对象
     * @param owner 查询条件
     * @return 业主信息分页数据
     */
    IPage<Owner> selectOwnerPage(IPage<Owner> page, Owner owner);

    /**
     * 根据业主姓名或手机号或业主类型或状态分页查询业主信息
     * @param page 分页对象
     * @param name 业主姓名
     * @param phone 手机号
     * @param ownerType 业主类型
     * @param status 状态
     * @return 业主信息分页数据
     */
    IPage<Owner> selectOwnerPageByMultiple(IPage<Owner> page, String name, String phone, String ownerType, String status);
}