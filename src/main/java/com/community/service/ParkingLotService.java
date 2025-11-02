package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.ParkingLot;

public interface ParkingLotService extends IService<ParkingLot> {
    
    /**
     * 分页查询停车场信息
     * @param page 分页对象
     * @param parkingLot 查询条件
     * @return 停车场信息分页数据
     */
    IPage<ParkingLot> selectParkingLotPage(IPage<ParkingLot> page, ParkingLot parkingLot);

    /**
     * 根据停车场名称或停车场编码分页查询停车场信息
     * @param page 分页对象
     * @param lotName 停车场名称
     * @param lotCode 停车场编码
     * @return 停车场信息分页数据
     */
    IPage<ParkingLot> selectParkingLotPageByMultiple(IPage<ParkingLot> page, String lotName, String lotCode);
}