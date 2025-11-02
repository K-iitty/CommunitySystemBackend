package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.OwnerIssue;
import org.springframework.web.multipart.MultipartFile;

public interface OwnerIssueService extends IService<OwnerIssue> {
    
    /**
     * 分页查询业主问题信息
     * @param page 分页对象
     * @param ownerIssue 查询条件
     * @return 业主问题信息分页数据
     */
    IPage<OwnerIssue> selectOwnerIssuePage(IPage<OwnerIssue> page, OwnerIssue ownerIssue);
    
    /**
     * 更新问题描述图片
     * @param id 问题ID
     * @param files 问题描述图片文件
     * @return 是否成功
     */
    boolean updateAdditionalImages(Long id, MultipartFile[] files);
    
    /**
     * 获取问题描述图片
     * @param id 问题ID
     * @return 问题描述图片URL列表
     */
    String getAdditionalImages(Long id);
    
    /**
     * 分页查询待处理的业主问题
     * @param page 分页对象
     * @return 待处理业主问题分页数据
     */
    IPage<OwnerIssue> selectPendingIssuePage(IPage<OwnerIssue> page);
}