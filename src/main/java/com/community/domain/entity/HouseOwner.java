package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 业主房屋关联表实体类
 */
@Data
@TableName("house_owner")
public class HouseOwner {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 房屋ID
     */
    @TableField("house_id")
    private Long houseId;
    
    /**
     * 业主ID
     */
    @TableField("owner_id")
    private Long ownerId;
    
    /**
     * 关系类型:业主/家属/租客/其他
     */
    @TableField("relationship")
    private String relationship;
    
    /**
     * 是否为主要联系人
     */
    @TableField("is_primary")
    private Integer isPrimary;
    
    /**
     * 是否已验证
     */
    @TableField("is_verified")
    private Integer isVerified;
    
    /**
     * 关系开始日期
     */
    @TableField("start_date")
    private LocalDate startDate;
    
    /**
     * 关系结束日期
     */
    @TableField("end_date")
    private LocalDate endDate;
    
    /**
     * 租金金额
     */
    @TableField("rent_amount")
    private BigDecimal rentAmount;
    
    /**
     * 租金支付周期
     */
    @TableField("rent_pay_cycle")
    private String rentPayCycle;
    
    /**
     * 押金金额
     */
    @TableField("deposit_amount")
    private BigDecimal depositAmount;
    
    /**
     * 状态:正常/到期/终止
     */
    @TableField("status")
    private String status;
    
    /**
     * 验证人ID
     */
    @TableField("verified_by")
    private Long verifiedBy;
    
    /**
     * 验证时间
     */
    @TableField("verified_at")
    private LocalDateTime verifiedAt;
    
    /**
     * 验证备注
     */
    @TableField("verify_remark")
    private String verifyRemark;
    
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