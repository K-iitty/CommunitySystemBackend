package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.common.service.OssService;
import com.community.dao.CommunityNoticeDao;
import com.community.domain.entity.CommunityNotice;
import com.community.service.CommunityNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityNoticeServiceImpl extends ServiceImpl<CommunityNoticeDao, CommunityNotice> implements CommunityNoticeService {
    
    @Autowired
    private OssService ossService;

    @Override
    public IPage<CommunityNotice> selectCommunityNoticePage(IPage<CommunityNotice> page, CommunityNotice communityNotice) {
        LambdaQueryWrapper<CommunityNotice> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (communityNotice.getCommunityId() != null) {
            queryWrapper.eq(CommunityNotice::getCommunityId, communityNotice.getCommunityId());
        }
        
        // 根据公告标题模糊查询
        if (StringUtils.isNotBlank(communityNotice.getTitle())) {
            queryWrapper.like(CommunityNotice::getTitle, communityNotice.getTitle());
        }
        
        // 根据公告类型查询
        if (StringUtils.isNotBlank(communityNotice.getNoticeType())) {
            queryWrapper.eq(CommunityNotice::getNoticeType, communityNotice.getNoticeType());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(communityNotice.getStatus())) {
            queryWrapper.eq(CommunityNotice::getStatus, communityNotice.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(CommunityNotice::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNoticeImages(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "community/notice/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            CommunityNotice communityNotice = this.getById(id);
            if (communityNotice != null) {
                String photos = String.join(",", photoUrls);
                communityNotice.setNoticeImages(photos);
                return this.updateById(communityNotice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getNoticeImages(Long id) {
        if (id == null) {
            return null;
        }

        CommunityNotice communityNotice = this.getById(id);
        return communityNotice != null ? communityNotice.getNoticeImages() : null;
    }

    private String saveFile(MultipartFile file, String subPath) {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}