package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.House;
import com.community.service.HouseService;
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
@RequestMapping("/api/house")
@Tag(name = "房屋信息管理", description = "房屋信息相关接口")
@ApiSupport(order = 6, author = "社区管理系统开发团队")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 分页查询房屋信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param house 查询条件
     * @return 房屋信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询房屋信息", description = "根据条件分页查询房屋信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                    @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                    House house) {
        IPage<House> page = new Page<>(pageNum, pageSize);
        IPage<House> result = houseService.selectHousePage(page, house);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据房屋编码或房号分页查询房屋信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param houseCode 房屋编码
     * @param roomNo 房号
     * @return 房屋信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据房屋编码或房号分页查询房屋信息", description = "根据房屋编码或房号分页查询房屋信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "房屋编码") @RequestParam(required = false) String houseCode,
                                   @Parameter(description = "房号") @RequestParam(required = false) String roomNo) {
        IPage<House> page = new Page<>(pageNum, pageSize);
        IPage<House> result = houseService.selectHousePageByMultiple(page, houseCode, roomNo);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询房屋信息
     *
     * @param id 房屋ID
     * @return 房屋信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询房屋信息", description = "根据ID查询指定房屋信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "房屋ID") @PathVariable Long id) {
        House house = houseService.getById(id);
        return Result.ok().put("data", house);
    }

    /**
     * 新增房屋信息
     *
     * @param house 房屋信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增房屋信息", description = "新增房屋信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(@Parameter(description = "房屋信息") @RequestBody House house) {
        boolean result = houseService.save(house);

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
     * 修改房屋信息
     *
     * @param house 房屋信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改房屋信息", description = "修改房屋信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(@Parameter(description = "房屋信息") @RequestBody House house) {
        boolean result = houseService.updateById(house);

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
     * 删除房屋信息
     *
     * @param id 房屋ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除房屋信息", description = "根据ID删除房屋信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "房屋ID") @PathVariable Long id) {
        boolean result = houseService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}