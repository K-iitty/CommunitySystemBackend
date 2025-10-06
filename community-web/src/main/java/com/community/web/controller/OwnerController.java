package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity .Owner;
import com.community.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Knife4j增强注解
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

@RestController
@RequestMapping("/api/owner")
@Tag(name = "业主信息管理", description = "业主信息相关接口")
@ApiSupport(order = 4, author = "社区管理系统开发团队")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /**
     * 分页查询业主信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param owner 查询条件
     * @return 业主信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询业主信息", description = "根据条件分页查询业主信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Owner owner) {
        IPage<Owner> page = new Page<>(pageNum, pageSize);
        IPage<Owner> result = ownerService.selectOwnerPage(page, owner);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据业主姓名或手机号或业主类型或状态分页查询业主信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param name 业主姓名
     * @param phone 手机号
     * @param ownerType 业主类型
     * @param status 状态
     * @return 业主信息分页数据
     */
    @GetMapping("/search")
    @Operation(
        summary = "根据业主姓名或手机号或业主类型或状态分页查询业主信息",
        description = "根据业主姓名或手机号或业主类型或状态分页查询业主信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "name", description = "业主姓名"),
            @Parameter(name = "phone", description = "手机号"),
            @Parameter(name = "ownerType", description = "业主类型"),
            @Parameter(name = "status", description = "状态")
        }
    )
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "业主姓名") @RequestParam(required = false) String name,
                                   @Parameter(description = "手机号") @RequestParam(required = false) String phone,
                                   @Parameter(description = "业主类型") @RequestParam(required = false) String ownerType,
                                   @Parameter(description = "状态") @RequestParam(required = false) String status) {
        IPage<Owner> page = new Page<>(pageNum, pageSize);
        IPage<Owner> result = ownerService.selectOwnerPageByMultiple(page, name, phone, ownerType, status);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询业主信息
     *
     * @param id 业主ID
     * @return 业主信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询业主信息", description = "根据ID查询指定业主信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "业主ID", required = true, example = "1") @PathVariable Long id) {
        Owner owner = ownerService.getById(id);
        return Result.ok().put("data", owner);
    }

    /**
     * 新增业主信息
     *
     * @param owner 业主信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增业主信息", description = "新增业主信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "业主信息") @RequestBody Owner owner) {
        boolean result = ownerService.save(owner);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改业主信息
     *
     * @param owner 业主信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改业主信息", description = "修改业主信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "业主信息") @RequestBody Owner owner) {
        boolean result = ownerService.updateById(owner);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除业主信息
     *
     * @param id 业主ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除业主信息", description = "根据ID删除业主信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "业主ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = ownerService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}