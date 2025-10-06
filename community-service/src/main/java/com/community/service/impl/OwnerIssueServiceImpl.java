package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.OwnerIssueDao;
import com.community.domain.entity.OwnerIssue;
import com.community.service.OwnerIssueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OwnerIssueServiceImpl extends ServiceImpl<OwnerIssueDao, OwnerIssue> implements OwnerIssueService {

    @Override
    public IPage<OwnerIssue> selectOwnerIssuePage(IPage<OwnerIssue> page, OwnerIssue ownerIssue) {
        LambdaQueryWrapper<OwnerIssue> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (ownerIssue.getCommunityId() != null) {
            queryWrapper.eq(OwnerIssue::getCommunityId, ownerIssue.getCommunityId());
        }
        
        // 根据业主ID查询
        if (ownerIssue.getOwnerId() != null) {
            queryWrapper.eq(OwnerIssue::getOwnerId, ownerIssue.getOwnerId());
        }
        
        // 根据问题标题模糊查询
        if (StringUtils.isNotBlank(ownerIssue.getIssueTitle())) {
            queryWrapper.like(OwnerIssue::getIssueTitle, ownerIssue.getIssueTitle());
        }
        
        // 根据问题类型查询
        if (StringUtils.isNotBlank(ownerIssue.getIssueType())) {
            queryWrapper.eq(OwnerIssue::getIssueType, ownerIssue.getIssueType());
        }
        
        // 根据问题状态查询
        if (StringUtils.isNotBlank(ownerIssue.getIssueStatus())) {
            queryWrapper.eq(OwnerIssue::getIssueStatus, ownerIssue.getIssueStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(OwnerIssue::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}