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
}