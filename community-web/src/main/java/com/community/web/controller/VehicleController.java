package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.Vehicle;
import com.community.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicle")
@Tag(name = "车辆信息管理", description = "车辆信息相关接口")
@ApiSupport(order = 12, author = "社区管理系统开发团队")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * 分页查询车辆信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param vehicle 查询条件
     * @return 车辆信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询车辆信息", description = "根据条件分页查询车辆信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Vehicle vehicle) {
        IPage<Vehicle> page = new Page<>(pageNum, pageSize);
        IPage<Vehicle> result = vehicleService.selectVehiclePage(page, vehicle);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据车牌号或车主ID分页查询车辆信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param plateNumber 车牌号
     * @param ownerId 车主ID
     * @return 车辆信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据车牌号或车主ID分页查询车辆信息", description = "根据车牌号或车主ID分页查询车辆信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "车牌号") @RequestParam(required = false) String plateNumber,
                                   @Parameter(description = "车主ID") @RequestParam(required = false) Long ownerId) {
        IPage<Vehicle> page = new Page<>(pageNum, pageSize);
        IPage<Vehicle> result = vehicleService.selectVehiclePageByMultiple(page, plateNumber, ownerId);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询车辆信息
     *
     * @param id 车辆ID
     * @return 车辆信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询车辆信息", description = "根据ID查询指定车辆信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "车辆ID") @PathVariable Long id) {
        Vehicle vehicle = vehicleService.getById(id);
        return Result.ok().put("data", vehicle);
    }

    /**
     * 新增车辆信息
     *
     * @param vehicle 车辆信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增车辆信息", description = "新增车辆信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(@Parameter(description = "车辆信息") @RequestBody Vehicle vehicle) {
        boolean result = vehicleService.save(vehicle);

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
     * 修改车辆信息
     *
     * @param vehicle 车辆信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改车辆信息", description = "修改车辆信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(@Parameter(description = "车辆信息") @RequestBody Vehicle vehicle) {
        boolean result = vehicleService.updateById(vehicle);

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
     * 删除车辆信息
     *
     * @param id 车辆ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除车辆信息", description = "根据ID删除车辆信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "车辆ID") @PathVariable Long id) {
        boolean result = vehicleService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}