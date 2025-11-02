package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 抄表记录表实体类
 */
@Data
@TableName("meter_reading")
public class MeterReading {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 仪表ID
     */
    @TableField("meter_id")
    private Long meterId;
    
    /**
     * 上次读数
     */
    @TableField("previous_reading")
    private BigDecimal previousReading;
    
    /**
     * 本次读数
     */
    @TableField("current_reading")
    private BigDecimal currentReading;
    
    /**
     * 用量
     */
    @TableField("usage_amount")
    private BigDecimal usageAmount;
    
    /**
     * 计量单位
     */
    @TableField("unit")
    private String unit;
    
    /**
     * 抄表日期
     */
    @TableField("reading_date")
    private LocalDate readingDate;
    
    /**
     * 抄表时间
     */
    @TableField("reading_time")
    private LocalDateTime readingTime;
    
    /**
     * 抄表人ID
     */
    @TableField("reader_id")
    private Long readerId;
    
    /**
     * 抄表人姓名
     */
    @TableField("reader_name")
    private String readerName;
    
    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;
    
    /**
     * 抄表类型
     */
    @TableField("reading_type")
    private String readingType;
    
    /**
     * 抄表状态
     */
    @TableField("reading_status")
    private String readingStatus;
    
    /**
     * 异常原因
     */
    @TableField("abnormal_reason")
    private String abnormalReason;
    
    /**
     * 是否已处理
     */
    @TableField("processed")
    private Integer processed;
    
    /**
     * 抄表图片
     */
    @TableField("reading_image")
    private String readingImage;
    
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