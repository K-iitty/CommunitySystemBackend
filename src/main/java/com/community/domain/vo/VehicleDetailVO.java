package com.community.domain.vo;

import com.community.domain.entity.Vehicle;
import com.community.domain.entity.Owner;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 车辆详细信息VO类
 */
@Data
public class VehicleDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 车辆信息
     */
    private Vehicle vehicle;
    
    /**
     * 业主信息
     */
    private Owner owner;
    
    /**
     * 获取车辆信息照片URL列表
     * @return 车辆信息照片URL列表
     */
    public List<String> getVehicleImagesList() {
        if (vehicle != null) {
            return vehicle.getVehicleImagesList();
        }
        return List.of();
    }
    
    /**
     * 判断车辆是否有驾照照片
     * @return 是否有驾照照片
     */
    public boolean hasDriverLicenseImage() {
        if (vehicle != null) {
            return vehicle.hasDriverLicenseImage();
        }
        return false;
    }
    
    /**
     * 判断车辆是否有信息照片
     * @return 是否有信息照片
     */
    public boolean hasVehicleImages() {
        if (vehicle != null) {
            return vehicle.hasVehicleImages();
        }
        return false;
    }
}