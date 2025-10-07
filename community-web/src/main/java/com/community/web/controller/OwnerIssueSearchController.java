package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.OwnerIssue;
import com.community.service.OwnerIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ownerIssue")
@Tag(name = "业主问题管理", description = "业主问题相关接口")
@ApiSupport(order = 24, author = "社区管理系统开发团队")
public class OwnerIssueSearchController {

    @Autowired
    private OwnerIssueService ownerIssueService;

    /**
     * 根据业主ID或问题类型或状态分页查询业主问题信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param ownerId 业主ID
     * @param issueType 问题类型
     * @param issueStatus 状态
     * @return 业主问题信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据业主ID或问题类型或状态分页查询业主问题信息", description = "根据业主ID或问题类型或状态分页查询业主问题信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result search(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                         @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                         @Parameter(description = "业主ID") @RequestParam(required = false) Long ownerId,
                         @Parameter(description = "问题类型") @RequestParam(required = false) String issueType,
                         @Parameter(description = "状态") @RequestParam(required = false) String issueStatus) {
        OwnerIssue ownerIssue = new OwnerIssue();
        ownerIssue.setOwnerId(ownerId);
        ownerIssue.setIssueType(issueType);
        ownerIssue.setIssueStatus(issueStatus);
        
        IPage<OwnerIssue> page = new Page<>(pageNum, pageSize);
        IPage<OwnerIssue> result = ownerIssueService.selectOwnerIssuePage(page, ownerIssue);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
}