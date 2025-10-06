package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.CommunityInfoDao;
import com.community.domain.entity.CommunityInfo;
import com.community.service.CommunityInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CommunityInfoServiceImpl extends ServiceImpl<CommunityInfoDao, CommunityInfo> implements CommunityInfoService {

    @Override
    public IPage<CommunityInfo> selectCommunityInfoPage(IPage<CommunityInfo> page, CommunityInfo communityInfo) {
        LambdaQueryWrapper<CommunityInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区名称模糊查询
        if (StringUtils.isNotBlank(communityInfo.getCommunityName())) {
            queryWrapper.like(CommunityInfo::getCommunityName, communityInfo.getCommunityName());
        }
        
        // 根据社区编码查询
        if (StringUtils.isNotBlank(communityInfo.getCommunityCode())) {
            queryWrapper.eq(CommunityInfo::getCommunityCode, communityInfo.getCommunityCode());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(communityInfo.getStatus())) {
            queryWrapper.eq(CommunityInfo::getStatus, communityInfo.getStatus());
        }
        
        // 根据物业公司查询
        if (StringUtils.isNotBlank(communityInfo.getPropertyCompany())) {
            queryWrapper.like(CommunityInfo::getPropertyCompany, communityInfo.getPropertyCompany());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(CommunityInfo::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<CommunityInfo> selectCommunityInfoPageByMultiple(IPage<CommunityInfo> page, String communityName, String detailAddress) {
        LambdaQueryWrapper<CommunityInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区名称模糊查询
        if (StringUtils.isNotBlank(communityName)) {
            queryWrapper.like(CommunityInfo::getCommunityName, communityName);
        }
        
        // 根据详细地址模糊查询
        if (StringUtils.isNotBlank(detailAddress)) {
            queryWrapper.like(CommunityInfo::getDetailAddress, detailAddress);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(CommunityInfo::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}