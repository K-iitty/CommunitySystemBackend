package com.community.web.controller;

import com.community.common.Result;
import com.community.domain.entity.*;
import com.community.domain.vo.*;
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
    
    /**
     * 根据业主ID查询车位和车辆信息
     */
    @GetMapping("/owner/{ownerId}/parkingInfo")
    @Operation(summary = "根据业主ID查询车位和车辆信息", description = "根据业主ID查询车位和车辆信息")
    @ApiOperationSupport(order = 14, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getParkingInfoByOwnerId(@Parameter(description = "业主ID") @PathVariable Long ownerId) {
        OwnerParkingInfoVO result = complexBusinessService.getParkingInfoByOwnerId(ownerId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据车位ID查询车位和停车场详细信息
     */
    @GetMapping("/parkingSpace/{parkingSpaceId}/detail")
    @Operation(summary = "根据车位ID查询车位和停车场详细信息", description = "根据车位ID查询车位和停车场详细信息")
    @ApiOperationSupport(order = 15, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getParkingSpaceDetailById(@Parameter(description = "车位ID") @PathVariable Long parkingSpaceId) {
        ParkingSpaceDetailVO result = complexBusinessService.getParkingSpaceDetailById(parkingSpaceId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据停车场ID查询停车场和社区详细信息
     */
    @GetMapping("/parkingLot/{parkingLotId}/detail")
    @Operation(summary = "根据停车场ID查询停车场和社区详细信息", description = "根据停车场ID查询停车场和社区详细信息")
    @ApiOperationSupport(order = 16, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getParkingLotDetailById(@Parameter(description = "停车场ID") @PathVariable Long parkingLotId) {
        ParkingLotDetailVO result = complexBusinessService.getParkingLotDetailById(parkingLotId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据业主ID查询仪表信息和仪表配置信息
     */
    @GetMapping("/owner/{ownerId}/meters")
    @Operation(summary = "根据业主ID查询仪表信息和仪表配置信息", description = "根据业主ID查询仪表信息和仪表配置信息")
    @ApiOperationSupport(order = 17, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getMeterInfoByOwnerId(@Parameter(description = "业主ID") @PathVariable Long ownerId) {
        OwnerMeterVO result = complexBusinessService.getMeterInfoByOwnerId(ownerId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据抄表ID查询抄表员信息和业主信息
     */
    @GetMapping("/meterReading/{readingId}/details")
    @Operation(summary = "根据抄表ID查询抄表员信息和业主信息", description = "根据抄表ID查询抄表员信息和业主信息")
    @ApiOperationSupport(order = 18, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getMeterReadingDetailById(@Parameter(description = "抄表记录ID") @PathVariable Long readingId) {
        MeterReadingDetailVO result = complexBusinessService.getMeterReadingDetailById(readingId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据抄表记录中的仪表ID查询仪表信息和仪表配置信息
     */
    @GetMapping("/meterReading/meter/{meterId}/details")
    @Operation(summary = "根据抄表记录中的仪表ID查询仪表信息和仪表配置信息", description = "根据抄表记录中的仪表ID查询仪表信息和仪表配置信息")
    @ApiOperationSupport(order = 19, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getMeterDetailByMeterId(@Parameter(description = "仪表ID") @PathVariable Long meterId) {
        MeterDetailVO result = complexBusinessService.getMeterDetailByMeterId(meterId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据楼栋ID获取楼栋信息和社区信息
     */
    @GetMapping("/building/{buildingId}/details")
    @Operation(summary = "根据楼栋ID获取楼栋信息和社区信息", description = "根据楼栋ID获取楼栋信息和社区信息")
    @ApiOperationSupport(order = 20, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getBuildingDetailById(@Parameter(description = "楼栋ID") @PathVariable Long buildingId) {
        BuildingDetailVO result = complexBusinessService.getBuildingDetailById(buildingId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据房屋ID查询房屋信息、楼栋信息、社区信息
     */
    @GetMapping("/house/{houseId}/details")
    @Operation(summary = "根据房屋ID查询房屋信息、楼栋信息、社区信息", description = "根据房屋ID查询房屋信息、楼栋信息、社区信息")
    @ApiOperationSupport(order = 21, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getHouseDetailById(@Parameter(description = "房屋ID") @PathVariable Long houseId) {
        HouseDetailVO result = complexBusinessService.getHouseDetailById(houseId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 新增房屋业主关联
     */
    @PostMapping("/houseOwner")
    @Operation(summary = "新增房屋业主关联", description = "新增房屋业主关联，需要输入业主ID和房屋ID")
    @ApiOperationSupport(order = 22, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result addHouseOwnerRelation(@Parameter(description = "业主ID") @RequestParam Long ownerId,
                                       @Parameter(description = "房屋ID") @RequestParam Long houseId) {
        boolean result = complexBusinessService.addHouseOwnerRelation(ownerId, houseId);
        if (result) {
            return Result.ok("关联成功");
        } else {
            return Result.error("关联失败");
        }
    }
    
    /**
     * 根据停车场ID查询停车场信息和社区信息
     */
    @GetMapping("/parkingLot/{parkingLotId}/details")
    @Operation(summary = "根据停车场ID查询停车场信息和社区信息", description = "根据停车场ID查询停车场信息和社区信息")
    @ApiOperationSupport(order = 23, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getParkingLotDetailWithCommunityById(@Parameter(description = "停车场ID") @PathVariable Long parkingLotId) {
        ParkingLotDetailVO result = complexBusinessService.getParkingLotDetailWithCommunityById(parkingLotId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据车位ID查询停车场信息和车位信息
     */
    @GetMapping("/parkingSpace/{spaceId}/details")
    @Operation(summary = "根据车位ID查询停车场信息和车位信息", description = "根据车位ID查询停车场信息和车位信息")
    @ApiOperationSupport(order = 24, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getParkingSpaceDetailWithLotById(@Parameter(description = "车位ID") @PathVariable Long spaceId) {
        ParkingSpaceDetailVO result = complexBusinessService.getParkingSpaceDetailWithLotById(spaceId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据车辆ID查询车辆信息和业主信息
     */
    @GetMapping("/vehicle/{vehicleId}/details")
    @Operation(summary = "根据车辆ID查询车辆信息和业主信息", description = "根据车辆ID查询车辆信息和业主信息")
    @ApiOperationSupport(order = 25, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getVehicleDetailById(@Parameter(description = "车辆ID") @PathVariable Long vehicleId) {
        VehicleDetailVO result = complexBusinessService.getVehicleDetailById(vehicleId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据停车记录ID查询车辆信息、业主信息、停车位信息和停车场信息
     */
    @GetMapping("/parkingRecord/{recordId}/details")
    @Operation(summary = "根据停车记录ID查询车辆信息、业主信息、停车位信息和停车场信息", description = "根据停车记录ID查询车辆信息、业主信息、停车位信息和停车场信息")
    @ApiOperationSupport(order = 26, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getParkingRecordDetailById(@Parameter(description = "停车记录ID") @PathVariable Long recordId) {
        ParkingRecordDetailVO result = complexBusinessService.getParkingRecordDetailById(recordId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据门禁设备ID查询楼栋信息、社区信息和门禁设备信息
     */
    @GetMapping("/accessControlDevice/{deviceId}/details")
    @Operation(summary = "根据门禁设备ID查询楼栋信息、社区信息和门禁设备信息", description = "根据门禁设备ID查询楼栋信息、社区信息和门禁设备信息")
    @ApiOperationSupport(order = 27, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getAccessControlDeviceDetailById(@Parameter(description = "门禁设备ID") @PathVariable Long deviceId) {
        AccessControlDeviceDetailVO result = complexBusinessService.getAccessControlDeviceDetailById(deviceId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据门禁记录ID查询人员信息和门禁设备信息
     */
    @GetMapping("/accessControlRecord/{recordId}/details")
    @Operation(summary = "根据门禁记录ID查询人员信息和门禁设备信息", description = "根据门禁记录ID查询人员信息（物业或业主）和门禁设备信息")
    @ApiOperationSupport(order = 28, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getAccessControlRecordDetailById(@Parameter(description = "门禁记录ID") @PathVariable Long recordId) {
        AccessControlRecordDetailVO result = complexBusinessService.getAccessControlRecordDetailById(recordId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据仪表信息ID查询仪表配置信息和所属的房屋信息或楼栋信息
     */
    @GetMapping("/meterInfo/{meterId}/details")
    @Operation(summary = "根据仪表信息ID查询仪表配置信息和所属的房屋信息或楼栋信息", description = "根据仪表信息ID查询仪表配置信息和所属的房屋信息或楼栋信息")
    @ApiOperationSupport(order = 29, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getMeterInfoDetailById(@Parameter(description = "仪表信息ID") @PathVariable Long meterId) {
        MeterInfoDetailVO result = complexBusinessService.getMeterInfoDetailById(meterId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据抄表记录ID查询仪表信息和抄表记录信息
     */
    @GetMapping("/meterReading/{readingId}/meterDetails")
    @Operation(summary = "根据抄表记录ID查询仪表信息和抄表记录信息", description = "根据抄表记录ID查询仪表信息和抄表记录信息")
    @ApiOperationSupport(order = 30, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getMeterReadingAndMeterInfoById(@Parameter(description = "抄表记录ID") @PathVariable Long readingId) {
        MeterReadingDetailVO result = complexBusinessService.getMeterReadingAndMeterInfoById(readingId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据员工ID查询部门信息和员工信息
     */
    @GetMapping("/staff/{staffId}/details")
    @Operation(summary = "根据员工ID查询部门信息和员工信息", description = "根据员工ID查询部门信息和员工信息")
    @ApiOperationSupport(order = 31, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getStaffDetailById(@Parameter(description = "员工ID") @PathVariable Long staffId) {
        StaffDetailVO result = complexBusinessService.getStaffDetailById(staffId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据业主ID查询基本信息、房屋拥有信息、车位所属信息、车辆所属信息
     */
    @GetMapping("/owner/{ownerId}/allDetails")
    @Operation(summary = "根据业主ID查询基本信息、房屋拥有信息、车位所属信息、车辆所属信息", description = "根据业主ID查询基本信息、房屋拥有信息、车位所属信息、车辆所属信息")
    @ApiOperationSupport(order = 32, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getOwnerDetailById(@Parameter(description = "业主ID") @PathVariable Long ownerId) {
        OwnerDetailVO result = complexBusinessService.getOwnerDetailById(ownerId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据社区公告ID获取社区公告信息和社区信息
     */
    @GetMapping("/communityNotice/{noticeId}/details")
    @Operation(summary = "根据社区公告ID获取社区公告信息和社区信息", description = "根据社区公告ID获取社区公告信息和社区信息")
    @ApiOperationSupport(order = 33, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getCommunityNoticeDetailById(@Parameter(description = "社区公告ID") @PathVariable Long noticeId) {
        CommunityNoticeDetailVO result = complexBusinessService.getCommunityNoticeDetailById(noticeId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据问题ID查询业主信息、房屋信息、社区信息
     */
    @GetMapping("/ownerIssue/{issueId}/details")
    @Operation(summary = "根据问题ID查询业主信息、房屋信息、社区信息", description = "根据问题ID查询业主信息、房屋信息、社区信息")
    @ApiOperationSupport(order = 34, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getOwnerIssueDetailById(@Parameter(description = "问题ID") @PathVariable Long issueId) {
        OwnerIssueDetailVO result = complexBusinessService.getOwnerIssueDetailById(issueId);
        return Result.ok().put("data", result);
    }
    
    /**
     * 根据问题跟进ID获取问题信息、跟进人信息、业主信息
     */
    @GetMapping("/issueFollowUp/{followUpId}/details")
    @Operation(summary = "根据问题跟进ID获取问题信息、跟进人信息、业主信息", description = "根据问题跟进ID获取问题信息、跟进人（物业）信息、业主信息")
    @ApiOperationSupport(order = 35, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getIssueFollowUpDetailById(@Parameter(description = "问题跟进ID") @PathVariable Long followUpId) {
        IssueFollowUpDetailVO result = complexBusinessService.getIssueFollowUpDetailById(followUpId);
        return Result.ok().put("data", result);
    }
}