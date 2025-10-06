package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.StaffExtension;
import com.community.service.StaffExtensionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staffExtension")
@Tag(name = "员工扩展信息管理", description = "员工扩展信息相关接口")
@ApiSupport(order = 22, author = "社区管理系统开发团队")
public class StaffExtensionController {

    @Autowired
    private StaffExtensionService staffExtensionService;

    /**
     * 分页查询员工扩展信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param staffExtension 查询条件
     * @return 员工扩展信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询员工扩展信息", description = "根据条件分页查询员工扩展信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       StaffExtension staffExtension) {
        IPage<StaffExtension> page = new Page<>(pageNum, pageSize);
        IPage<StaffExtension> result = staffExtensionService.selectStaffExtensionPage(page, staffExtension);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询员工扩展信息
     *
     * @param id 员工扩展信息ID
     * @return 员工扩展信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询员工扩展信息", description = "根据ID查询指定员工扩展信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "员工扩展信息ID", required = true, example = "1") @PathVariable Long id) {
        StaffExtension staffExtension = staffExtensionService.getById(id);
        return Result.ok().put("data", staffExtension);
    }

    /**
     * 新增员工扩展信息
     *
     * @param staffExtension 员工扩展信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增员工扩展信息", description = "新增员工扩展信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "员工扩展信息") @RequestBody StaffExtension staffExtension) {
        boolean result = staffExtensionService.save(staffExtension);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改员工扩展信息
     *
     * @param staffExtension 员工扩展信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改员工扩展信息", description = "修改员工扩展信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "员工扩展信息") @RequestBody StaffExtension staffExtension) {
        boolean result = staffExtensionService.updateById(staffExtension);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除员工扩展信息
     *
     * @param id 员工扩展信息ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除员工扩展信息", description = "根据ID删除员工扩展信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "员工扩展信息ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = staffExtensionService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}