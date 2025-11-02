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

    /**
     * 根据仪表ID或抄表人或抄表类型或抄表状态分页查询抄表记录
     * @param page 分页对象
     * @param meterId 仪表ID
     * @param readerName 抄表人
     * @param readingType 抄表类型
     * @param readingStatus 抄表状态
     * @return 抄表记录分页数据
     */
    IPage<MeterReading> selectMeterReadingPageByMultiple(IPage<MeterReading> page, Long meterId, String readerName, String readingType, String readingStatus);
}