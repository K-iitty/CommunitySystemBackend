package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.IssueFollowUpDao;
import com.community.domain.entity.IssueFollowUp;
import com.community.service.IssueFollowUpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class IssueFollowUpServiceImpl extends ServiceImpl<IssueFollowUpDao, IssueFollowUp> implements IssueFollowUpService {

    @Override
    public IPage<IssueFollowUp> selectIssueFollowUpPage(IPage<IssueFollowUp> page, IssueFollowUp issueFollowUp) {
        LambdaQueryWrapper<IssueFollowUp> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据问题ID查询
        if (issueFollowUp.getIssueId() != null) {
            queryWrapper.eq(IssueFollowUp::getIssueId, issueFollowUp.getIssueId());
        }
        
        // 根据操作人ID查询
        if (issueFollowUp.getOperatorId() != null) {
            queryWrapper.eq(IssueFollowUp::getOperatorId, issueFollowUp.getOperatorId());
        }
        
        // 根据操作人姓名模糊查询
        if (StringUtils.isNotBlank(issueFollowUp.getOperatorName())) {
            queryWrapper.like(IssueFollowUp::getOperatorName, issueFollowUp.getOperatorName());
        }
        
        // 根据跟进内容模糊查询
        if (StringUtils.isNotBlank(issueFollowUp.getFollowUpContent())) {
            queryWrapper.like(IssueFollowUp::getFollowUpContent, issueFollowUp.getFollowUpContent());
        }
        
        // 根据跟进类型查询
        if (StringUtils.isNotBlank(issueFollowUp.getFollowUpType())) {
            queryWrapper.eq(IssueFollowUp::getFollowUpType, issueFollowUp.getFollowUpType());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(IssueFollowUp::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}