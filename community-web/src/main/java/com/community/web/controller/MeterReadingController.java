package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.MeterReading;
import com.community.service.MeterReadingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meterReading")
@Tag(name = "抄表记录管理", description = "抄表记录相关接口")
@ApiSupport(order = 19, author = "社区管理系统开发团队")
public class MeterReadingController {

    @Autowired
    private MeterReadingService meterReadingService;

    /**
     * 分页查询抄表记录
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param meterReading 查询条件
     * @return 抄表记录分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询抄表记录", description = "根据条件分页查询抄表记录列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       MeterReading meterReading) {
        IPage<MeterReading> page = new Page<>(pageNum, pageSize);
        IPage<MeterReading> result = meterReadingService.selectMeterReadingPage(page, meterReading);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询抄表记录
     *
     * @param id 抄表记录ID
     * @return 抄表记录
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询抄表记录", description = "根据ID查询指定抄表记录")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "抄表记录ID", required = true, example = "1") @PathVariable Long id) {
        MeterReading meterReading = meterReadingService.getById(id);
        return Result.ok().put("data", meterReading);
    }

    /**
     * 新增抄表记录
     *
     * @param meterReading 抄表记录
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增抄表记录", description = "新增抄表记录")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "抄表记录") @RequestBody MeterReading meterReading) {
        boolean result = meterReadingService.save(meterReading);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改抄表记录
     *
     * @param meterReading 抄表记录
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改抄表记录", description = "修改抄表记录")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "抄表记录") @RequestBody MeterReading meterReading) {
        boolean result = meterReadingService.updateById(meterReading);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除抄表记录
     *
     * @param id 抄表记录ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除抄表记录", description = "根据ID删除抄表记录")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "抄表记录ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = meterReadingService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}