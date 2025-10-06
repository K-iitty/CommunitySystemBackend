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
}