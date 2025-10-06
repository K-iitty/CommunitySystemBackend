package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.AccessControlDevice;
import com.community.service.AccessControlDeviceService;
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
@RequestMapping("/api/accessControlDevice")
@Tag(name = "门禁设备管理", description = "门禁设备相关接口")
@ApiSupport(order = 2, author = "社区管理系统开发团队")
public class  AccessControlDeviceController {

    @Autowired
    private AccessControlDeviceService accessControlDeviceService;

    /**
     * 分页查询门禁设备信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param accessControlDevice 查询条件
     * @return 门禁设备信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询门禁设备信息", description = "根据条件分页查询门禁设备信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       AccessControlDevice accessControlDevice) {
        IPage<AccessControlDevice> page = new Page<>(pageNum, pageSize);
        IPage<AccessControlDevice> result = accessControlDeviceService.selectAccessControlDevicePage(page, accessControlDevice);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询门禁设备信息
     *
     * @param id 门禁设备ID
     * @return 门禁设备信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询门禁设备信息", description = "根据ID查询指定门禁设备信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "门禁设备ID", required = true, example = "1") @PathVariable Long id) {
        AccessControlDevice accessControlDevice = accessControlDeviceService.getById(id);
        return Result.ok().put("data", accessControlDevice);
    }

    /**
     * 新增门禁设备信息
     *
     * @param accessControlDevice 门禁设备信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增门禁设备信息", description = "新增门禁设备信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "门禁设备信息") @RequestBody AccessControlDevice accessControlDevice) {
        boolean result = accessControlDeviceService.save(accessControlDevice);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改门禁设备信息
     *
     * @param accessControlDevice 门禁设备信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改门禁设备信息", description = "修改门禁设备信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "门禁设备信息") @RequestBody AccessControlDevice accessControlDevice) {
        boolean result = accessControlDeviceService.updateById(accessControlDevice);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除门禁设备信息
     *
     * @param id 门禁设备ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除门禁设备信息", description = "根据ID删除门禁设备信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "门禁设备ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = accessControlDeviceService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}