package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.MeterConfig;
import com.community.service.MeterConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meterConfig")
@Tag(name = "仪表配置管理", description = "仪表配置相关接口")
@ApiSupport(order = 20, author = "社区管理系统开发团队")
public class MeterConfigController {

    @Autowired
    private MeterConfigService meterConfigService;

    /**
     * 分页查询仪表配置信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param meterConfig 查询条件
     * @return 仪表配置信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询仪表配置信息", description = "根据条件分页查询仪表配置信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       MeterConfig meterConfig) {
        IPage<MeterConfig> page = new Page<>(pageNum, pageSize);
        IPage<MeterConfig> result = meterConfigService.selectMeterConfigPage(page, meterConfig);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询仪表配置信息
     *
     * @param id 仪表配置ID
     * @return 仪表配置信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询仪表配置信息", description = "根据ID查询指定仪表配置信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "仪表配置ID", required = true, example = "1") @PathVariable Long id) {
        MeterConfig meterConfig = meterConfigService.getById(id);
        return Result.ok().put("data", meterConfig);
    }

    /**
     * 新增仪表配置信息
     *
     * @param meterConfig 仪表配置信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增仪表配置信息", description = "新增仪表配置信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "仪表配置信息") @RequestBody MeterConfig meterConfig) {
        boolean result = meterConfigService.save(meterConfig);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改仪表配置信息
     *
     * @param meterConfig 仪表配置信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改仪表配置信息", description = "修改仪表配置信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "仪表配置信息") @RequestBody MeterConfig meterConfig) {
        boolean result = meterConfigService.updateById(meterConfig);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除仪表配置信息
     *
     * @param id 仪表配置ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除仪表配置信息", description = "根据ID删除仪表配置信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "仪表配置ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = meterConfigService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}