package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.CommunityInfoDao;
import com.community.domain.entity.CommunityInfo;
import com.community.domain.entity.House;
import com.community.domain.entity.HouseOwner;
import com.community.domain.entity.Owner;
import com.community.domain.entity.Building;
import com.community.domain.entity.ParkingLot;
import com.community.domain.entity.Vehicle;
import com.community.domain.vo.CommunityStatisticsVO;
import com.community.service.CommunityInfoService;
import com.community.service.HouseOwnerService;
import com.community.service.BuildingService;
import com.community.service.ParkingLotService;
import com.community.common.service.OssService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityInfoServiceImpl extends ServiceImpl<CommunityInfoDao, CommunityInfo> implements CommunityInfoService {
    
    @Autowired
    private OssService ossService;
    
    @Autowired
    private HouseServiceImpl houseService;
    
    @Autowired
    private OwnerServiceImpl ownerService;
    
    @Autowired
    private VehicleServiceImpl vehicleService;
    
    @Autowired
    private HouseOwnerService houseOwnerService;
    
    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private ParkingLotService parkingLotService;

    @Override
    public IPage<CommunityInfo> selectCommunityInfoPage(IPage<CommunityInfo> page, CommunityInfo communityInfo) {
        LambdaQueryWrapper<CommunityInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区名称模糊查询
        if (StringUtils.isNotBlank(communityInfo.getCommunityName())) {
            queryWrapper.like(CommunityInfo::getCommunityName, communityInfo.getCommunityName());
        }
        
        // 根据社区编码查询
        if (StringUtils.isNotBlank(communityInfo.getCommunityCode())) {
            queryWrapper.eq(CommunityInfo::getCommunityCode, communityInfo.getCommunityCode());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(communityInfo.getStatus())) {
            queryWrapper.eq(CommunityInfo::getStatus, communityInfo.getStatus());
        }
        
        // 根据物业公司查询
        if (StringUtils.isNotBlank(communityInfo.getPropertyCompany())) {
            queryWrapper.like(CommunityInfo::getPropertyCompany, communityInfo.getPropertyCompany());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(CommunityInfo::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<CommunityInfo> selectCommunityInfoPageByMultiple(IPage<CommunityInfo> page, String communityName, String detailAddress) {
        LambdaQueryWrapper<CommunityInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区名称模糊查询
        if (StringUtils.isNotBlank(communityName)) {
            queryWrapper.like(CommunityInfo::getCommunityName, communityName);
        }
        
        // 根据详细地址模糊查询
        if (StringUtils.isNotBlank(detailAddress)) {
            queryWrapper.like(CommunityInfo::getDetailAddress, detailAddress);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(CommunityInfo::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
    
    @Override
    public long countAll() {
        return this.count();
    }

    @Override
    public boolean updateCommunityImages(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "community/info/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            CommunityInfo communityInfo = this.getById(id);
            if (communityInfo != null) {
                String photos = String.join(",", photoUrls);
                communityInfo.setCommunityImages(photos);
                return this.updateById(communityInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getCommunityImages(Long id) {
        if (id == null) {
            return null;
        }

        CommunityInfo communityInfo = this.getById(id);
        return communityInfo != null ? communityInfo.getCommunityImages() : null;
    }
    
    @Override
    public List<CommunityStatisticsVO> getCommunityStatistics() {
        // 获取所有社区信息
        List<CommunityInfo> communities = this.list();
        
        // 构建返回结果
        return communities.stream().map(community -> {
            CommunityStatisticsVO vo = new CommunityStatisticsVO();
            vo.setCommunityId(community.getId());
            vo.setCommunityName(community.getCommunityName());
            
            // 统计房屋数量
            LambdaQueryWrapper<House> houseQueryWrapper = new LambdaQueryWrapper<>();
            houseQueryWrapper.eq(House::getCommunityId, community.getId());
            vo.setHouseCount(houseService.count(houseQueryWrapper));
            
            // 统计业主数量 (通过房屋业主关联表统计)
            // 先获取该社区下的所有房屋
            LambdaQueryWrapper<House> houseQuery = new LambdaQueryWrapper<>();
            houseQuery.eq(House::getCommunityId, community.getId());
            List<House> houses = houseService.list(houseQuery);
            
            // 获取这些房屋关联的业主数量
            if (!houses.isEmpty()) {
                List<Long> houseIds = houses.stream().map(House::getId).collect(Collectors.toList());
                LambdaQueryWrapper<HouseOwner> houseOwnerQueryWrapper = new LambdaQueryWrapper<>();
                houseOwnerQueryWrapper.in(HouseOwner::getHouseId, houseIds);
                vo.setOwnerCount(houseOwnerService.count(houseOwnerQueryWrapper));
            } else {
                vo.setOwnerCount(0L);
            }
            
            // 统计楼栋数量
            LambdaQueryWrapper<Building> buildingQueryWrapper = new LambdaQueryWrapper<>();
            buildingQueryWrapper.eq(Building::getCommunityId, community.getId());
            vo.setBuildingCount(buildingService.count(buildingQueryWrapper));
            
            // 统计停车场数量
            LambdaQueryWrapper<ParkingLot> parkingLotQueryWrapper = new LambdaQueryWrapper<>();
            parkingLotQueryWrapper.eq(ParkingLot::getCommunityId, community.getId());
            vo.setParkingLotCount(parkingLotService.count(parkingLotQueryWrapper));
            
            return vo;
        }).collect(Collectors.toList());
    }

    private String saveFile(MultipartFile file, String subPath) {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}