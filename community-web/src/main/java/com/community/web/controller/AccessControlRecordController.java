package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.AccessControlRecord;
import com.community.service.AccessControlRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accessControlRecord")
@Tag(name = "门禁记录管理", description = "门禁记录相关接口")
@ApiSupport(order = 8, author = "社区管理系统开发团队")
public class AccessControlRecordController {

    @Autowired
    private AccessControlRecordService accessControlRecordService;

    /**
     * 分页查询门禁记录信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param accessControlRecord 查询条件
     * @return 门禁记录信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询门禁记录信息", description = "根据条件分页查询门禁记录信息列表")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       AccessControlRecord accessControlRecord) {
        IPage<AccessControlRecord> page = new Page<>(pageNum, pageSize);
        IPage<AccessControlRecord> result = accessControlRecordService.selectAccessControlRecordPage(page, accessControlRecord);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据设备ID或人员姓名或通行方式或验证结果分页查询门禁记录信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param deviceId 设备ID
     * @param personName 人员姓名
     * @param accessMethod 通行方式
     * @param verifyResult 验证结果
     * @return 门禁记录信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据设备ID或人员姓名或通行方式或验证结果分页查询门禁记录信息", description = "根据设备ID或人员姓名或通行方式或验证结果分页查询门禁记录信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "设备ID") @RequestParam(required = false) Long deviceId,
                                   @Parameter(description = "人员姓名") @RequestParam(required = false) String personName,
                                   @Parameter(description = "通行方式") @RequestParam(required = false) String accessMethod,
                                   @Parameter(description = "验证结果") @RequestParam(required = false) String verifyResult) {
        IPage<AccessControlRecord> page = new Page<>(pageNum, pageSize);
        IPage<AccessControlRecord> result = accessControlRecordService.selectAccessControlRecordPageByMultiple(page, deviceId, personName, accessMethod, verifyResult);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询门禁记录信息
     *
     * @param id 门禁记录ID
     * @return 门禁记录信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询门禁记录信息", description = "根据ID查询指定门禁记录信息")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "门禁记录ID") @PathVariable Long id) {
        AccessControlRecord accessControlRecord = accessControlRecordService.getById(id);
        return Result.ok().put("data", accessControlRecord);
    }

    /**
     * 新增门禁记录信息
     *
     * @param accessControlRecord 门禁记录信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增门禁记录信息", description = "新增门禁记录信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "门禁记录信息") @RequestBody AccessControlRecord accessControlRecord) {
        boolean result = accessControlRecordService.save(accessControlRecord);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改门禁记录信息
     *
     * @param accessControlRecord 门禁记录信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改门禁记录信息", description = "修改门禁记录信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "门禁记录信息") @RequestBody AccessControlRecord accessControlRecord) {
        boolean result = accessControlRecordService.updateById(accessControlRecord);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除门禁记录信息
     *
     * @param id 门禁记录ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除门禁记录信息", description = "根据ID删除门禁记录信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "门禁记录ID") @PathVariable Long id) {
        boolean result = accessControlRecordService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}