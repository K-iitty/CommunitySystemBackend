package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.MeterInfo;
import com.community.service.MeterInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meterInfo")
@Tag(name = "仪表信息管理", description = "仪表信息相关接口")
@ApiSupport(order = 18, author = "社区管理系统开发团队")
public class MeterInfoController {

    @Autowired
    private MeterInfoService meterInfoService;

    /**
     * 分页查询仪表信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param meterInfo 查询条件
     * @return 仪表信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询仪表信息", description = "根据条件分页查询仪表信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       MeterInfo meterInfo) {
        IPage<MeterInfo> page = new Page<>(pageNum, pageSize);
        IPage<MeterInfo> result = meterInfoService.selectMeterInfoPage(page, meterInfo);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据仪表编码或仪表名称或位置类型或仪表状态分页查询仪表信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param meterCode 仪表编码
     * @param meterName 仪表名称
     * @param locationType 位置类型
     * @param meterStatus 仪表状态
     * @return 仪表信息分页数据
     */
    @GetMapping("/search")
    @Operation(
        summary = "根据仪表编码或仪表名称或位置类型或仪表状态分页查询仪表信息",
        description = "根据仪表编码或仪表名称或位置类型或仪表状态分页查询仪表信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "meterCode", description = "仪表编码"),
            @Parameter(name = "meterName", description = "仪表名称"),
            @Parameter(name = "locationType", description = "位置类型"),
            @Parameter(name = "meterStatus", description = "仪表状态")
        }
    )
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "仪表编码") @RequestParam(required = false) String meterCode,
                                   @Parameter(description = "仪表名称") @RequestParam(required = false) String meterName,
                                   @Parameter(description = "位置类型") @RequestParam(required = false) String locationType,
                                   @Parameter(description = "仪表状态") @RequestParam(required = false) String meterStatus) {
        IPage<MeterInfo> page = new Page<>(pageNum, pageSize);
        IPage<MeterInfo> result = meterInfoService.selectMeterInfoPageByMultiple(page, meterCode, meterName, locationType, meterStatus);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询仪表信息
     *
     * @param id 仪表信息ID
     * @return 仪表信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询仪表信息", description = "根据ID查询指定仪表信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "仪表信息ID", required = true, example = "1") @PathVariable Long id) {
        MeterInfo meterInfo = meterInfoService.getById(id);
        return Result.ok().put("data", meterInfo);
    }

    /**
     * 新增仪表信息
     *
     * @param meterInfo 仪表信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增仪表信息", description = "新增仪表信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "仪表信息") @RequestBody MeterInfo meterInfo) {
        boolean result = meterInfoService.save(meterInfo);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改仪表信息
     *
     * @param meterInfo 仪表信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改仪表信息", description = "修改仪表信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "仪表信息") @RequestBody MeterInfo meterInfo) {
        boolean result = meterInfoService.updateById(meterInfo);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除仪表信息
     *
     * @param id 仪表信息ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除仪表信息", description = "根据ID删除仪表信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "仪表信息ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = meterInfoService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}