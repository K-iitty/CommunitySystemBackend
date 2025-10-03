package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 车辆表实体类
 */
@Data
@TableName("vehicle")
public class Vehicle {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 车主ID
     */
    @TableField("owner_id")
    private Long ownerId;
    
    /**
     * 车牌号
     */
    @TableField("plate_number")
    private String plateNumber;
    
    /**
     * 车辆类型
     */
    @TableField("vehicle_type")
    private String vehicleType;
    
    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;
    
    /**
     * 型号
     */
    @TableField("model")
    private String model;
    
    /**
     * 颜色
     */
    @TableField("color")
    private String color;
    
    /**
     * 固定车位ID
     */
    @TableField("fixed_space_id")
    private Long fixedSpaceId;
    
    /**
     * 行驶证号
     */
    @TableField("vehicle_license_no")
    private String vehicleLicenseNo;
    
    /**
     * 发动机号
     */
    @TableField("engine_no")
    private String engineNo;
    
    /**
     * 状态:正常/冻结/黑名单
     */
    @TableField("status")
    private String status;
    
    /**
     * 登记日期
     */
    @TableField("register_date")
    private LocalDate registerDate;
    
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}