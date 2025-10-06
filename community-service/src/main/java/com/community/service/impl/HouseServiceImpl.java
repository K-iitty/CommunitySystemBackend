package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.HouseDao;
import com.community.domain.entity.House;
import com.community.service.HouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseDao, House> implements HouseService {

    @Override
    public IPage<House> selectHousePage(IPage<House> page, House house) {
        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (house.getCommunityId() != null) {
            queryWrapper.eq(House::getCommunityId, house.getCommunityId());
        }
        
        // 根据楼栋ID查询
        if (house.getBuildingId() != null) {
            queryWrapper.eq(House::getBuildingId, house.getBuildingId());
        }
        
        // 根据房间号模糊查询
        if (StringUtils.isNotBlank(house.getRoomNo())) {
            queryWrapper.like(House::getRoomNo, house.getRoomNo());
        }
        
        // 根据完整房间号模糊查询
        if (StringUtils.isNotBlank(house.getFullRoomNo())) {
            queryWrapper.like(House::getFullRoomNo, house.getFullRoomNo());
        }
        
        // 根据房屋状态查询
        if (StringUtils.isNotBlank(house.getHouseStatus())) {
            queryWrapper.eq(House::getHouseStatus, house.getHouseStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(House::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}