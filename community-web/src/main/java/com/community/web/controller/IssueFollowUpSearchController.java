package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.IssueFollowUp;
import com.community.service.IssueFollowUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issueFollowUp")
@Tag(name = "问题跟进管理", description = "问题跟进相关接口")
@ApiSupport(order = 23, author = "社区管理系统开发团队")
public class IssueFollowUpSearchController {

    @Autowired
    private IssueFollowUpService issueFollowUpService;

    /**
     * 根据问题ID或跟进人分页查询问题跟进信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param issueId 问题ID
     * @param operatorName 跟进人
     * @return 问题跟进信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据问题ID或跟进人分页查询问题跟进信息", description = "根据问题ID或跟进人分页查询问题跟进信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result search(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                         @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                         @Parameter(description = "问题ID") @RequestParam(required = false) Long issueId,
                         @Parameter(description = "跟进人") @RequestParam(required = false) String operatorName) {
        IssueFollowUp issueFollowUp = new IssueFollowUp();
        issueFollowUp.setIssueId(issueId);
        issueFollowUp.setOperatorName(operatorName);
        
        IPage<IssueFollowUp> page = new Page<>(pageNum, pageSize);
        IPage<IssueFollowUp> result = issueFollowUpService.selectIssueFollowUpPage(page, issueFollowUp);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
}