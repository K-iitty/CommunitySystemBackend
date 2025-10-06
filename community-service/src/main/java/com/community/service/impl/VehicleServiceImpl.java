package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.VehicleDao;
import com.community.domain.entity.Vehicle;
import com.community.service.VehicleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleDao, Vehicle> implements VehicleService {

    @Override
    public IPage<Vehicle> selectVehiclePage(IPage<Vehicle> page, Vehicle vehicle) {
        LambdaQueryWrapper<Vehicle> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据车主ID查询
        if (vehicle.getOwnerId() != null) {
            queryWrapper.eq(Vehicle::getOwnerId, vehicle.getOwnerId());
        }
        
        // 根据车牌号模糊查询
        if (StringUtils.isNotBlank(vehicle.getPlateNumber())) {
            queryWrapper.like(Vehicle::getPlateNumber, vehicle.getPlateNumber());
        }
        
        // 根据车辆品牌查询
        if (StringUtils.isNotBlank(vehicle.getBrand())) {
            queryWrapper.like(Vehicle::getBrand, vehicle.getBrand());
        }
        
        // 根据车辆类型查询
        if (StringUtils.isNotBlank(vehicle.getVehicleType())) {
            queryWrapper.eq(Vehicle::getVehicleType, vehicle.getVehicleType());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Vehicle::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}