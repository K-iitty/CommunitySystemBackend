package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.common.service.OssService;
import com.community.dao.OwnerIssueDao;
import com.community.domain.entity.OwnerIssue;
import com.community.service.OwnerIssueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerIssueServiceImpl extends ServiceImpl<OwnerIssueDao, OwnerIssue> implements OwnerIssueService {
    
    @Autowired
    private OssService ossService;

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

    @Override
    public boolean updateAdditionalImages(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "owner/issue/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            OwnerIssue ownerIssue = this.getById(id);
            if (ownerIssue != null) {
                String photos = String.join(",", photoUrls);
                ownerIssue.setAdditionalImages(photos);
                return this.updateById(ownerIssue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getAdditionalImages(Long id) {
        if (id == null) {
            return null;
        }

        OwnerIssue ownerIssue = this.getById(id);
        return ownerIssue != null ? ownerIssue.getAdditionalImages() : null;
    }

    @Override
    public IPage<OwnerIssue> selectPendingIssuePage(IPage<OwnerIssue> page) {
        LambdaQueryWrapper<OwnerIssue> queryWrapper = new LambdaQueryWrapper<>();
        
        // 查询issue_status为"待处理"的数据
        queryWrapper.eq(OwnerIssue::getIssueStatus, "待处理");
        
        // 按上报时间倒序排列（最新的在最前面）
        queryWrapper.orderByDesc(OwnerIssue::getReportedTime);
        
        return this.page(page, queryWrapper);
    }

    private String saveFile(MultipartFile file, String subPath) {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}