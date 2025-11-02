package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 问题跟进记录表实体类
 */
@Data
@TableName("issue_follow_up")
public class IssueFollowUp {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 问题ID
     */
    @TableField("issue_id")
    private Long issueId;
    
    /**
     * 跟进类型:业主补充/处理进展/状态变更/费用确认/满意度评价
     */
    @TableField("follow_up_type")
    private String followUpType;
    
    /**
     * 跟进内容
     */
    @TableField("follow_up_content")
    private String followUpContent;
    
    /**
     * 操作人类型:owner/staff/system
     */
    @TableField("operator_type")
    private String operatorType;
    
    /**
     * 操作人ID
     */
    @TableField("operator_id")
    private Long operatorId;
    
    /**
     * 操作人姓名
     */
    @TableField("operator_name")
    private String operatorName;
    
    /**
     * 附件信息(JSON格式)
     */
    @TableField("attachments")
    private String attachments;
    
    /**
     * 内部备注
     */
    @TableField("internal_note")
    private String internalNote;
    
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}