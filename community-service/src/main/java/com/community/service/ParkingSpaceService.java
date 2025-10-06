package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.ParkingSpace;

public interface ParkingSpaceService extends IService<ParkingSpace> {
    
    /**
     * 分页查询车位信息
     * @param page 分页对象
     * @param parkingSpace 查询条件
     * @return 车位信息分页数据
     */
    IPage<ParkingSpace> selectParkingSpacePage(IPage<ParkingSpace> page, ParkingSpace parkingSpace);
}