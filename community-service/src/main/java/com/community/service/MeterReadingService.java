package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.MeterReading;

public interface MeterReadingService extends IService<MeterReading> {
    
    /**
     * 分页查询抄表记录
     * @param page 分页对象
     * @param meterReading 查询条件
     * @return 抄表记录分页数据
     */
    IPage<MeterReading> selectMeterReadingPage(IPage<MeterReading> page, MeterReading meterReading);
}