package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.AdminOperationLogDao;
import com.community.domain.entity.AdminOperationLog;
import com.community.service.AdminOperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AdminOperationLogServiceImpl extends ServiceImpl<AdminOperationLogDao, AdminOperationLog> implements AdminOperationLogService {

    @Override
    public IPage<AdminOperationLog> selectAdminOperationLogPage(IPage<AdminOperationLog> page, AdminOperationLog adminOperationLog) {
        LambdaQueryWrapper<AdminOperationLog> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据管理员ID查询
        if (adminOperationLog.getAdminId() != null) {
            queryWrapper.eq(AdminOperationLog::getAdminId, adminOperationLog.getAdminId());
        }
        
        // 根据操作模块模糊查询
        if (StringUtils.isNotBlank(adminOperationLog.getOperationModule())) {
            queryWrapper.like(AdminOperationLog::getOperationModule, adminOperationLog.getOperationModule());
        }
        
        // 根据操作类型查询
        if (StringUtils.isNotBlank(adminOperationLog.getOperationType())) {
            queryWrapper.eq(AdminOperationLog::getOperationType, adminOperationLog.getOperationType());
        }
        
        // 根据操作描述模糊查询
        if (StringUtils.isNotBlank(adminOperationLog.getOperationDescription())) {
            queryWrapper.like(AdminOperationLog::getOperationDescription, adminOperationLog.getOperationDescription());
        }
        
        // 根据操作状态查询
        if (StringUtils.isNotBlank(adminOperationLog.getOperationStatus())) {
            queryWrapper.eq(AdminOperationLog::getOperationStatus, adminOperationLog.getOperationStatus());
        }
        
        // 根据请求方法查询
        if (StringUtils.isNotBlank(adminOperationLog.getRequestMethod())) {
            queryWrapper.eq(AdminOperationLog::getRequestMethod, adminOperationLog.getRequestMethod());
        }
        
        // 默认按操作时间倒序排列
        queryWrapper.orderByDesc(AdminOperationLog::getOperationTime);
        
        return this.page(page, queryWrapper);
    }
}