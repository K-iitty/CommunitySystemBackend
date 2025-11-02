package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.MeterConfig;

public interface MeterConfigService extends IService<MeterConfig> {
    
    /**
     * 分页查询仪表配置信息
     * @param page 分页对象
     * @param meterConfig 查询条件
     * @return 仪表配置信息分页数据
     */
    IPage<MeterConfig> selectMeterConfigPage(IPage<MeterConfig> page, MeterConfig meterConfig);

    /**
     * 根据分类名称或仪表类型或状态分页查询仪表配置信息
     * @param page 分页对象
     * @param categoryName 分类名称
     * @param meterType 仪表类型
     * @param status 状态
     * @return 仪表配置信息分页数据
     */
    IPage<MeterConfig> selectMeterConfigPageByMultiple(IPage<MeterConfig> page, String categoryName, String meterType, String status);
}