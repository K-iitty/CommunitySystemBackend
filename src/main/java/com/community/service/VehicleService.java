package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Vehicle;
import org.springframework.web.multipart.MultipartFile;

public interface VehicleService extends IService<Vehicle> {
    
    /**
     * 分页查询车辆信息
     * @param page 分页对象
     * @param vehicle 查询条件
     * @return 车辆信息分页数据
     */
    IPage<Vehicle> selectVehiclePage(IPage<Vehicle> page, Vehicle vehicle);

    /**
     * 根据车牌号或车主ID分页查询车辆信息
     * @param page 分页对象
     * @param plateNumber 车牌号
     * @param ownerId 车主ID
     * @return 车辆信息分页数据
     */
    IPage<Vehicle> selectVehiclePageByMultiple(IPage<Vehicle> page, String plateNumber, Long ownerId);
    
    /**
     * 更新驾照照片
     * @param id 车辆ID
     * @param file 驾照照片文件
     * @return 是否成功
     */
    boolean updateDriverLicenseImage(Long id, MultipartFile file);
    
    /**
     * 获取驾照照片
     * @param id 车辆ID
     * @return 驾照照片URL
     */
    String getDriverLicenseImage(Long id);
    
    /**
     * 更新车辆信息照片
     * @param id 车辆ID
     * @param files 车辆信息照片文件
     * @return 是否成功
     */
    boolean updateVehicleImages(Long id, MultipartFile[] files);
    
    /**
     * 获取车辆信息照片
     * @param id 车辆ID
     * @return 车辆信息照片URL列表
     */
    String getVehicleImages(Long id);
}