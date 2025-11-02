package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.HouseOwnerDao;
import com.community.domain.entity.House;
import com.community.domain.entity.HouseOwner;
import com.community.domain.entity.Owner;
import com.community.domain.vo.HouseOwnerVO;
import com.community.service.HouseOwnerService;
import com.community.service.HouseService;
import com.community.service.OwnerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseOwnerServiceImpl extends ServiceImpl<HouseOwnerDao, HouseOwner> implements HouseOwnerService {
    
    @Autowired
    private HouseService houseService;
    
    @Autowired
    private OwnerService ownerService;

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
    
    @Override
    public IPage<HouseOwnerVO> selectHouseOwnerVOPage(IPage<HouseOwnerVO> page, HouseOwner houseOwner) {
        // 先查询关联信息
        IPage<HouseOwner> houseOwnerPage = new Page<>(page.getCurrent(), page.getSize());
        IPage<HouseOwner> houseOwnerResult = selectHouseOwnerPage(houseOwnerPage, houseOwner);
        
        // 转换为VO对象
        Page<HouseOwnerVO> voPage = new Page<>(page.getCurrent(), page.getSize(), houseOwnerResult.getTotal());
        voPage.setRecords(houseOwnerResult.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
        
        return voPage;
    }
    
    @Override
    public HouseOwnerVO selectHouseOwnerVOById(Long id) {
        HouseOwner houseOwner = this.getById(id);
        if (houseOwner == null) {
            return null;
        }
        return convertToVO(houseOwner);
    }
    
    /**
     * 将HouseOwner转换为HouseOwnerVO
     * @param houseOwner 房屋业主关联信息
     * @return HouseOwnerVO
     */
    private HouseOwnerVO convertToVO(HouseOwner houseOwner) {
        HouseOwnerVO vo = new HouseOwnerVO();
        vo.setHouseOwner(houseOwner);
        
        // 查询房屋信息
        House house = houseService.getById(houseOwner.getHouseId());
        vo.setHouse(house);
        
        // 查询业主信息
        Owner owner = ownerService.getById(houseOwner.getOwnerId());
        vo.setOwner(owner);
        
        return vo;
    }
}