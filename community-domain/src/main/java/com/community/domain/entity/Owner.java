package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 业主表实体类
 */
@Data
@TableName("owner")
public class Owner {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    
    /**
     * 姓名
     */
    @TableField("name")
    private String name;
    
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    
    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;
    
    /**
     * 性别
     */
    @TableField("gender")
    private String gender;
    
    /**
     * 出生日期
     */
    @TableField("birth_date")
    private LocalDate birthDate;
    
    /**
     * 业主类型:业主/家属/租客
     */
    @TableField("owner_type")
    private String ownerType;
    
    /**
     * 政治面貌
     */
    @TableField("political_status")
    private String politicalStatus;
    
    /**
     * 婚姻状况
     */
    @TableField("marital_status")
    private String maritalStatus;
    
    /**
     * 民族
     */
    @TableField("nationality")
    private String nationality;
    
    /**
     * 户口类型
     */
    @TableField("household_type")
    private String householdType;
    
    /**
     * 户籍所在地
     */
    @TableField("census_register")
    private String censusRegister;
    
    /**
     * 暂住证号
     */
    @TableField("temporary_resident_no")
    private String temporaryResidentNo;
    
    /**
     * 现住地址
     */
    @TableField("current_address")
    private String currentAddress;
    
    /**
     * 紧急联系人姓名
     */
    @TableField("emergency_contact_name")
    private String emergencyContactName;
    
    /**
     * 紧急联系人关系
     */
    @TableField("emergency_contact_relation")
    private String emergencyContactRelation;
    
    /**
     * 紧急联系人手机号码
     */
    @TableField("emergency_contact_phone")
    private String emergencyContactPhone;
    
    /**
     * 居住类型
     */
    @TableField("residence_type")
    private String residenceType;
    
    /**
     * 入住日期
     */
    @TableField("move_in_date")
    private LocalDate moveInDate;
    
    /**
     * 迁出日期
     */
    @TableField("move_out_date")
    private LocalDate moveOutDate;
    
    /**
     * 状态:正常/冻结/迁出
     */
    @TableField("status")
    private String status;
    
    /**
     * 认证状态
     */
    @TableField("verify_status")
    private String verifyStatus;
    
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