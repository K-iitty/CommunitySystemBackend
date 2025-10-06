package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.Role;
import com.community.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@Tag(name = "角色信息管理", description = "角色信息相关接口")
@ApiSupport(order = 16, author = "社区管理系统开发团队")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询角色信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param role 查询条件
     * @return 角色信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询角色信息", description = "根据条件分页查询角色信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Role role) {
        IPage<Role> page = new Page<>(pageNum, pageSize);
        IPage<Role> result = roleService.selectRolePage(page, role);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据角色名称或角色编码或角色类型或状态分页查询角色信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param roleName 角色名称
     * @param roleCode 角色编码
     * @param roleType 角色类型
     * @param status 状态
     * @return 角色信息分页数据
     */
    @GetMapping("/search")
    @Operation(
        summary = "根据角色名称或角色编码或角色类型或状态分页查询角色信息",
        description = "根据角色名称或角色编码或角色类型或状态分页查询角色信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "roleName", description = "角色名称"),
            @Parameter(name = "roleCode", description = "角色编码"),
            @Parameter(name = "roleType", description = "角色类型"),
            @Parameter(name = "status", description = "状态")
        }
    )
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "角色名称") @RequestParam(required = false) String roleName,
                                   @Parameter(description = "角色编码") @RequestParam(required = false) String roleCode,
                                   @Parameter(description = "角色类型") @RequestParam(required = false) String roleType,
                                   @Parameter(description = "状态") @RequestParam(required = false) String status) {
        IPage<Role> page = new Page<>(pageNum, pageSize);
        IPage<Role> result = roleService.selectRolePageByMultiple(page, roleName, roleCode, roleType, status);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询角色信息", description = "根据ID查询指定角色信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "角色ID", required = true, example = "1") @PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok().put("data", role);
    }

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增角色信息", description = "新增角色信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "角色信息") @RequestBody Role role) {
        boolean result = roleService.save(role);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改角色信息", description = "修改角色信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "角色信息") @RequestBody Role role) {
        boolean result = roleService.updateById(role);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除角色信息
     *
     * @param id 角色ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色信息", description = "根据ID删除角色信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "角色ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = roleService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}