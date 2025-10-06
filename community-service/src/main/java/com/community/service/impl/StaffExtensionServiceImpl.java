package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.StaffExtensionDao;
import com.community.domain.entity.StaffExtension;
import com.community.service.StaffExtensionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StaffExtensionServiceImpl extends ServiceImpl<StaffExtensionDao, StaffExtension> implements StaffExtensionService {

    @Override
    public IPage<StaffExtension> selectStaffExtensionPage(IPage<StaffExtension> page, StaffExtension staffExtension) {
        LambdaQueryWrapper<StaffExtension> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据员工ID查询
        if (staffExtension.getStaffId() != null) {
            queryWrapper.eq(StaffExtension::getStaffId, staffExtension.getStaffId());
        }
        
        // 根据扩展键模糊查询
        if (StringUtils.isNotBlank(staffExtension.getExtensionKey())) {
            queryWrapper.like(StaffExtension::getExtensionKey, staffExtension.getExtensionKey());
        }
        
        // 根据值类型查询
        if (StringUtils.isNotBlank(staffExtension.getValueType())) {
            queryWrapper.eq(StaffExtension::getValueType, staffExtension.getValueType());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(StaffExtension::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}