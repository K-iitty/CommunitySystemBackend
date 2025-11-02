package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.House;
import com.community.domain.vo.HouseDetailVO;
import org.springframework.web.multipart.MultipartFile;

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
    
    /**
     * 获取房屋总数
     * @return 房屋总数
     */
    long countAll();
    
    /**
     * 根据ID查询房屋详细信息
     * @param id 房屋ID
     * @return 房屋详细信息
     */
    HouseDetailVO getHouseDetailById(Long id);
    
    /**
     * 更新户型图
     * @param id 房屋ID
     * @param file 户型图文件
     * @return 是否成功
     */
    boolean updateFloorPlanImage(Long id, MultipartFile file);
    
    /**
     * 获取户型图
     * @param id 房屋ID
     * @return 户型图URL
     */
    String getFloorPlanImage(Long id);
}