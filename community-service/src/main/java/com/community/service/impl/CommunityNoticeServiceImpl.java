package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.CommunityNoticeDao;
import com.community.domain.entity.CommunityNotice;
import com.community.service.CommunityNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CommunityNoticeServiceImpl extends ServiceImpl<CommunityNoticeDao, CommunityNotice> implements CommunityNoticeService {

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
}