package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.VehicleDao;
import com.community.domain.entity.Vehicle;
import com.community.service.VehicleService;
import com.community.common.service.OssService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleDao, Vehicle> implements VehicleService {
    
    @Autowired
    private VehicleDao vehicleDao;
    
    @Autowired
    private OssService ossService;
    
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

    @Override
    public IPage<Vehicle> selectVehiclePageByMultiple(IPage<Vehicle> page, String plateNumber, Long ownerId) {
        LambdaQueryWrapper<Vehicle> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据车牌号模糊查询
        if (StringUtils.isNotBlank(plateNumber)) {
            queryWrapper.like(Vehicle::getPlateNumber, plateNumber);
        }
        
        // 根据车主ID查询
        if (ownerId != null) {
            queryWrapper.eq(Vehicle::getOwnerId, ownerId);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Vehicle::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateDriverLicenseImage(Long id, MultipartFile file) {
        if (id == null || file == null || file.isEmpty()) {
            return false;
        }

        try {
            // 保存文件到OSS并获取URL
            String url = ossService.uploadFile(file, "vehicle/driver_license/");

            // 更新数据库
            Vehicle vehicle = this.getById(id);
            if (vehicle != null) {
                vehicle.setDriverLicenseImage(url);
                return this.updateById(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getDriverLicenseImage(Long id) {
        if (id == null) {
            return null;
        }

        Vehicle vehicle = this.getById(id);
        return vehicle != null ? vehicle.getDriverLicenseImage() : null;
    }

    @Override
    public boolean updateVehicleImages(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "vehicle/info/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            Vehicle vehicle = this.getById(id);
            if (vehicle != null) {
                String photos = String.join(",", photoUrls);
                vehicle.setVehicleImages(photos);
                return this.updateById(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getVehicleImages(Long id) {
        if (id == null) {
            return null;
        }

        Vehicle vehicle = this.getById(id);
        return vehicle != null ? vehicle.getVehicleImages() : null;
    }

    private String saveFile(MultipartFile file, String subPath) {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}