package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.BuildingDao;
import com.community.domain.entity.Building;
import com.community.service.BuildingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingDao, Building> implements BuildingService {

    @Override
    public IPage<Building> selectBuildingPage(IPage<Building> page, Building building) {
        LambdaQueryWrapper<Building> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (building.getCommunityId() != null) {
            queryWrapper.eq(Building::getCommunityId, building.getCommunityId());
        }
        
        // 根据楼栋编号模糊查询
        if (StringUtils.isNotBlank(building.getBuildingNo())) {
            queryWrapper.like(Building::getBuildingNo, building.getBuildingNo());
        }
        
        // 根据楼栋名称模糊查询
        if (StringUtils.isNotBlank(building.getBuildingName())) {
            queryWrapper.like(Building::getBuildingName, building.getBuildingName());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(building.getStatus())) {
            queryWrapper.eq(Building::getStatus, building.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Building::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}