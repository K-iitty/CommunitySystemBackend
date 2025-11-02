package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 门禁设备表实体类
 */
@Data
@TableName("access_control_device")
public class AccessControlDevice {
    
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
     * 所属楼栋ID
     */
    @TableField("building_id")
    private Long buildingId;
    
    /**
     * 门禁名称
     */
    @TableField("device_name")
    private String deviceName;
    
    /**
     * 设备类型
     */
    @TableField("device_type")
    private String deviceType;
    
    /**
     * 配置码/设备编码
     */
    @TableField("device_code")
    private String deviceCode;
    
    /**
     * 设备序列号
     */
    @TableField("device_sn")
    private String deviceSn;
    
    /**
     * 安装位置
     */
    @TableField("install_location")
    private String installLocation;
    
    /**
     * 安装日期
     */
    @TableField("install_date")
    private LocalDate installDate;
    
    /**
     * 设备状态:启用/禁用/故障
     */
    @TableField("device_status")
    private String deviceStatus;
    
    /**
     * 在线状态:0-离线,1-在线
     */
    @TableField("online_status")
    private Integer onlineStatus;
    
    /**
     * 支持人脸识别
     */
    @TableField("support_face")
    private Integer supportFace;
    
    /**
     * 支持指纹识别
     */
    @TableField("support_fingerprint")
    private Integer supportFingerprint;
    
    /**
     * 支持刷卡
     */
    @TableField("support_card")
    private Integer supportCard;
    
    /**
     * 支持二维码
     */
    @TableField("support_qrcode")
    private Integer supportQrcode;
    
    /**
     * 欠费禁入
     */
    @TableField("arrears_ban_enabled")
    private Integer arrearsBanEnabled;
    
    /**
     * 访客审核
     */
    @TableField("visitor_approval_enabled")
    private Integer visitorApprovalEnabled;
    
    /**
     * 开门持续时间(秒)
     */
    @TableField("open_duration")
    private Integer openDuration;
    
    /**
     * 最后心跳时间
     */
    @TableField("last_heartbeat")
    private LocalDateTime lastHeartbeat;
    
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