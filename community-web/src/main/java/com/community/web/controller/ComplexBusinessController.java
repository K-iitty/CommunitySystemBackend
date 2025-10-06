package com.community.web.controller;

import com.community.common.Result;
import com.community.domain.entity.*;
import com.community.service.ComplexBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complex")
@Tag(name = "复杂业务管理", description = "复杂业务相关接口")
@ApiSupport(order = 21, author = "社区管理系统开发团队")
public class ComplexBusinessController {
    
    @Autowired
    private ComplexBusinessService complexBusinessService;
    
    /**
     * 为业主添加车辆信息
     */
    @PostMapping("/owner/{ownerId}/vehicle")
    @Operation(summary = "为业主添加车辆信息", description = "为指定业主添加车辆信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result addVehicleForOwner(@Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                    @Parameter(description = "车辆信息") @RequestBody Vehicle vehicle) {
        boolean result = complexBusinessService.addVehicleForOwner(ownerId, vehicle);
        if (result) {
            return Result.ok("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 为业主添加车位信息
     */
    @PostMapping("/owner/{ownerId}/parkingSpace")
    @Operation(summary = "为业主添加车位信息", description = "为指定业主添加车位信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result addParkingSpaceForOwner(@Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                         @Parameter(description = "车位信息") @RequestBody ParkingSpace parkingSpace) {
        boolean result = complexBusinessService.addParkingSpaceForOwner(ownerId, parkingSpace);
        if (result) {
            return Result.ok("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 为车位关联停车场信息
     */
    @PostMapping("/parkingSpace/{parkingSpaceId}/linkToParkingLot/{parkingLotId}")
    @Operation(summary = "为车位关联停车场信息", description = "为指定车位关联停车场信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result linkParkingSpaceToParkingLot(@Parameter(description = "车位ID") @PathVariable Long parkingSpaceId, 
                                              @Parameter(description = "停车场ID") @PathVariable Long parkingLotId) {
        boolean result = complexBusinessService.linkParkingSpaceToParkingLot(parkingSpaceId, parkingLotId);
        if (result) {
            return Result.ok("关联成功");
        } else {
            return Result.error("关联失败");
        }
    }
    
    /**
     * 为房屋关联业主信息
     */
    @PostMapping("/house/{houseId}/linkToOwner/{ownerId}")
    @Operation(summary = "为房屋关联业主信息", description = "为指定房屋关联业主信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result linkHouseToOwner(@Parameter(description = "房屋ID") @PathVariable Long houseId, 
                                  @Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                  @Parameter(description = "关系描述") @RequestParam String relationship) {
        boolean result = complexBusinessService.linkHouseToOwner(houseId, ownerId, relationship);
        if (result) {
            return Result.ok("关联成功");
        } else {
            return Result.error("关联失败");
        }
    }
    
    /**
     * 为业主关联家属信息
     */
    @PostMapping("/owner/{ownerId}/familyMember")
    @Operation(summary = "为业主关联家属信息", description = "为指定业主关联家属信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result addFamilyMemberForOwner(@Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                         @Parameter(description = "家属信息") @RequestBody Owner familyMember) {
        boolean result = complexBusinessService.addFamilyMemberForOwner(ownerId, familyMember);
        if (result) {
            return Result.ok("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 为业主关联房屋的仪表信息
     */
    @PostMapping("/owner/{ownerId}/house/{houseId}/meterInfo")
    @Operation(summary = "为业主关联房屋的仪表信息", description = "为指定业主关联房屋的仪表信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result addMeterInfoForOwnerHouse(@Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                           @Parameter(description = "房屋ID") @PathVariable Long houseId,
                                           @Parameter(description = "仪表信息") @RequestBody MeterInfo meterInfo) {
        boolean result = complexBusinessService.addMeterInfoForOwnerHouse(ownerId, houseId, meterInfo);
        if (result) {
            return Result.ok("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 为业主生成仪表收费记录
     */
    @PostMapping("/owner/{ownerId}/meterChargeRecord")
    @Operation(summary = "为业主生成仪表收费记录", description = "为指定业主生成仪表收费记录")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result generateMeterChargeRecord(@Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                           @Parameter(description = "抄表记录") @RequestBody MeterReading meterReading) {
        boolean result = complexBusinessService.generateMeterChargeRecord(ownerId, meterReading);
        if (result) {
            return Result.ok("生成成功");
        } else {
            return Result.error("生成失败");
        }
    }
    
    /**
     * 为业主添加仪表
     */
    @PostMapping("/owner/{ownerId}/meter")
    @Operation(summary = "为业主添加仪表", description = "为指定业主添加仪表")
    @ApiOperationSupport(order = 8, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result addMeterForOwner(@Parameter(description = "业主ID") @PathVariable Long ownerId, 
                                  @Parameter(description = "仪表信息") @RequestBody MeterInfo meterInfo) {
        boolean result = complexBusinessService.addMeterForOwner(ownerId, meterInfo);
        if (result) {
            return Result.ok("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 为仪表设置分类
     */
    @PostMapping("/meter/{meterId}/category/{categoryId}")
    @Operation(summary = "为仪表设置分类", description = "为指定仪表设置分类")
    @ApiOperationSupport(order = 9, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result setMeterCategory(@Parameter(description = "仪表ID") @PathVariable Long meterId, 
                                  @Parameter(description = "分类ID") @PathVariable Long categoryId) {
        boolean result = complexBusinessService.setMeterCategory(meterId, categoryId);
        if (result) {
            return Result.ok("设置成功");
        } else {
            return Result.error("设置失败");
        }
    }
    
    /**
     * 为楼栋分配公告
     */
    @PostMapping("/building/{buildingId}/notice")
    @Operation(summary = "为楼栋分配公告", description = "为指定楼栋分配公告")
    @ApiOperationSupport(order = 10, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result assignNoticeToBuilding(@Parameter(description = "楼栋ID") @PathVariable Long buildingId, 
                                        @Parameter(description = "公告信息") @RequestBody CommunityNotice notice) {
        boolean result = complexBusinessService.assignNoticeToBuilding(buildingId, notice);
        if (result) {
            return Result.ok("分配成功");
        } else {
            return Result.error("分配失败");
        }
    }
    
    /**
     * 为社区分配公告
     */
    @PostMapping("/community/{communityId}/notice")
    @Operation(summary = "为社区分配公告", description = "为指定社区分配公告")
    @ApiOperationSupport(order = 11, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result assignNoticeToCommunity(@Parameter(description = "社区ID") @PathVariable Long communityId, 
                                         @Parameter(description = "公告信息") @RequestBody CommunityNotice notice) {
        boolean result = complexBusinessService.assignNoticeToCommunity(communityId, notice);
        if (result) {
            return Result.ok("分配成功");
        } else {
            return Result.error("分配失败");
        }
    }
    
    /**
     * 为物业人员设置不同的部门
     */
    @PostMapping("/staff/{staffId}/department/{departmentId}")
    @Operation(summary = "为物业人员设置不同的部门", description = "为指定物业人员设置不同的部门")
    @ApiOperationSupport(order = 12, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result assignStaffToDepartment(@Parameter(description = "员工ID") @PathVariable Long staffId, 
                                         @Parameter(description = "部门ID") @PathVariable Long departmentId) {
        boolean result = complexBusinessService.assignStaffToDepartment(staffId, departmentId);
        if (result) {
            return Result.ok("设置成功");
        } else {
            return Result.error("设置失败");
        }
    }
    
    /**
     * 将业主问题分配给对应类型的物业人员负责解决问题
     */
    @PostMapping("/issue/{issueId}/assignToStaff/{staffId}")
    @Operation(summary = "将业主问题分配给对应类型的物业人员负责解决问题", description = "将指定业主问题分配给对应类型的物业人员负责解决问题")
    @ApiOperationSupport(order = 13, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result assignIssueToStaff(@Parameter(description = "问题ID") @PathVariable Long issueId, 
                                    @Parameter(description = "员工ID") @PathVariable Long staffId) {
        boolean result = complexBusinessService.assignIssueToStaff(issueId, staffId);
        if (result) {
            return Result.ok("分配成功");
        } else {
            return Result.error("分配失败");
        }
    }
}