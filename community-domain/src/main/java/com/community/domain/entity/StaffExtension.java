package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 员工扩展信息表实体类
 */
@Data
@TableName("staff_extension")
public class StaffExtension {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 员工ID
     */
    @TableField("staff_id")
    private Long staffId;
    
    /**
     * 扩展字段键
     */
    @TableField("extension_key")
    private String extensionKey;
    
    /**
     * 扩展字段值
     */
    @TableField("extension_value")
    private String extensionValue;
    
    /**
     * 值类型
     */
    @TableField("value_type")
    private String valueType;
    
    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;
    
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