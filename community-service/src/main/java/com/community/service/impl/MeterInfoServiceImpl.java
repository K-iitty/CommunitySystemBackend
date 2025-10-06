package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.MeterInfoDao;
import com.community.domain.entity.MeterInfo;
import com.community.service.MeterInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MeterInfoServiceImpl extends ServiceImpl<MeterInfoDao, MeterInfo> implements MeterInfoService {

    @Override
    public IPage<MeterInfo> selectMeterInfoPage(IPage<MeterInfo> page, MeterInfo meterInfo) {
        LambdaQueryWrapper<MeterInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (meterInfo.getCommunityId() != null) {
            queryWrapper.eq(MeterInfo::getCommunityId, meterInfo.getCommunityId());
        }
        
        // 根据房屋ID查询
        if (meterInfo.getHouseId() != null) {
            queryWrapper.eq(MeterInfo::getHouseId, meterInfo.getHouseId());
        }
        
        // 根据楼栋ID查询
        if (meterInfo.getBuildingId() != null) {
            queryWrapper.eq(MeterInfo::getBuildingId, meterInfo.getBuildingId());
        }
        
        // 根据配置ID查询
        if (meterInfo.getConfigId() != null) {
            queryWrapper.eq(MeterInfo::getConfigId, meterInfo.getConfigId());
        }
        
        // 根据仪表名称模糊查询
        if (StringUtils.isNotBlank(meterInfo.getMeterName())) {
            queryWrapper.like(MeterInfo::getMeterName, meterInfo.getMeterName());
        }
        
        // 根据仪表编码查询
        if (StringUtils.isNotBlank(meterInfo.getMeterCode())) {
            queryWrapper.eq(MeterInfo::getMeterCode, meterInfo.getMeterCode());
        }
        
        // 根据仪表状态查询
        if (StringUtils.isNotBlank(meterInfo.getMeterStatus())) {
            queryWrapper.eq(MeterInfo::getMeterStatus, meterInfo.getMeterStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(MeterInfo::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<MeterInfo> selectMeterInfoPageByMultiple(IPage<MeterInfo> page, String meterCode, String meterName, String locationType, String meterStatus) {
        LambdaQueryWrapper<MeterInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据仪表编码查询
        if (StringUtils.isNotBlank(meterCode)) {
            queryWrapper.eq(MeterInfo::getMeterCode, meterCode);
        }
        
        // 根据仪表名称模糊查询
        if (StringUtils.isNotBlank(meterName)) {
            queryWrapper.like(MeterInfo::getMeterName, meterName);
        }
        
        // 根据位置类型查询
        if (StringUtils.isNotBlank(locationType)) {
            queryWrapper.eq(MeterInfo::getLocationType, locationType);
        }
        
        // 根据仪表状态查询
        if (StringUtils.isNotBlank(meterStatus)) {
            queryWrapper.eq(MeterInfo::getMeterStatus, meterStatus);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(MeterInfo::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}