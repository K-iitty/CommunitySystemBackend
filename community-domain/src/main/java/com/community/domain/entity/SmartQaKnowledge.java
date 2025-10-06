package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 智能问答知识库表实体类
 */
@Data
@TableName("smart_qa_knowledge")
public class SmartQaKnowledge {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 文档分类
     */
    @TableField("category")
    private String category;
    
    /**
     * 文档标题
     */
    @TableField("title")
    private String title;
    
    /**
     * 文档描述/摘要
     */
    @TableField("description")
    private String description;
    
    /**
     * 文档存储路径
     */
    @TableField("file_path")
    private String filePath;
    
    /**
     * 文档文件名
     */
    @TableField("file_name")
    private String fileName;
    
    /**
     * 文档类型(pdf/doc/txt等)
     */
    @TableField("file_type")
    private String fileType;
    
    /**
     * 文档大小(字节)
     */
    @TableField("file_size")
    private Long fileSize;
    
    /**
     * 标签，逗号分隔
     */
    @TableField("tags")
    private String tags;
    
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
     * 查看次数
     */
    @TableField("view_count")
    private Integer viewCount;
    
    /**
     * 下载次数
     */
    @TableField("download_count")
    private Integer downloadCount;
    
    /**
     * 上传人ID
     */
    @TableField("created_by")
    private Long createdBy;
    
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