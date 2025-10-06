package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.SmartQaKnowledgeDao;
import com.community.domain.entity.SmartQaKnowledge;
import com.community.service.SmartQaKnowledgeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SmartQaKnowledgeServiceImpl extends ServiceImpl<SmartQaKnowledgeDao, SmartQaKnowledge> implements SmartQaKnowledgeService {

    @Override
    public IPage<SmartQaKnowledge> selectSmartQaKnowledgePage(IPage<SmartQaKnowledge> page, SmartQaKnowledge smartQaKnowledge) {
        LambdaQueryWrapper<SmartQaKnowledge> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据分类模糊查询
        if (StringUtils.isNotBlank(smartQaKnowledge.getCategory())) {
            queryWrapper.like(SmartQaKnowledge::getCategory, smartQaKnowledge.getCategory());
        }
        
        // 根据标题模糊查询
        if (StringUtils.isNotBlank(smartQaKnowledge.getTitle())) {
            queryWrapper.like(SmartQaKnowledge::getTitle, smartQaKnowledge.getTitle());
        }
        
        // 根据标签查询
        if (StringUtils.isNotBlank(smartQaKnowledge.getTags())) {
            queryWrapper.like(SmartQaKnowledge::getTags, smartQaKnowledge.getTags());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(smartQaKnowledge.getStatus())) {
            queryWrapper.eq(SmartQaKnowledge::getStatus, smartQaKnowledge.getStatus());
        }
        
        // 根据上传人ID查询
        if (smartQaKnowledge.getCreatedBy() != null) {
            queryWrapper.eq(SmartQaKnowledge::getCreatedBy, smartQaKnowledge.getCreatedBy());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(SmartQaKnowledge::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}