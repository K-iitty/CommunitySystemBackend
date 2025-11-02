package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.SmartQaKnowledge;

public interface SmartQaKnowledgeService extends IService<SmartQaKnowledge> {
    
    /**
     * 分页查询知识库信息
     * @param page 分页对象
     * @param smartQaKnowledge 查询条件
     * @return 知识库信息分页数据
     */
    IPage<SmartQaKnowledge> selectSmartQaKnowledgePage(IPage<SmartQaKnowledge> page, SmartQaKnowledge smartQaKnowledge);

    /**
     * 根据标题分页查询知识库信息
     * @param page 分页对象
     * @param title 标题
     * @return 知识库信息分页数据
     */
    IPage<SmartQaKnowledge> selectSmartQaKnowledgePageByMultiple(IPage<SmartQaKnowledge> page, String title);
}