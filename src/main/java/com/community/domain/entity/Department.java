package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 部门表实体类
 */
@Data
@TableName("department")
public class Department {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 部门名称
     */
    @TableField("department_name")
    private String departmentName;
    
    /**
     * 父部门ID
     */
    @TableField("parent_id")
    private Long parentId;
    
    /**
     * 部门编码
     */
    @TableField("department_code")
    private String departmentCode;
    
    /**
     * 部门层级
     */
    @TableField("department_level")
    private Integer departmentLevel;
    
    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;
    
    /**
     * 状态:启用/禁用
     */
    @TableField("status")
    private String status;
    
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    
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