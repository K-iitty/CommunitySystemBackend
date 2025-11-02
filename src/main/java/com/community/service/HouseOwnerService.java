package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.HouseOwner;
import com.community.domain.vo.HouseOwnerVO;

public interface HouseOwnerService extends IService<HouseOwner> {
    
    /**
     * 分页查询房屋业主关联信息
     * @param page 分页对象
     * @param houseOwner 查询条件
     * @return 房屋业主关联信息分页数据
     */
    IPage<HouseOwner> selectHouseOwnerPage(IPage<HouseOwner> page, HouseOwner houseOwner);
    
    /**
     * 分页查询房屋业主关联详细信息
     * @param page 分页对象
     * @param houseOwner 查询条件
     * @return 房屋业主关联详细信息分页数据
     */
    IPage<HouseOwnerVO> selectHouseOwnerVOPage(IPage<HouseOwnerVO> page, HouseOwner houseOwner);
    
    /**
     * 根据ID查询房屋业主关联详细信息
     * @param id 关联ID
     * @return 房屋业主关联详细信息
     */
    HouseOwnerVO selectHouseOwnerVOById(Long id);
}