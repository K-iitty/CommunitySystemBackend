package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.ParkingLotDao;
import com.community.domain.entity.ParkingLot;
import com.community.service.ParkingLotService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl extends ServiceImpl<ParkingLotDao, ParkingLot> implements ParkingLotService {

    @Override
    public IPage<ParkingLot> selectParkingLotPage(IPage<ParkingLot> page, ParkingLot parkingLot) {
        LambdaQueryWrapper<ParkingLot> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (parkingLot.getCommunityId() != null) {
            queryWrapper.eq(ParkingLot::getCommunityId, parkingLot.getCommunityId());
        }
        
        // 根据停车场名称模糊查询
        if (StringUtils.isNotBlank(parkingLot.getLotName())) {
            queryWrapper.like(ParkingLot::getLotName, parkingLot.getLotName());
        }
        
        // 根据停车场编码查询
        if (StringUtils.isNotBlank(parkingLot.getLotCode())) {
            queryWrapper.eq(ParkingLot::getLotCode, parkingLot.getLotCode());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(parkingLot.getStatus())) {
            queryWrapper.eq(ParkingLot::getStatus, parkingLot.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(ParkingLot::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<ParkingLot> selectParkingLotPageByMultiple(IPage<ParkingLot> page, String lotName, String lotCode) {
        LambdaQueryWrapper<ParkingLot> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据停车场名称模糊查询
        if (StringUtils.isNotBlank(lotName)) {
            queryWrapper.like(ParkingLot::getLotName, lotName);
        }
        
        // 根据停车场编码查询
        if (StringUtils.isNotBlank(lotCode)) {
            queryWrapper.eq(ParkingLot::getLotCode, lotCode);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(ParkingLot::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}