package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.MeterConfigDao;
import com.community.domain.entity.MeterConfig;
import com.community.service.MeterConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MeterConfigServiceImpl extends ServiceImpl<MeterConfigDao, MeterConfig> implements MeterConfigService {

    @Override
    public IPage<MeterConfig> selectMeterConfigPage(IPage<MeterConfig> page, MeterConfig meterConfig) {
        LambdaQueryWrapper<MeterConfig> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据分类名称模糊查询
        if (StringUtils.isNotBlank(meterConfig.getCategoryName())) {
            queryWrapper.like(MeterConfig::getCategoryName, meterConfig.getCategoryName());
        }
        
        // 根据仪表种类查询
        if (StringUtils.isNotBlank(meterConfig.getMeterType())) {
            queryWrapper.eq(MeterConfig::getMeterType, meterConfig.getMeterType());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(meterConfig.getStatus())) {
            queryWrapper.eq(MeterConfig::getStatus, meterConfig.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(MeterConfig::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}