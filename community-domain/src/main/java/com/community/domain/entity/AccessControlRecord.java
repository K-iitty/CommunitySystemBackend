package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 门禁记录表（整合版）实体类
 */
@Data
@TableName("access_control_record")
public class AccessControlRecord {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 人员ID(业主/员工/访客)
     */
    @TableField("person_id")
    private Long personId;
    
    /**
     * 人员类型:owner/staff/visitor
     */
    @TableField("person_type")
    private String personType;
    
    /**
     * 姓名
     */
    @TableField("person_name")
    private String personName;
    
    /**
     * 手机号
     */
    @TableField("person_phone")
    private String personPhone;
    
    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;
    
    /**
     * 门禁设备ID
     */
    @TableField("device_id")
    private Long deviceId;
    
    /**
     * 所属小区ID
     */
    @TableField("community_id")
    private Long communityId;
    
    /**
     * 出入类型:entry/exit
     */
    @TableField("access_type")
    private String accessType;
    
    /**
     * 出入方式
     */
    @TableField("access_method")
    private String accessMethod;
    
    /**
     * 权限类型
     */
    @TableField("permission_type")
    private String permissionType;
    
    /**
     * 权限开始时间
     */
    @TableField("valid_start_time")
    private LocalDateTime validStartTime;
    
    /**
     * 权限结束时间
     */
    @TableField("valid_end_time")
    private LocalDateTime validEndTime;
    
    /**
     * 出入时间
     */
    @TableField("access_time")
    private LocalDateTime accessTime;
    
    /**
     * 出入闸机名称
     */
    @TableField("gate_name")
    private String gateName;
    
    /**
     * 位置信息
     */
    @TableField("location_info")
    private String locationInfo;
    
    /**
     * 验证结果
     */
    @TableField("verify_result")
    private String verifyResult;
    
    /**
     * 失败原因
     */
    @TableField("fail_reason")
    private String failReason;
    
    /**
     * 抓拍图片
     */
    @TableField("capture_image")
    private String captureImage;
    
    /**
     * 审批人ID
     */
    @TableField("approved_by")
    private Long approvedBy;
    
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