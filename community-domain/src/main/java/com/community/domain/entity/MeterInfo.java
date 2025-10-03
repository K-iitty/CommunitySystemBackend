package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 仪表信息表实体类
 */
@Data
@TableName("meter_info")
public class MeterInfo {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 所属小区ID
     */
    @TableField("community_id")
    private Long communityId;
    
    /**
     * 房间ID
     */
    @TableField("house_id")
    private Long houseId;
    
    /**
     * 楼栋ID
     */
    @TableField("building_id")
    private Long buildingId;
    
    /**
     * 仪表配置ID
     */
    @TableField("config_id")
    private Long configId;
    
    /**
     * 仪表名称
     */
    @TableField("meter_name")
    private String meterName;
    
    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;
    
    /**
     * 仪表种类
     */
    @TableField("meter_type")
    private String meterType;
    
    /**
     * 仪表编码
     */
    @TableField("meter_code")
    private String meterCode;
    
    /**
     * 仪表序列号
     */
    @TableField("meter_sn")
    private String meterSn;
    
    /**
     * 仪表位置
     */
    @TableField("location_type")
    private String locationType;
    
    /**
     * 安装位置描述
     */
    @TableField("install_location")
    private String installLocation;
    
    /**
     * 安装日期
     */
    @TableField("install_date")
    private LocalDate installDate;
    
    /**
     * 起始读数
     */
    @TableField("initial_reading")
    private BigDecimal initialReading;
    
    /**
     * 当前读数
     */
    @TableField("current_reading")
    private BigDecimal currentReading;
    
    /**
     * 最大读数
     */
    @TableField("max_reading")
    private BigDecimal maxReading;
    
    /**
     * 总用量
     */
    @TableField("total_usage")
    private BigDecimal totalUsage;
    
    /**
     * 计量单位
     */
    @TableField("unit")
    private String unit;
    
    /**
     * 收费标准
     */
    @TableField("charge_standard")
    private String chargeStandard;
    
    /**
     * 在线状态
     */
    @TableField("online_status")
    private Integer onlineStatus;
    
    /**
     * 通电状态
     */
    @TableField("power_status")
    private Integer powerStatus;
    
    /**
     * 仪表状态
     */
    @TableField("meter_status")
    private String meterStatus;
    
    /**
     * 通信地址
     */
    @TableField("comm_address")
    private String commAddress;
    
    /**
     * 最后通信时间
     */
    @TableField("last_comm_time")
    private LocalDateTime lastCommTime;
    
    /**
     * 备注信息
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