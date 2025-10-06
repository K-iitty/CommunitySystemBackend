package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.House;

public interface HouseService extends IService<House> {
    
    /**
     * 分页查询房屋信息
     * @param page 分页对象
     * @param house 查询条件
     * @return 房屋信息分页数据
     */
    IPage<House> selectHousePage(IPage<House> page, House house);

    /**
     * 根据房屋编码或房号分页查询房屋信息
     * @param page 分页对象
     * @param houseCode 房屋编码
     * @param roomNo 房号
     * @return 房屋信息分页数据
     */
    IPage<House> selectHousePageByMultiple(IPage<House> page, String houseCode, String roomNo);
}