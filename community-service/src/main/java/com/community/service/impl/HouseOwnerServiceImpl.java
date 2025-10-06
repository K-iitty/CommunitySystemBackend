package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.HouseOwnerDao;
import com.community.domain.entity.HouseOwner;
import com.community.service.HouseOwnerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HouseOwnerServiceImpl extends ServiceImpl<HouseOwnerDao, HouseOwner> implements HouseOwnerService {

    @Override
    public IPage<HouseOwner> selectHouseOwnerPage(IPage<HouseOwner> page, HouseOwner houseOwner) {
        LambdaQueryWrapper<HouseOwner> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据房屋ID查询
        if (houseOwner.getHouseId() != null) {
            queryWrapper.eq(HouseOwner::getHouseId, houseOwner.getHouseId());
        }
        
        // 根据业主ID查询
        if (houseOwner.getOwnerId() != null) {
            queryWrapper.eq(HouseOwner::getOwnerId, houseOwner.getOwnerId());
        }
        
        // 根据关系类型查询
        if (StringUtils.isNotBlank(houseOwner.getRelationship())) {
            queryWrapper.eq(HouseOwner::getRelationship, houseOwner.getRelationship());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(houseOwner.getStatus())) {
            queryWrapper.eq(HouseOwner::getStatus, houseOwner.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(HouseOwner::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}