package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.SystemAdmin;
import com.community.service.SystemAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/systemAdmin")
@Tag(name = "系统管理员管理", description = "系统管理员相关接口")
@ApiSupport(order = 15, author = "社区管理系统开发团队")
public class SystemAdminSearchController {

    @Autowired
    private SystemAdminService systemAdminService;

    /**
     * 根据用户名或真实姓名或状态分页查询系统管理员信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param username 用户名
     * @param realName 真实姓名
     * @param status 状态
     * @return 系统管理员信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据用户名或真实姓名或状态分页查询系统管理员信息", description = "根据用户名或真实姓名或状态分页查询系统管理员信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result search(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                         @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                         @Parameter(description = "用户名") @RequestParam(required = false) String username,
                         @Parameter(description = "真实姓名") @RequestParam(required = false) String realName,
                         @Parameter(description = "状态") @RequestParam(required = false) String status) {
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setUsername(username);
        systemAdmin.setRealName(realName);
        systemAdmin.setStatus(status);
        
        IPage<SystemAdmin> page = new Page<>(pageNum, pageSize);
        IPage<SystemAdmin> result = systemAdminService.selectSystemAdminPage(page, systemAdmin);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
}