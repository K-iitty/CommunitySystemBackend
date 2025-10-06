package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.ParkingLot;
import com.community.service.ParkingLotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Knife4j增强注解
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/parkingLot")
@Tag(name = "停车场信息管理", description = "停车场信息相关接口")
@ApiSupport(order = 7, author = "社区管理系统开发团队")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    /**
     * 分页查询停车场信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param parkingLot 查询条件
     * @return 停车场信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询停车场信息", description = "根据条件分页查询停车场信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       ParkingLot parkingLot) {
        IPage<ParkingLot> page = new Page<>(pageNum, pageSize);
        IPage<ParkingLot> result = parkingLotService.selectParkingLotPage(page, parkingLot);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询停车场信息
     *
     * @param id 停车场ID
     * @return 停车场信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询停车场信息", description = "根据ID查询指定停车场信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "停车场ID") @PathVariable Long id) {
        ParkingLot parkingLot = parkingLotService.getById(id);
        return Result.ok().put("data", parkingLot);
    }

    /**
     * 新增停车场信息
     *
     * @param parkingLot 停车场信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增停车场信息", description = "新增停车场信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(@Parameter(description = "停车场信息") @RequestBody ParkingLot parkingLot) {
        boolean result = parkingLotService.save(parkingLot);

        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("code", 200);
            response.put("msg", "新增成功");
        } else {
            response.put("code", 500);
            response.put("msg", "新增失败");
        }
        return response;
    }

    /**
     * 修改停车场信息
     *
     * @param parkingLot 停车场信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改停车场信息", description = "修改停车场信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(@Parameter(description = "停车场信息") @RequestBody ParkingLot parkingLot) {
        boolean result = parkingLotService.updateById(parkingLot);

        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("code", 200);
            response.put("msg", "修改成功");
        } else {
            response.put("code", 500);
            response.put("msg", "修改失败");
        }
        return response;
    }

    /**
     * 删除停车场信息
     *
     * @param id 停车场ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除停车场信息", description = "根据ID删除停车场信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "停车场ID") @PathVariable Long id) {
        boolean result = parkingLotService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}