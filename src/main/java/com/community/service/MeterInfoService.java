package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.MeterInfo;

public interface MeterInfoService extends IService<MeterInfo> {
    
    /**
     * 分页查询仪表信息
     * @param page 分页对象
     * @param meterInfo 查询条件
     * @return 仪表信息分页数据
     */
    IPage<MeterInfo> selectMeterInfoPage(IPage<MeterInfo> page, MeterInfo meterInfo);

    /**
     * 根据仪表编码或仪表名称或位置类型或仪表状态分页查询仪表信息
     * @param page 分页对象
     * @param meterCode 仪表编码
     * @param meterName 仪表名称
     * @param locationType 位置类型
     * @param meterStatus 仪表状态
     * @return 仪表信息分页数据
     */
    IPage<MeterInfo> selectMeterInfoPageByMultiple(IPage<MeterInfo> page, String meterCode, String meterName, String locationType, String meterStatus);
}