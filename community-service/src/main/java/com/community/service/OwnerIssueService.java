package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.OwnerIssue;

public interface OwnerIssueService extends IService<OwnerIssue> {
    
    /**
     * 分页查询业主问题信息
     * @param page 分页对象
     * @param ownerIssue 查询条件
     * @return 业主问题信息分页数据
     */
    IPage<OwnerIssue> selectOwnerIssuePage(IPage<OwnerIssue> page, OwnerIssue ownerIssue);
}