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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    @Operation(
        summary = "根据车牌号或车主ID分页查询车辆信息", 
        description = "根据车牌号或车主ID分页查询车辆信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "plateNumber", description = "车牌号"),
            @Parameter(name = "ownerId", description = "车主ID")
        }
    )
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
     * 新增车辆信息（支持同时上传图片）
     *
     * @param ownerId 车主ID（必填）
     * @param plateNumber 车牌号（必填）
     * @param vehicleType 车辆类型
     * @param brand 品牌
     * @param model 型号
     * @param color 颜色
     * @param fixedSpaceId 固定车位ID
     * @param vehicleLicenseNo 行驶证号
     * @param engineNo 发动机号
     * @param status 状态
     * @param registerDate 登记日期
     * @param remark 备注
     * @param driverLicenseImageFile 驾照照片文件（可选）
     * @param vehicleImageFiles 车辆信息照片文件（可选）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增车辆信息", description = "新增车辆信息（可同时上传图片）")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(
            @Parameter(description = "车主ID", required = true) @RequestParam Long ownerId,
            @Parameter(description = "车牌号", required = true) @RequestParam String plateNumber,
            @Parameter(description = "车辆类型") @RequestParam(required = false) String vehicleType,
            @Parameter(description = "品牌") @RequestParam(required = false) String brand,
            @Parameter(description = "型号") @RequestParam(required = false) String model,
            @Parameter(description = "颜色") @RequestParam(required = false) String color,
            @Parameter(description = "固定车位ID") @RequestParam(required = false) Long fixedSpaceId,
            @Parameter(description = "行驶证号") @RequestParam(required = false) String vehicleLicenseNo,
            @Parameter(description = "发动机号") @RequestParam(required = false) String engineNo,
            @Parameter(description = "状态") @RequestParam(required = false) String status,
            @Parameter(description = "登记日期") @RequestParam(required = false) String registerDate,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "驾照照片文件（可选）") @RequestParam(value = "driverLicenseImageFile", required = false) MultipartFile driverLicenseImageFile,
            @Parameter(description = "车辆信息照片文件（可选）") @RequestParam(value = "vehicleImageFiles", required = false) MultipartFile[] vehicleImageFiles) {
        
        // 构建车辆信息对象
        Vehicle vehicle = new Vehicle();
        vehicle.setOwnerId(ownerId);
        vehicle.setPlateNumber(plateNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setFixedSpaceId(fixedSpaceId);
        vehicle.setVehicleLicenseNo(vehicleLicenseNo);
        vehicle.setEngineNo(engineNo);
        vehicle.setStatus(status);
        
        // 解析日期字段
        if (registerDate != null && !registerDate.isEmpty()) {
            try {
                vehicle.setRegisterDate(java.time.LocalDate.parse(registerDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } catch (Exception e) {
                // 忽略日期解析错误
            }
        }
        
        vehicle.setRemark(remark);
        
        // 保存车辆信息
        boolean result = vehicleService.save(vehicle);
        
        // 如果有驾照照片文件，则处理图片上传
        if (result && driverLicenseImageFile != null && !driverLicenseImageFile.isEmpty()) {
            try {
                vehicleService.updateDriverLicenseImage(vehicle.getId(), driverLicenseImageFile);
            } catch (Exception e) {
                // 驾照照片上传失败不应该影响车辆信息的保存
                e.printStackTrace();
            }
        }
        
        // 如果有车辆信息照片文件，则处理图片上传
        if (result && vehicleImageFiles != null && vehicleImageFiles.length > 0) {
            try {
                vehicleService.updateVehicleImages(vehicle.getId(), vehicleImageFiles);
            } catch (Exception e) {
                // 车辆信息照片上传失败不应该影响车辆信息的保存
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
     * 修改车辆信息（支持同时上传图片）
     *
     * @param id 车辆ID
     * @param ownerId 车主ID
     * @param plateNumber 车牌号
     * @param vehicleType 车辆类型
     * @param brand 品牌
     * @param model 型号
     * @param color 颜色
     * @param fixedSpaceId 固定车位ID
     * @param vehicleLicenseNo 行驶证号
     * @param engineNo 发动机号
     * @param status 状态
     * @param registerDate 登记日期
     * @param remark 备注
     * @param driverLicenseImageFile 驾照照片文件（可选）
     * @param vehicleImageFiles 车辆信息照片文件（可选）
     * @return 操作结果
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改车辆信息", description = "修改车辆信息（可同时上传图片）")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(
            @Parameter(description = "车辆ID") @PathVariable Long id,
            @Parameter(description = "车主ID") @RequestParam(required = false) Long ownerId,
            @Parameter(description = "车牌号") @RequestParam(required = false) String plateNumber,
            @Parameter(description = "车辆类型") @RequestParam(required = false) String vehicleType,
            @Parameter(description = "品牌") @RequestParam(required = false) String brand,
            @Parameter(description = "型号") @RequestParam(required = false) String model,
            @Parameter(description = "颜色") @RequestParam(required = false) String color,
            @Parameter(description = "固定车位ID") @RequestParam(required = false) Long fixedSpaceId,
            @Parameter(description = "行驶证号") @RequestParam(required = false) String vehicleLicenseNo,
            @Parameter(description = "发动机号") @RequestParam(required = false) String engineNo,
            @Parameter(description = "状态") @RequestParam(required = false) String status,
            @Parameter(description = "登记日期") @RequestParam(required = false) String registerDate,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "驾照照片文件（可选）") @RequestParam(value = "driverLicenseImageFile", required = false) MultipartFile driverLicenseImageFile,
            @Parameter(description = "车辆信息照片文件（可选）") @RequestParam(value = "vehicleImageFiles", required = false) MultipartFile[] vehicleImageFiles) {
        
        // 先从数据库获取原始车辆信息
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("msg", "车辆信息不存在");
            return response;
        }
        
        // 更新车辆信息对象
        vehicle.setOwnerId(ownerId);
        vehicle.setPlateNumber(plateNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setFixedSpaceId(fixedSpaceId);
        vehicle.setVehicleLicenseNo(vehicleLicenseNo);
        vehicle.setEngineNo(engineNo);
        vehicle.setStatus(status);
        
        // 解析日期字段
        if (registerDate != null && !registerDate.isEmpty()) {
            try {
                vehicle.setRegisterDate(java.time.LocalDate.parse(registerDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } catch (Exception e) {
                // 忽略日期解析错误
            }
        }
        
        vehicle.setRemark(remark);
        
        // 保存车辆信息
        boolean result = vehicleService.updateById(vehicle);
        
        // 如果有驾照照片文件，则处理图片上传
        if (result && driverLicenseImageFile != null && !driverLicenseImageFile.isEmpty()) {
            try {
                vehicleService.updateDriverLicenseImage(id, driverLicenseImageFile);
            } catch (Exception e) {
                // 驾照照片上传失败不应该影响车辆信息的更新
                e.printStackTrace();
            }
        }
        
        // 如果有车辆信息照片文件，则处理图片上传
        if (result && vehicleImageFiles != null && vehicleImageFiles.length > 0) {
            try {
                vehicleService.updateVehicleImages(id, vehicleImageFiles);
            } catch (Exception e) {
                // 车辆信息照片上传失败不应该影响车辆信息的更新
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

    /**
     * 上传驾照照片
     *
     * @param id 车辆ID
     * @param file 驾照照片文件
     * @return 操作结果
     */
    @PostMapping("/{id}/driver-license-image")
    @Operation(summary = "上传驾照照片", description = "为指定车辆上传驾照照片")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadDriverLicenseImage(
            @Parameter(description = "车辆ID") @PathVariable Long id,
            @Parameter(description = "驾照照片文件") @RequestParam("file") MultipartFile file) {
        try {
            boolean result = vehicleService.updateDriverLicenseImage(id, file);
            if (result) {
                return Result.ok("驾照照片上传成功");
            } else {
                return Result.error("驾照照片上传失败");
            }
        } catch (Exception e) {
            return Result.error("驾照照片上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取驾照照片
     *
     * @param id 车辆ID
     * @return 驾照照片URL
     */
    @GetMapping("/{id}/driver-license-image")
    @Operation(summary = "获取驾照照片", description = "获取指定车辆的驾照照片")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getDriverLicenseImage(@Parameter(description = "车辆ID") @PathVariable Long id) {
        try {
            String photo = vehicleService.getDriverLicenseImage(id);
            return Result.ok().put("data", photo);
        } catch (Exception e) {
            return Result.error("获取驾照照片失败: " + e.getMessage());
        }
    }

    /**
     * 上传车辆信息照片
     *
     * @param id 车辆ID
     * @param files 车辆信息照片文件
     * @return 操作结果
     */
    @PostMapping("/{id}/vehicle-images")
    @Operation(summary = "上传车辆信息照片", description = "为指定车辆上传信息照片")
    @ApiOperationSupport(order = 8, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadVehicleImages(
            @Parameter(description = "车辆ID") @PathVariable Long id,
            @Parameter(description = "车辆信息照片文件") @RequestParam("files") MultipartFile[] files) {
        try {
            boolean result = vehicleService.updateVehicleImages(id, files);
            if (result) {
                return Result.ok("车辆信息照片上传成功");
            } else {
                return Result.error("车辆信息照片上传失败");
            }
        } catch (Exception e) {
            return Result.error("车辆信息照片上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取车辆信息照片
     *
     * @param id 车辆ID
     * @return 车辆信息照片URL列表
     */
    @GetMapping("/{id}/vehicle-images")
    @Operation(summary = "获取车辆信息照片", description = "获取指定车辆的信息照片")
    @ApiOperationSupport(order = 9, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getVehicleImages(@Parameter(description = "车辆ID") @PathVariable Long id) {
        try {
            String photos = vehicleService.getVehicleImages(id);
            return Result.ok().put("data", photos);
        } catch (Exception e) {
            return Result.error("获取车辆信息照片失败: " + e.getMessage());
        }
    }
}