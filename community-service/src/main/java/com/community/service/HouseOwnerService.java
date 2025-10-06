package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.HouseOwner;

public interface HouseOwnerService extends IService<HouseOwner> {
    
    /**
     * 分页查询房屋业主关联信息
     * @param page 分页对象
     * @param houseOwner 查询条件
     * @return 房屋业主关联信息分页数据
     */
    IPage<HouseOwner> selectHouseOwnerPage(IPage<HouseOwner> page, HouseOwner houseOwner);
}