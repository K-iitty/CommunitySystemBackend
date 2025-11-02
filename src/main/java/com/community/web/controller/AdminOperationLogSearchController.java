package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.AdminOperationLog;
import com.community.service.AdminOperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminOperationLog")
@Tag(name = "管理员操作日志管理", description = "管理员操作日志相关接口")
@ApiSupport(order = 9, author = "社区管理系统开发团队")
public class AdminOperationLogSearchController {

    @Autowired
    private AdminOperationLogService adminOperationLogService;

    /**
     * 根据管理员ID或操作类型或操作描述分页查询操作日志信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param adminId 管理员ID
     * @param operationType 操作类型
     * @param operationDescription 操作描述
     * @return 管理员操作日志信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据管理员ID或操作类型或操作描述分页查询操作日志信息", description = "根据管理员ID或操作类型或操作描述分页查询操作日志信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result search(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                         @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                         @Parameter(description = "管理员ID") @RequestParam(required = false) Long adminId,
                         @Parameter(description = "操作类型") @RequestParam(required = false) String operationType,
                         @Parameter(description = "操作描述") @RequestParam(required = false) String operationDescription) {
        AdminOperationLog adminOperationLog = new AdminOperationLog();
        adminOperationLog.setAdminId(adminId);
        adminOperationLog.setOperationType(operationType);
        adminOperationLog.setOperationDescription(operationDescription);
        
        IPage<AdminOperationLog> page = new Page<>(pageNum, pageSize);
        IPage<AdminOperationLog> result = adminOperationLogService.selectAdminOperationLogPage(page, adminOperationLog);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
}