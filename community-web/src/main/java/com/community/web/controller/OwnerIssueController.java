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
public class OwnerIssueController {

    @Autowired
    private OwnerIssueService ownerIssueService;

    /**
     * 分页查询业主问题信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param ownerIssue 查询条件
     * @return 业主问题信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询业主问题信息", description = "根据条件分页查询业主问题信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       OwnerIssue ownerIssue) {
        IPage<OwnerIssue> page = new Page<>(pageNum, pageSize);
        IPage<OwnerIssue> result = ownerIssueService.selectOwnerIssuePage(page, ownerIssue);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询业主问题信息
     *
     * @param id 业主问题ID
     * @return 业主问题信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询业主问题信息", description = "根据ID查询指定业主问题信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "业主问题ID", required = true, example = "1") @PathVariable Long id) {
        OwnerIssue ownerIssue = ownerIssueService.getById(id);
        return Result.ok().put("data", ownerIssue);
    }

    /**
     * 新增业主问题信息
     *
     * @param ownerIssue 业主问题信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增业主问题信息", description = "新增业主问题信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "业主问题信息") @RequestBody OwnerIssue ownerIssue) {
        boolean result = ownerIssueService.save(ownerIssue);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改业主问题信息
     *
     * @param ownerIssue 业主问题信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改业主问题信息", description = "修改业主问题信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "业主问题信息") @RequestBody OwnerIssue ownerIssue) {
        boolean result = ownerIssueService.updateById(ownerIssue);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除业主问题信息
     *
     * @param id 业主问题ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除业主问题信息", description = "根据ID删除业主问题信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "业主问题ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = ownerIssueService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}