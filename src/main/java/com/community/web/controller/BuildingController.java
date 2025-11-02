package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.Building;
import com.community.service.BuildingService;
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
@RequestMapping("/api/building")
@Tag(name = "楼栋信息管理", description = "楼栋信息相关接口")
@ApiSupport(order = 3, author = "社区管理系统开发团队")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    /**
     * 获取楼栋总数
     *
     * @return 楼栋总数
     */
    @GetMapping("/count")
    @Operation(summary = "获取楼栋总数", description = "获取楼栋总数")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result count() {
        long count = buildingService.countAll();
        return Result.ok().put("data", count);
    }

    /**
     * 分页查询楼栋信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param building 查询条件
     * @return 楼栋信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询楼栋信息", description = "根据条件分页查询楼栋信息列表")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Building building) {
        IPage<Building> page = new Page<>(pageNum, pageSize);
        IPage<Building> result = buildingService.selectBuildingPage(page, building);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据楼栋名称或楼栋编号分页查询楼栋信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param buildingName 楼栋名称
     * @param buildingNo 楼栋编号
     * @return 楼栋信息分页数据
     */
    @GetMapping("/search")
    @Operation(
        summary = "根据楼栋名称或楼栋编号分页查询楼栋信息", 
        description = "根据楼栋名称或楼栋编号分页查询楼栋信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "buildingName", description = "楼栋名称"),
            @Parameter(name = "buildingNo", description = "楼栋编号")
        }
    )
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "楼栋名称") @RequestParam(required = false) String buildingName,
                                   @Parameter(description = "楼栋编号") @RequestParam(required = false) String buildingNo) {
        IPage<Building> page = new Page<>(pageNum, pageSize);
        IPage<Building> result = buildingService.selectBuildingPageByMultiple(page, buildingName, buildingNo);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询楼栋信息
     *
     * @param id 楼栋ID
     * @return 楼栋信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询楼栋信息", description = "根据ID查询指定楼栋信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "楼栋ID") @PathVariable Long id) {
        Building building = buildingService.getById(id);
        return Result.ok().put("data", building);
    }

    /**
     * 新增楼栋信息
     *
     * @param building 楼栋信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增楼栋信息", description = "新增楼栋信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(@Parameter(description = "楼栋信息") @RequestBody Building building) {
        boolean result = buildingService.save(building);

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
     * 修改楼栋信息
     *
     * @param building 楼栋信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改楼栋信息", description = "修改楼栋信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(@Parameter(description = "楼栋信息") @RequestBody Building building) {
        boolean result = buildingService.updateById(building);

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
     * 删除楼栋信息
     *
     * @param id 楼栋ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除楼栋信息", description = "根据ID删除楼栋信息")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "楼栋ID") @PathVariable Long id) {
        boolean result = buildingService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}