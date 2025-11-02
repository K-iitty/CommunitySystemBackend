package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 系统管理员表实体类
 */
@Data
@TableName("system_admin")
public class SystemAdmin {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 登录用户名
     */
    @TableField("username")
    private String username;
    
    /**
     * 登录密码
     */
    @TableField("password")
    private String password;
    
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    
    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;
    
    /**
     * 头像URL
     */
    @TableField("avatar")
    private String avatar;
    
    /**
     * 性别:男/女/保密
     */
    @TableField("gender")
    private String gender;
    
    /**
     * 出生日期
     */
    @TableField("birth_date")
    private LocalDate birthDate;
    
    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;
    
    /**
     * 角色类型:super_admin/admin/operator
     */
    @TableField("role_type")
    private String roleType;
    
    /**
     * 权限列表(JSON格式)
     */
    @TableField("permissions")
    private String permissions;
    
    /**
     * 所属部门
     */
    @TableField("department")
    private String department;
    
    /**
     * 职位
     */
    @TableField("position")
    private String position;
    
    /**
     * 微信号
     */
    @TableField("wechat")
    private String wechat;
    
    /**
     * QQ号
     */
    @TableField("qq")
    private String qq;
    
    /**
     * 联系地址
     */
    @TableField("address")
    private String address;
    
    /**
     * 紧急联系人
     */
    @TableField("emergency_contact")
    private String emergencyContact;
    
    /**
     * 紧急联系电话
     */
    @TableField("emergency_phone")
    private String emergencyPhone;
    
    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
    
    /**
     * 最后登录IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    
    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;
    
    /**
     * 连续登录失败次数
     */
    @TableField("failed_login_count")
    private Integer failedLoginCount;
    
    /**
     * 账号是否锁定
     */
    @TableField("account_locked")
    private Integer accountLocked;
    
    /**
     * 锁定截止时间
     */
    @TableField("lock_until_time")
    private LocalDateTime lockUntilTime;
    
    /**
     * 状态:正常/禁用/锁定
     */
    @TableField("status")
    private String status;
    
    /**
     * 邮箱是否验证
     */
    @TableField("email_verified")
    private Integer emailVerified;
    
    /**
     * 手机是否验证
     */
    @TableField("phone_verified")
    private Integer phoneVerified;
    
    /**
     * 创建人ID(自关联)
     */
    @TableField("created_by")
    private Long createdBy;
    
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