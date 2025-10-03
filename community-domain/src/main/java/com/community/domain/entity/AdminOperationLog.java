package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理员操作日志表实体类
 */
@Data
@TableName("admin_operation_log")
public class AdminOperationLog {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 管理员ID
     */
    @TableField("admin_id")
    private Long adminId;
    
    /**
     * 操作时间
     */
    @TableField("operation_time")
    private LocalDateTime operationTime;
    
    /**
     * 操作模块
     */
    @TableField("operation_module")
    private String operationModule;
    
    /**
     * 操作类型
     */
    @TableField("operation_type")
    private String operationType;
    
    /**
     * 操作描述
     */
    @TableField("operation_description")
    private String operationDescription;
    
    /**
     * 请求URL
     */
    @TableField("request_url")
    private String requestUrl;
    
    /**
     * 请求方法
     */
    @TableField("request_method")
    private String requestMethod;
    
    /**
     * 请求IP
     */
    @TableField("request_ip")
    private String requestIp;
    
    /**
     * 请求参数
     */
    @TableField("request_params")
    private String requestParams;
    
    /**
     * 响应代码
     */
    @TableField("response_code")
    private Integer responseCode;
    
    /**
     * 操作状态
     */
    @TableField("operation_status")
    private String operationStatus;
}