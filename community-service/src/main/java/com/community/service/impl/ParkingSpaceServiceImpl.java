package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.ParkingSpaceDao;
import com.community.domain.entity.ParkingSpace;
import com.community.service.ParkingSpaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceServiceImpl extends ServiceImpl<ParkingSpaceDao, ParkingSpace> implements ParkingSpaceService {

    @Override
    public IPage<ParkingSpace> selectParkingSpacePage(IPage<ParkingSpace> page, ParkingSpace parkingSpace) {
        LambdaQueryWrapper<ParkingSpace> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据停车场ID查询
        if (parkingSpace.getParkingLotId() != null) {
            queryWrapper.eq(ParkingSpace::getParkingLotId, parkingSpace.getParkingLotId());
        }
        
        // 根据车位编号模糊查询
        if (StringUtils.isNotBlank(parkingSpace.getSpaceNo())) {
            queryWrapper.like(ParkingSpace::getSpaceNo, parkingSpace.getSpaceNo());
        }
        
        // 根据车位类型查询
        if (StringUtils.isNotBlank(parkingSpace.getSpaceType())) {
            queryWrapper.eq(ParkingSpace::getSpaceType, parkingSpace.getSpaceType());
        }
        
        // 根据车位状态查询
        if (StringUtils.isNotBlank(parkingSpace.getSpaceStatus())) {
            queryWrapper.eq(ParkingSpace::getSpaceStatus, parkingSpace.getSpaceStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(ParkingSpace::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<ParkingSpace> selectParkingSpacePageByMultiple(IPage<ParkingSpace> page, String spaceNo) {
        LambdaQueryWrapper<ParkingSpace> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据车位编号模糊查询
        if (StringUtils.isNotBlank(spaceNo)) {
            queryWrapper.like(ParkingSpace::getSpaceNo, spaceNo);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(ParkingSpace::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}