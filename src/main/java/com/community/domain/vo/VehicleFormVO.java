package com.community.domain.vo;

import com.community.domain.entity.Vehicle;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 车辆表单VO类 - 用于接收multipart/form-data请求
 */
@Data
public class VehicleFormVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 车主ID
     */
    private Long ownerId;
    
    /**
     * 车牌号
     */
    private String plateNumber;
    
    /**
     * 车辆类型
     */
    private String vehicleType;
    
    /**
     * 品牌
     */
    private String brand;
    
    /**
     * 型号
     */
    private String model;
    
    /**
     * 颜色
     */
    private String color;
    
    /**
     * 固定车位ID
     */
    private Long fixedSpaceId;
    
    /**
     * 行驶证号
     */
    private String vehicleLicenseNo;
    
    /**
     * 发动机号
     */
    private String engineNo;
    
    /**
     * 状态:正常/冻结/黑名单
     */
    private String status;
    
    /**
     * 登记日期
     */
    private LocalDate registerDate;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 驾照照片文件
     */
    private MultipartFile driverLicenseImageFile;
    
    /**
     * 车辆信息照片文件数组
     */
    private MultipartFile[] vehicleImageFiles;
    
    /**
     * 转换为Vehicle实体对象
     * @return Vehicle实体对象
     */
    public Vehicle toVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(this.id);
        vehicle.setOwnerId(this.ownerId);
        vehicle.setPlateNumber(this.plateNumber);
        vehicle.setVehicleType(this.vehicleType);
        vehicle.setBrand(this.brand);
        vehicle.setModel(this.model);
        vehicle.setColor(this.color);
        vehicle.setFixedSpaceId(this.fixedSpaceId);
        vehicle.setVehicleLicenseNo(this.vehicleLicenseNo);
        vehicle.setEngineNo(this.engineNo);
        vehicle.setStatus(this.status);
        vehicle.setRegisterDate(this.registerDate);
        vehicle.setRemark(this.remark);
        return vehicle;
    }
}
