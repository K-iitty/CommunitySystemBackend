package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Staff;

public interface StaffService extends IService<Staff> {
    
    /**
     * 分页查询员工信息
     * @param page 分页对象
     * @param staff 查询条件
     * @return 员工信息分页数据
     */
    IPage<Staff> selectStaffPage(IPage<Staff> page, Staff staff);
}