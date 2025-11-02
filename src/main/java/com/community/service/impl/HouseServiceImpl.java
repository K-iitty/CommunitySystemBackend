package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.common.service.OssService;
import com.community.dao.HouseDao;
import com.community.domain.entity.House;
import com.community.domain.vo.HouseDetailVO;
import com.community.service.HouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseDao, House> implements HouseService {
    
    @Autowired
    private OssService ossService;

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

    @Override
    public IPage<House> selectHousePageByMultiple(IPage<House> page, String houseCode, String roomNo) {
        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据房屋编码精确查询
        if (StringUtils.isNotBlank(houseCode)) {
            queryWrapper.eq(House::getHouseCode, houseCode);
        }
        
        // 根据房间号模糊查询
        if (StringUtils.isNotBlank(roomNo)) {
            queryWrapper.like(House::getRoomNo, roomNo);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(House::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
    
    @Override
    public long countAll() {
        return this.count();
    }

    @Override
    public HouseDetailVO getHouseDetailById(Long id) {
        if (id == null) {
            return null;
        }
        
        House house = this.getById(id);
        if (house == null) {
            return null;
        }
        
        HouseDetailVO vo = new HouseDetailVO();
        BeanUtils.copyProperties(house, vo);
        // 确保floorPlanImage字段被正确设置
        vo.setFloorPlanImage(house.getFloorPlanImage());
        return vo;
    }

    @Override
    public boolean updateFloorPlanImage(Long id, MultipartFile file) {
        if (id == null || file == null || file.isEmpty()) {
            return false;
        }

        try {
            // 保存文件到OSS并获取URL
            String url = ossService.uploadFile(file, "house/floor_plan/");

            // 更新数据库
            House house = this.getById(id);
            if (house != null) {
                house.setFloorPlanImage(url);
                return this.updateById(house);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getFloorPlanImage(Long id) {
        if (id == null) {
            return null;
        }

        House house = this.getById(id);
        return house != null ? house.getFloorPlanImage() : null;
    }

    private String saveFile(MultipartFile file, String subPath) {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}