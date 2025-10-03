package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 角色表实体类
 */
@Data
@TableName("role")
public class Role {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    
    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;
    
    /**
     * 角色类型
     */
    @TableField("role_type")
    private String roleType;
    
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    
    /**
     * 成员数量
     */
    @TableField("member_count")
    private Integer memberCount;
    
    /**
     * 状态:启用/禁用
     */
    @TableField("status")
    private String status;
    
    /**
     * 权限配置(JSON)
     */
    @TableField("permissions")
    private String permissions;
    
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