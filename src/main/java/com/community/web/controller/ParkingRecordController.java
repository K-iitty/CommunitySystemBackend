package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.ParkingRecord;
import com.community.service.ParkingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parkingRecord")
@Tag(name = "停车记录管理", description = "停车记录相关接口")
@ApiSupport(order = 17, author = "社区管理系统开发团队")
public class ParkingRecordController {

    @Autowired
    private ParkingRecordService parkingRecordService;

    /**
     * 分页查询停车记录信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param parkingRecord 查询条件
     * @return 停车记录信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询停车记录信息", description = "根据条件分页查询停车记录信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       ParkingRecord parkingRecord) {
        IPage<ParkingRecord> page = new Page<>(pageNum, pageSize);
        IPage<ParkingRecord> result = parkingRecordService.selectParkingRecordPage(page, parkingRecord);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询停车记录信息
     *
     * @param id 停车记录ID
     * @return 停车记录信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询停车记录信息", description = "根据ID查询指定停车记录信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "停车记录ID", required = true, example = "1") @PathVariable Long id) {
        ParkingRecord parkingRecord = parkingRecordService.getById(id);
        return Result.ok().put("data", parkingRecord);
    }

    /**
     * 新增停车记录信息
     *
     * @param parkingRecord 停车记录信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增停车记录信息", description = "新增停车记录信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "停车记录信息") @RequestBody ParkingRecord parkingRecord) {
        boolean result = parkingRecordService.save(parkingRecord);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改停车记录信息
     *
     * @param parkingRecord 停车记录信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改停车记录信息", description = "修改停车记录信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "停车记录信息") @RequestBody ParkingRecord parkingRecord) {
        boolean result = parkingRecordService.updateById(parkingRecord);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除停车记录信息
     *
     * @param id 停车记录ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除停车记录信息", description = "根据ID删除停车记录信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "停车记录ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = parkingRecordService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}