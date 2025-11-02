package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.ParkingRecordDao;
import com.community.domain.entity.ParkingRecord;
import com.community.service.ParkingRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ParkingRecordServiceImpl extends ServiceImpl<ParkingRecordDao, ParkingRecord> implements ParkingRecordService {

    @Override
    public IPage<ParkingRecord> selectParkingRecordPage(IPage<ParkingRecord> page, ParkingRecord parkingRecord) {
        LambdaQueryWrapper<ParkingRecord> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据车辆ID查询
        if (parkingRecord.getVehicleId() != null) {
            queryWrapper.eq(ParkingRecord::getVehicleId, parkingRecord.getVehicleId());
        }
        
        // 根据车牌号模糊查询
        if (StringUtils.isNotBlank(parkingRecord.getPlateNumber())) {
            queryWrapper.like(ParkingRecord::getPlateNumber, parkingRecord.getPlateNumber());
        }
        
        // 根据车主ID查询
        if (parkingRecord.getOwnerId() != null) {
            queryWrapper.eq(ParkingRecord::getOwnerId, parkingRecord.getOwnerId());
        }
        
        // 根据停车场ID查询
        if (parkingRecord.getParkingLotId() != null) {
            queryWrapper.eq(ParkingRecord::getParkingLotId, parkingRecord.getParkingLotId());
        }
        
        // 根据车位ID查询
        if (parkingRecord.getParkingSpaceId() != null) {
            queryWrapper.eq(ParkingRecord::getParkingSpaceId, parkingRecord.getParkingSpaceId());
        }
        
        // 根据支付状态查询
        if (StringUtils.isNotBlank(parkingRecord.getPaymentStatus())) {
            queryWrapper.eq(ParkingRecord::getPaymentStatus, parkingRecord.getPaymentStatus());
        }
        
        // 默认按入场时间倒序排列
        queryWrapper.orderByDesc(ParkingRecord::getEntryTime);
        
        return this.page(page, queryWrapper);
    }
}