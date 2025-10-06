package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.StaffExtension;

public interface StaffExtensionService extends IService<StaffExtension> {
    
    /**
     * 分页查询员工扩展信息
     * @param page 分页对象
     * @param staffExtension 查询条件
     * @return 员工扩展信息分页数据
     */
    IPage<StaffExtension> selectStaffExtensionPage(IPage<StaffExtension> page, StaffExtension staffExtension);
}