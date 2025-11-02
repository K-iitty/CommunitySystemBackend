package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.AdminOperationLog;

public interface AdminOperationLogService extends IService<AdminOperationLog> {
    
    /**
     * 分页查询管理员操作日志信息
     * @param page 分页对象
     * @param adminOperationLog 查询条件
     * @return 管理员操作日志信息分页数据
     */
    IPage<AdminOperationLog> selectAdminOperationLogPage(IPage<AdminOperationLog> page, AdminOperationLog adminOperationLog);
}