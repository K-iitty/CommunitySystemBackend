package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Building;

public interface BuildingService extends IService<Building> {
    
    /**
     * 分页查询楼栋信息
     * @param page 分页对象
     * @param building 查询条件
     * @return 楼栋信息分页数据
     */
    IPage<Building> selectBuildingPage(IPage<Building> page, Building building);
}