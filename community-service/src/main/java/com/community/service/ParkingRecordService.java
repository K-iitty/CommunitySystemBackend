package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.ParkingRecord;

public interface ParkingRecordService extends IService<ParkingRecord> {
    
    /**
     * 分页查询停车记录信息
     * @param page 分页对象
     * @param parkingRecord 查询条件
     * @return 停车记录信息分页数据
     */
    IPage<ParkingRecord> selectParkingRecordPage(IPage<ParkingRecord> page, ParkingRecord parkingRecord);
}