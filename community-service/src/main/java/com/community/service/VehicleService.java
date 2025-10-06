package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Vehicle;

public interface VehicleService extends IService<Vehicle> {
    
    /**
     * 分页查询车辆信息
     * @param page 分页对象
     * @param vehicle 查询条件
     * @return 车辆信息分页数据
     */
    IPage<Vehicle> selectVehiclePage(IPage<Vehicle> page, Vehicle vehicle);
}