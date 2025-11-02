package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.IssueFollowUp;

public interface IssueFollowUpService extends IService<IssueFollowUp> {
    
    /**
     * 分页查询问题跟进信息
     * @param page 分页对象
     * @param issueFollowUp 查询条件
     * @return 问题跟进信息分页数据
     */
    IPage<IssueFollowUp> selectIssueFollowUpPage(IPage<IssueFollowUp> page, IssueFollowUp issueFollowUp);
}