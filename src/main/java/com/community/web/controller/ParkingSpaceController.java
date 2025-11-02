package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.ParkingSpace;
import com.community.service.ParkingSpaceService;
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
@RequestMapping("/api/parkingSpace")
@Tag(name = "车位信息管理", description = "车位信息相关接口")
@ApiSupport(order = 11, author = "社区管理系统开发团队")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    /**
     * 分页查询车位信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param parkingSpace 查询条件
     * @return 车位信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询车位信息", description = "根据条件分页查询车位信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                    @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                    ParkingSpace parkingSpace) {
        IPage<ParkingSpace> page = new Page<>(pageNum, pageSize);
        IPage<ParkingSpace> result = parkingSpaceService.selectParkingSpacePage(page, parkingSpace);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据车位编号或车位号分页查询车位信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param spaceNo 车位编号或车位号
     * @return 车位信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据车位编号或车位号分页查询车位信息", description = "根据车位编号或车位号分页查询车位信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "车位编号或车位号") @RequestParam(required = false) String spaceNo) {
        IPage<ParkingSpace> page = new Page<>(pageNum, pageSize);
        IPage<ParkingSpace> result = parkingSpaceService.selectParkingSpacePageByMultiple(page, spaceNo);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询车位信息
     *
     * @param id 车位ID
     * @return 车位信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询车位信息", description = "根据ID查询指定车位信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "车位ID") @PathVariable Long id) {
        ParkingSpace parkingSpace = parkingSpaceService.getById(id);
        return Result.ok().put("data", parkingSpace);
    }

    /**
     * 新增车位信息
     *
     * @param parkingSpace 车位信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增车位信息", description = "新增车位信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "车位信息") @RequestBody ParkingSpace parkingSpace) {
        boolean result = parkingSpaceService.save(parkingSpace);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改车位信息
     *
     * @param parkingSpace 车位信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改车位信息", description = "修改车位信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "车位信息") @RequestBody ParkingSpace parkingSpace) {
        boolean result = parkingSpaceService.updateById(parkingSpace);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除车位信息
     *
     * @param id 车位ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除车位信息", description = "根据ID删除车位信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "车位ID") @PathVariable Long id) {
        boolean result = parkingSpaceService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}