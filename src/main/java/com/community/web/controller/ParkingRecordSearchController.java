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
public class ParkingRecordSearchController {

    @Autowired
    private ParkingRecordService parkingRecordService;

    /**
     * 根据车位ID或车牌号分页查询停车记录信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param parkingSpaceId 车位ID
     * @param plateNumber 车牌号
     * @return 停车记录信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据车位ID或车牌号分页查询停车记录信息", description = "根据车位ID或车牌号分页查询停车记录信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result search(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                         @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                         @Parameter(description = "车位ID") @RequestParam(required = false) Long parkingSpaceId,
                         @Parameter(description = "车牌号") @RequestParam(required = false) String plateNumber) {
        ParkingRecord parkingRecord = new ParkingRecord();
        parkingRecord.setParkingSpaceId(parkingSpaceId);
        parkingRecord.setPlateNumber(plateNumber);
        
        IPage<ParkingRecord> page = new Page<>(pageNum, pageSize);
        IPage<ParkingRecord> result = parkingRecordService.selectParkingRecordPage(page, parkingRecord);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
}