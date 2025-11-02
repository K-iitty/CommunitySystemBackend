package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 业主问题表实体类
 */
@Data
@TableName("owner_issue")
public class OwnerIssue {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 所属社区ID
     */
    @TableField("community_id")
    private Long communityId;
    
    /**
     * 业主ID
     */
    @TableField("owner_id")
    private Long ownerId;
    
    /**
     * 关联房屋ID
     */
    @TableField("house_id")
    private Long houseId;
    
    /**
     * 问题标题
     */
    @TableField("issue_title")
    private String issueTitle;
    
    /**
     * 问题详情描述
     */
    @TableField("issue_content")
    private String issueContent;
    
    /**
     * 问题类型:报修服务/投诉建议/咨询服务/其他
     */
    @TableField("issue_type")
    private String issueType;
    
    /**
     * 问题子类型
     */
    @TableField("sub_type")
    private String subType;
    
    /**
     * 位置类型:室内/公共区域/停车场/其他
     */
    @TableField("location_type")
    private String locationType;
    
    /**
     * 具体位置
     */
    @TableField("specific_location")
    private String specificLocation;
    
    /**
     * 联系人姓名
     */
    @TableField("contact_name")
    private String contactName;
    
    /**
     * 联系电话
     */
    @TableField("contact_phone")
    private String contactPhone;
    
    /**
     * 最佳联系时间
     */
    @TableField("best_contact_time")
    private String bestContactTime;
    
    /**
     * 紧急程度:紧急/高/一般/低
     */
    @TableField("urgency_level")
    private String urgencyLevel;
    
    /**
     * 期望解决时间
     */
    @TableField("expected_resolve_time")
    private LocalDateTime expectedResolveTime;
    
    /**
     * 指派处理人员ID
     */
    @TableField("assigned_staff_id")
    private Long assignedStaffId;
    
    /**
     * 指派部门ID
     */
    @TableField("assigned_department_id")
    private Long assignedDepartmentId;
    
    /**
     * 指派时间
     */
    @TableField("assigned_time")
    private LocalDateTime assignedTime;
    
    /**
     * 指派备注
     */
    @TableField("assigned_remark")
    private String assignedRemark;
    
    /**
     * 实际处理人员ID
     */
    @TableField("processor_staff_id")
    private Long processorStaffId;
    
    /**
     * 处理方案
     */
    @TableField("process_plan")
    private String processPlan;
    
    /**
     * 处理结果
     */
    @TableField("process_result")
    private String processResult;
    
    /**
     * 处理开始时间
     */
    @TableField("process_start_time")
    private LocalDateTime processStartTime;
    
    /**
     * 处理完成时间
     */
    @TableField("process_end_time")
    private LocalDateTime processEndTime;
    
    /**
     * 实际耗时(小时)
     */
    @TableField("actual_hours")
    private BigDecimal actualHours;
    
    /**
     * 是否产生费用
     */
    @TableField("has_cost")
    private Integer hasCost;
    
    /**
     * 材料费用
     */
    @TableField("material_cost")
    private BigDecimal materialCost;
    
    /**
     * 人工费用
     */
    @TableField("labor_cost")
    private BigDecimal laborCost;
    
    /**
     * 总费用
     */
    @TableField("total_cost")
    private BigDecimal totalCost;
    
    /**
     * 费用支付状态
     */
    @TableField("cost_payment_status")
    private String costPaymentStatus;
    
    /**
     * 问题状态
     */
    @TableField("issue_status")
    private String issueStatus;
    
    /**
     * 工单状态
     */
    @TableField("work_status")
    private String workStatus;
    
    /**
     * 满意度评分(1-5分)
     */
    @TableField("satisfaction_level")
    private Integer satisfactionLevel;
    
    /**
     * 满意度反馈
     */
    @TableField("satisfaction_feedback")
    private String satisfactionFeedback;
    
    /**
     * 问题图片(JSON数组)
     */
    @TableField("issue_images")
    private String issueImages;
    
    /**
     * 处理过程图片(JSON数组)
     */
    @TableField("process_images")
    private String processImages;
    
    /**
     * 处理结果图片(JSON数组)
     */
    @TableField("result_images")
    private String resultImages;
    
    /**
     * 问题描述图片（JSON数组格式，0-多张）
     */
    @TableField("additional_images")
    private String additionalImages;
    
    /**
     * 上报时间
     */
    @TableField("reported_time")
    private LocalDateTime reportedTime;
    
    /**
     * 首次响应时间
     */
    @TableField("response_time")
    private LocalDateTime responseTime;
    
    /**
     * 预计完成时间
     */
    @TableField("estimated_complete_time")
    private LocalDateTime estimatedCompleteTime;
    
    /**
     * 实际完成时间
     */
    @TableField("actual_complete_time")
    private LocalDateTime actualCompleteTime;
    
    /**
     * 关闭人ID
     */
    @TableField("closed_by")
    private Long closedBy;
    
    /**
     * 关闭时间
     */
    @TableField("closed_time")
    private LocalDateTime closedTime;
    
    /**
     * 关闭原因
     */
    @TableField("close_reason")
    private String closeReason;
    
    /**
     * 是否已评价
     */
    @TableField("is_evaluated")
    private Integer isEvaluated;
    
    /**
     * 评价时间
     */
    @TableField("evaluation_time")
    private LocalDateTime evaluationTime;
    
    /**
     * 评价内容
     */
    @TableField("evaluation_content")
    private String evaluationContent;
    
    /**
     * 内部备注
     */
    @TableField("internal_remark")
    private String internalRemark;
    
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
    
    /**
     * 获取问题图片URL列表
     * @return 问题图片URL列表
     */
    public List<String> getIssueImagesList() {
        if (issueImages == null || issueImages.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(issueImages.split(","));
    }
    
    /**
     * 获取处理过程图片URL列表
     * @return 处理过程图片URL列表
     */
    public List<String> getProcessImagesList() {
        if (processImages == null || processImages.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(processImages.split(","));
    }
    
    /**
     * 获取处理结果图片URL列表
     * @return 处理结果图片URL列表
     */
    public List<String> getResultImagesList() {
        if (resultImages == null || resultImages.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(resultImages.split(","));
    }
    
    /**
     * 获取附加图片URL列表
     * @return 附加图片URL列表
     */
    public List<String> getAdditionalImagesList() {
        if (additionalImages == null || additionalImages.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(additionalImages.split(","));
    }
    
    /**
     * 判断是否有问题图片
     * @return 是否有问题图片
     */
    public boolean hasIssueImages() {
        return issueImages != null && !issueImages.isEmpty();
    }
    
    /**
     * 判断是否有处理过程图片
     * @return 是否有处理过程图片
     */
    public boolean hasProcessImages() {
        return processImages != null && !processImages.isEmpty();
    }
    
    /**
     * 判断是否有处理结果图片
     * @return 是否有处理结果图片
     */
    public boolean hasResultImages() {
        return resultImages != null && !resultImages.isEmpty();
    }
    
    /**
     * 判断是否有附加图片
     * @return 是否有附加图片
     */
    public boolean hasAdditionalImages() {
        return additionalImages != null && !additionalImages.isEmpty();
    }
}