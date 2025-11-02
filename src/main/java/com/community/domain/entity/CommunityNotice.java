package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 社区公告表实体类
 */
@Data
@TableName("community_notice")
public class CommunityNotice {
    
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
     * 发布人ID(关联system_admin)
     */
    @TableField("created_by")
    private Long createdBy;
    
    /**
     * 公告标题
     */
    @TableField("title")
    private String title;
    
    /**
     * 公告内容
     */
    @TableField("content")
    private String content;
    
    /**
     * 公告类型:社区公告/活动公告/紧急通知/温馨提示
     */
    @TableField("notice_type")
    private String noticeType;
    
    /**
     * 活动日期
     */
    @TableField("activity_date")
    private LocalDate activityDate;
    
    /**
     * 活动时间
     */
    @TableField("activity_time")
    private String activityTime;
    
    /**
     * 活动地点
     */
    @TableField("activity_location")
    private String activityLocation;
    
    /**
     * 活动组织者
     */
    @TableField("activity_organizer")
    private String activityOrganizer;
    
    /**
     * 活动联系人
     */
    @TableField("activity_contact")
    private String activityContact;
    
    /**
     * 活动联系电话
     */
    @TableField("activity_contact_phone")
    private String activityContactPhone;
    
    /**
     * 目标受众
     */
    @TableField("target_audience")
    private String targetAudience;
    
    /**
     * 目标楼栋(JSON数组)
     */
    @TableField("target_buildings")
    private String targetBuildings;
    
    /**
     * 目标业主类型
     */
    @TableField("target_owner_types")
    private String targetOwnerTypes;
    
    /**
     * 发布时间
     */
    @TableField("publish_time")
    private LocalDateTime publishTime;
    
    /**
     * 生效时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;
    
    /**
     * 失效时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;
    
    /**
     * 是否紧急
     */
    @TableField("is_urgent")
    private Integer isUrgent;
    
    /**
     * 是否置顶
     */
    @TableField("is_top")
    private Integer isTop;
    
    /**
     * 置顶结束时间
     */
    @TableField("top_end_time")
    private LocalDateTime topEndTime;
    
    /**
     * 阅读次数
     */
    @TableField("read_count")
    private Integer readCount;
    
    /**
     * 附件信息(JSON格式)
     */
    @TableField("attachments")
    private String attachments;
    
    /**
     * 状态:草稿/已发布/已撤回/已过期
     */
    @TableField("status")
    private String status;
    
    /**
     * 审核状态
     */
    @TableField("approval_status")
    private String approvalStatus;
    
    /**
     * 审核人ID
     */
    @TableField("approved_by")
    private Long approvedBy;
    
    /**
     * 审核时间
     */
    @TableField("approved_at")
    private LocalDateTime approvedAt;
    
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    
    /**
     * 公告图片（JSON数组格式，0-多张）
     */
    @TableField("notice_images")
    private String noticeImages;
    
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
     * 获取公告图片URL列表
     * @return 公告图片URL列表
     */
    public List<String> getNoticeImagesList() {
        if (noticeImages == null || noticeImages.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(noticeImages.split(","));
    }
    
    /**
     * 判断是否有公告图片
     * @return 是否有公告图片
     */
    public boolean hasNoticeImages() {
        return noticeImages != null && !noticeImages.isEmpty();
    }
}