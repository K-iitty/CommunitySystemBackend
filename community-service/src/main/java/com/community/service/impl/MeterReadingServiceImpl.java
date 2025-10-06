package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.MeterReadingDao;
import com.community.domain.entity.MeterReading;
import com.community.service.MeterReadingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MeterReadingServiceImpl extends ServiceImpl<MeterReadingDao, MeterReading> implements MeterReadingService {

    @Override
    public IPage<MeterReading> selectMeterReadingPage(IPage<MeterReading> page, MeterReading meterReading) {
        LambdaQueryWrapper<MeterReading> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据仪表ID查询
        if (meterReading.getMeterId() != null) {
            queryWrapper.eq(MeterReading::getMeterId, meterReading.getMeterId());
        }
        
        // 根据抄表人ID查询
        if (meterReading.getReaderId() != null) {
            queryWrapper.eq(MeterReading::getReaderId, meterReading.getReaderId());
        }
        
        // 根据分类名称模糊查询
        if (StringUtils.isNotBlank(meterReading.getCategoryName())) {
            queryWrapper.like(MeterReading::getCategoryName, meterReading.getCategoryName());
        }
        
        // 根据抄表类型查询
        if (StringUtils.isNotBlank(meterReading.getReadingType())) {
            queryWrapper.eq(MeterReading::getReadingType, meterReading.getReadingType());
        }
        
        // 根据抄表状态查询
        if (StringUtils.isNotBlank(meterReading.getReadingStatus())) {
            queryWrapper.eq(MeterReading::getReadingStatus, meterReading.getReadingStatus());
        }
        
        // 默认按抄表时间倒序排列
        queryWrapper.orderByDesc(MeterReading::getReadingTime);
        
        return this.page(page, queryWrapper);
    }
}