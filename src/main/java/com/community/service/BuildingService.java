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

    /**
     * 根据楼栋名称或楼栋编号分页查询楼栋信息
     * @param page 分页对象
     * @param buildingName 楼栋名称
     * @param buildingNo 楼栋编号
     * @return 楼栋信息分页数据
     */
    IPage<Building> selectBuildingPageByMultiple(IPage<Building> page, String buildingName, String buildingNo);
    
    /**
     * 获取楼栋总数
     * @return 楼栋总数
     */
    long countAll();
}