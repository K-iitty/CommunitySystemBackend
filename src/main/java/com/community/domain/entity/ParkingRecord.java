package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 停车记录表实体类
 */
@Data
@TableName("parking_record")
public class ParkingRecord {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 车辆ID
     */
    @TableField("vehicle_id")
    private Long vehicleId;
    
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
     * 车主ID
     */
    @TableField("owner_id")
    private Long ownerId;
    
    /**
     * 车主姓名
     */
    @TableField("owner_name")
    private String ownerName;
    
    /**
     * 车主手机号
     */
    @TableField("owner_phone")
    private String ownerPhone;
    
    /**
     * 停车场ID
     */
    @TableField("parking_lot_id")
    private Long parkingLotId;
    
    /**
     * 车位ID
     */
    @TableField("parking_space_id")
    private Long parkingSpaceId;
    
    /**
     * 出入闸机号
     */
    @TableField("gate_no")
    private String gateNo;
    
    /**
     * 入场时间
     */
    @TableField("entry_time")
    private LocalDateTime entryTime;
    
    /**
     * 出场时间
     */
    @TableField("exit_time")
    private LocalDateTime exitTime;
    
    /**
     * 支付时间
     */
    @TableField("pay_time")
    private LocalDateTime payTime;
    
    /**
     * 停车时长(分钟)
     */
    @TableField("duration_minutes")
    private Integer durationMinutes;
    
    /**
     * 停车费用
     */
    @TableField("parking_fee")
    private BigDecimal parkingFee;
    
    /**
     * 实收费用
     */
    @TableField("actual_fee")
    private BigDecimal actualFee;
    
    /**
     * 支付方式
     */
    @TableField("payment_method")
    private String paymentMethod;
    
    /**
     * 支付状态
     */
    @TableField("payment_status")
    private String paymentStatus;
    
    /**
     * 交易流水号
     */
    @TableField("transaction_no")
    private String transactionNo;
    
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
}