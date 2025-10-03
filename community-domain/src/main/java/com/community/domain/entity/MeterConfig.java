package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 仪表配置表实体类
 */
@Data
@TableName("meter_config")
public class MeterConfig {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 分类名称(电表/水表/燃气表)
     */
    @TableField("category_name")
    private String categoryName;
    
    /**
     * 仪表种类
     */
    @TableField("meter_type")
    private String meterType;
    
    /**
     * 产品ID
     */
    @TableField("product_id")
    private String productId;
    
    /**
     * 配置参数(JSON格式)
     */
    @TableField("config_params")
    private String configParams;
    
    /**
     * 计量单位
     */
    @TableField("unit")
    private String unit;
    
    /**
     * 小数位数
     */
    @TableField("decimal_places")
    private Integer decimalPlaces;
    
    /**
     * 最大值
     */
    @TableField("max_value")
    private BigDecimal maxValue;
    
    /**
     * 最小值
     */
    @TableField("min_value")
    private BigDecimal minValue;
    
    /**
     * 收费标准
     */
    @TableField("charge_standard")
    private String chargeStandard;
    
    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;
    
    /**
     * 计算方式
     */
    @TableField("calculation_method")
    private String calculationMethod;
    
    /**
     * 通信协议
     */
    @TableField("comm_protocol")
    private String commProtocol;
    
    /**
     * 状态:启用/禁用
     */
    @TableField("status")
    private String status;
    
    /**
     * 是否默认配置
     */
    @TableField("is_default")
    private Integer isDefault;
    
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