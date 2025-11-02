package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.House;
import com.community.domain.vo.HouseDetailVO;
import com.community.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Knife4j增强注解
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import java.math.BigDecimal;
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
     * 获取房屋总数
     *
     * @return 房屋总数
     */
    @GetMapping("/count")
    @Operation(summary = "获取房屋总数", description = "获取房屋总数")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result count() {
        long count = houseService.countAll();
        return Result.ok().put("data", count);
    }

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
    @ApiOperationSupport(order = 2, author = "开发团队")
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
    @Operation(
        summary = "根据房屋编码或房号分页查询房屋信息", 
        description = "根据房屋编码或房号分页查询房屋信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "houseCode", description = "房屋编码"),
            @Parameter(name = "roomNo", description = "房号")
        }
    )
    @ApiOperationSupport(order = 3, author = "开发团队")
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
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "房屋ID") @PathVariable Long id) {
        HouseDetailVO house = houseService.getHouseDetailById(id);
        return Result.ok().put("data", house);
    }

    /**
     * 新增房屋信息（支持同时上传户型图）
     *
     * @param communityId 所属社区ID
     * @param buildingId 所属楼宇ID
     * @param roomNo 房间号
     * @param fullRoomNo 完整房间号
     * @param houseCode 房屋唯一编码
     * @param buildingArea 建筑面积
     * @param usableArea 使用面积
     * @param sharedArea 公摊面积
     * @param houseType 房屋类型
     * @param houseLayout 房屋户型
     * @param houseOrientation 房屋朝向
     * @param parkingSpaceNo 车位号
     * @param parkingType 车位类型
     * @param houseStatus 房屋状态
     * @param decorationStatus 装修状态
     * @param floorLevel 所在楼层
     * @param hasBalcony 是否有阳台
     * @param hasGarden 是否有花园
     * @param remark 备注信息
     * @param file 户型图文件（可选）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增房屋信息", description = "新增房屋信息（可同时上传户型图）")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(
            @Parameter(description = "所属社区ID", required = true) @RequestParam Long communityId,
            @Parameter(description = "所属楼宇ID", required = true) @RequestParam Long buildingId,
            @Parameter(description = "房间号", required = true) @RequestParam String roomNo,
            @Parameter(description = "完整房间号") @RequestParam(required = false) String fullRoomNo,
            @Parameter(description = "房屋唯一编码") @RequestParam(required = false) String houseCode,
            @Parameter(description = "建筑面积(㎡)", required = true) @RequestParam BigDecimal buildingArea,
            @Parameter(description = "使用面积(㎡)") @RequestParam(required = false) BigDecimal usableArea,
            @Parameter(description = "公摊面积(㎡)") @RequestParam(required = false) BigDecimal sharedArea,
            @Parameter(description = "房屋类型", required = true) @RequestParam String houseType,
            @Parameter(description = "房屋户型") @RequestParam(required = false) String houseLayout,
            @Parameter(description = "房屋朝向") @RequestParam(required = false) String houseOrientation,
            @Parameter(description = "车位号") @RequestParam(required = false) String parkingSpaceNo,
            @Parameter(description = "车位类型") @RequestParam(required = false) String parkingType,
            @Parameter(description = "房屋状态") @RequestParam(required = false) String houseStatus,
            @Parameter(description = "装修状态") @RequestParam(required = false) String decorationStatus,
            @Parameter(description = "所在楼层") @RequestParam(required = false) Integer floorLevel,
            @Parameter(description = "是否有阳台") @RequestParam(required = false) Integer hasBalcony,
            @Parameter(description = "是否有花园") @RequestParam(required = false) Integer hasGarden,
            @Parameter(description = "备注信息") @RequestParam(required = false) String remark,
            @Parameter(description = "户型图文件（可选）") @RequestParam(value = "file", required = false) MultipartFile file) {
        
        // 构建房屋信息对象
        House house = new House();
        house.setCommunityId(communityId);
        house.setBuildingId(buildingId);
        house.setRoomNo(roomNo);
        house.setFullRoomNo(fullRoomNo);
        house.setHouseCode(houseCode);
        house.setBuildingArea(buildingArea);
        house.setUsableArea(usableArea);
        house.setSharedArea(sharedArea);
        house.setHouseType(houseType);
        house.setHouseLayout(houseLayout);
        house.setHouseOrientation(houseOrientation);
        house.setParkingSpaceNo(parkingSpaceNo);
        house.setParkingType(parkingType);
        house.setHouseStatus(houseStatus);
        house.setDecorationStatus(decorationStatus);
        house.setFloorLevel(floorLevel);
        house.setHasBalcony(hasBalcony);
        house.setHasGarden(hasGarden);
        house.setRemark(remark);
        
        // 保存房屋信息
        boolean result = houseService.save(house);
        
        // 如果有户型图文件，则同时处理图片上传
        if (result && file != null && !file.isEmpty()) {
            try {
                houseService.updateFloorPlanImage(house.getId(), file);
            } catch (Exception e) {
                // 图片上传失败不应该影响房屋信息的保存
                e.printStackTrace();
            }
        }

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
     * 修改房屋信息（支持同时上传户型图）
     *
     * @param id 房屋ID
     * @param communityId 所属社区ID
     * @param buildingId 所属楼宇ID
     * @param roomNo 房间号
     * @param fullRoomNo 完整房间号
     * @param houseCode 房屋唯一编码
     * @param buildingArea 建筑面积
     * @param usableArea 使用面积
     * @param sharedArea 公摊面积
     * @param houseType 房屋类型
     * @param houseLayout 房屋户型
     * @param houseOrientation 房屋朝向
     * @param parkingSpaceNo 车位号
     * @param parkingType 车位类型
     * @param houseStatus 房屋状态
     * @param decorationStatus 装修状态
     * @param floorLevel 所在楼层
     * @param hasBalcony 是否有阳台
     * @param hasGarden 是否有花园
     * @param remark 备注信息
     * @param file 户型图文件（可选）
     * @return 操作结果
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改房屋信息", description = "修改房屋信息（可同时上传户型图）")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(
            @Parameter(description = "房屋ID") @PathVariable Long id,
            @Parameter(description = "所属社区ID") @RequestParam(required = false) Long communityId,
            @Parameter(description = "所属楼宇ID") @RequestParam(required = false) Long buildingId,
            @Parameter(description = "房间号") @RequestParam(required = false) String roomNo,
            @Parameter(description = "完整房间号") @RequestParam(required = false) String fullRoomNo,
            @Parameter(description = "房屋唯一编码") @RequestParam(required = false) String houseCode,
            @Parameter(description = "建筑面积(㎡)") @RequestParam(required = false) BigDecimal buildingArea,
            @Parameter(description = "使用面积(㎡)") @RequestParam(required = false) BigDecimal usableArea,
            @Parameter(description = "公摊面积(㎡)") @RequestParam(required = false) BigDecimal sharedArea,
            @Parameter(description = "房屋类型") @RequestParam(required = false) String houseType,
            @Parameter(description = "房屋户型") @RequestParam(required = false) String houseLayout,
            @Parameter(description = "房屋朝向") @RequestParam(required = false) String houseOrientation,
            @Parameter(description = "车位号") @RequestParam(required = false) String parkingSpaceNo,
            @Parameter(description = "车位类型") @RequestParam(required = false) String parkingType,
            @Parameter(description = "房屋状态") @RequestParam(required = false) String houseStatus,
            @Parameter(description = "装修状态") @RequestParam(required = false) String decorationStatus,
            @Parameter(description = "所在楼层") @RequestParam(required = false) Integer floorLevel,
            @Parameter(description = "是否有阳台") @RequestParam(required = false) Integer hasBalcony,
            @Parameter(description = "是否有花园") @RequestParam(required = false) Integer hasGarden,
            @Parameter(description = "备注信息") @RequestParam(required = false) String remark,
            @Parameter(description = "户型图文件（可选）") @RequestParam(value = "file", required = false) MultipartFile file) {
        
        // 先从数据库获取原始房屋信息
        House house = houseService.getById(id);
        if (house == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("msg", "房屋信息不存在");
            return response;
        }
        
        // 更新房屋信息对象
        house.setCommunityId(communityId);
        house.setBuildingId(buildingId);
        house.setRoomNo(roomNo);
        house.setFullRoomNo(fullRoomNo);
        house.setHouseCode(houseCode);
        house.setBuildingArea(buildingArea);
        house.setUsableArea(usableArea);
        house.setSharedArea(sharedArea);
        house.setHouseType(houseType);
        house.setHouseLayout(houseLayout);
        house.setHouseOrientation(houseOrientation);
        house.setParkingSpaceNo(parkingSpaceNo);
        house.setParkingType(parkingType);
        house.setHouseStatus(houseStatus);
        house.setDecorationStatus(decorationStatus);
        house.setFloorLevel(floorLevel);
        house.setHasBalcony(hasBalcony);
        house.setHasGarden(hasGarden);
        house.setRemark(remark);
        
        // 保存房屋信息
        boolean result = houseService.updateById(house);
        
        // 如果有户型图文件，则同时处理图片上传
        if (result && file != null && !file.isEmpty()) {
            try {
                houseService.updateFloorPlanImage(house.getId(), file);
            } catch (Exception e) {
                // 图片上传失败不应该影响房屋信息的保存
                e.printStackTrace();
            }
        }

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
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "房屋ID") @PathVariable Long id) {
        boolean result = houseService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 上传户型图
     *
     * @param id 房屋ID
     * @param file 户型图文件
     * @return 操作结果
     */
    @PostMapping("/{id}/floor-plan-image")
    @Operation(summary = "上传户型图", description = "为指定房屋上传户型图")
    @ApiOperationSupport(order = 8, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadFloorPlanImage(
            @Parameter(description = "房屋ID") @PathVariable Long id,
            @Parameter(description = "户型图文件") @RequestParam("file") MultipartFile file) {
        try {
            boolean result = houseService.updateFloorPlanImage(id, file);
            if (result) {
                return Result.ok("户型图上传成功");
            } else {
                return Result.error("户型图上传失败");
            }
        } catch (Exception e) {
            return Result.error("户型图上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取户型图
     *
     * @param id 房屋ID
     * @return 户型图URL
     */
    @GetMapping("/{id}/floor-plan-image")
    @Operation(summary = "获取户型图", description = "获取指定房屋的户型图")
    @ApiOperationSupport(order = 9, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getFloorPlanImage(@Parameter(description = "房屋ID") @PathVariable Long id) {
        try {
            String photo = houseService.getFloorPlanImage(id);
            return Result.ok().put("data", photo);
        } catch (Exception e) {
            return Result.error("获取户型图失败: " + e.getMessage());
        }
    }
}