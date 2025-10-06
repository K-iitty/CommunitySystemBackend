package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.Department;
import com.community.service.DepartmentService;
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
@RequestMapping("/api/department")
@Tag(name = "部门信息管理", description = "部门信息相关接口")
@ApiSupport(order = 5, author = "社区管理系统开发团队")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询部门信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param department 查询条件
     * @return 部门信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询部门信息", description = "根据条件分页查询部门信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Department department) {
        IPage<Department> page = new Page<>(pageNum, pageSize);
        IPage<Department> result = departmentService.selectDepartmentPage(page, department);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询部门信息
     *
     * @param id 部门ID
     * @return 部门信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询部门信息", description = "根据ID查询指定部门信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "部门ID", required = true, example = "1") @PathVariable Long id) {
        Department department = departmentService.getById(id);
        return Result.ok().put("data", department);
    }

    /**
     * 新增部门信息
     *
     * @param department 部门信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增部门信息", description = "新增部门信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "部门信息") @RequestBody Department department) {
        boolean result = departmentService.save(department);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改部门信息
     *
     * @param department 部门信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改部门信息", description = "修改部门信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "部门信息") @RequestBody Department department) {
        boolean result = departmentService.updateById(department);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除部门信息
     *
     * @param id 部门ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门信息", description = "根据ID删除部门信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "部门ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = departmentService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}