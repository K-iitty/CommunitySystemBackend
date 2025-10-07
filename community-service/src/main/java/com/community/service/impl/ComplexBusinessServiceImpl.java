package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.community.dao.*;
import com.community.domain.entity.*;
import com.community.domain.vo.OwnerParkingInfoVO;
import com.community.domain.vo.ParkingSpaceDetailVO;
import com.community.domain.vo.ParkingLotDetailVO;
import com.community.service.ComplexBusinessService;
import com.community.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplexBusinessServiceImpl implements ComplexBusinessService {
    
    @Autowired
    private OwnerService ownerService;
    
    @Autowired
    private VehicleDao vehicleDao;
    
    @Autowired
    private ParkingSpaceDao parkingSpaceDao;
    
    @Autowired
    private HouseOwnerDao houseOwnerDao;
    
    @Autowired
    private MeterInfoDao meterInfoDao;
    
    @Autowired
    private MeterReadingDao meterReadingDao;
    
    @Autowired
    private StaffDao staffDao;
    
    @Autowired
    private OwnerIssueDao ownerIssueDao;
    
    @Autowired
    private OwnerDao ownerDao;
    
    @Autowired
    private HouseDao houseDao;
    
    @Autowired
    private CommunityNoticeDao communityNoticeDao;
    
    @Autowired
    private BuildingDao buildingDao;
    
    @Autowired
    private CommunityInfoDao communityInfoDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private ParkingLotDao parkingLotDao;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addVehicleForOwner(Long ownerId, Vehicle vehicle) {
        // 设置车主ID
        vehicle.setOwnerId(ownerId);
        // 保存车辆信息
        return vehicleDao.insert(vehicle) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addParkingSpaceForOwner(Long ownerId, ParkingSpace parkingSpace) {
        // 设置业主ID
        parkingSpace.setOwnerId(ownerId);
        // 保存车位信息
        return parkingSpaceDao.insert(parkingSpace) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean linkParkingSpaceToParkingLot(Long parkingSpaceId, Long parkingLotId) {
        ParkingSpace parkingSpace = parkingSpaceDao.selectById(parkingSpaceId);
        if (parkingSpace != null) {
            parkingSpace.setParkingLotId(parkingLotId);
            return parkingSpaceDao.updateById(parkingSpace) > 0;
        }
        return false;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean linkHouseToOwner(Long houseId, Long ownerId, String relationship) {
        HouseOwner houseOwner = new HouseOwner();
        houseOwner.setHouseId(houseId);
        houseOwner.setOwnerId(ownerId);
        houseOwner.setRelationship(relationship);
        houseOwner.setStatus("正常");
        return houseOwnerDao.insert(houseOwner) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFamilyMemberForOwner(Long ownerId, Owner familyMember) {
        // 保存家属信息
        if (ownerDao.insert(familyMember) > 0) {
            // 建立与原业主的关联关系
            Owner originalOwner = ownerDao.selectById(ownerId);
            if (originalOwner != null) {
                // 可以在这里添加关联逻辑，比如在备注中说明关系等
                return true;
            }
        }
        return false;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMeterInfoForOwnerHouse(Long ownerId, Long houseId, MeterInfo meterInfo) {
        // 设置房屋ID和社区ID
        meterInfo.setHouseId(houseId);
        House house = houseDao.selectById(houseId);
        if (house != null) {
            meterInfo.setCommunityId(house.getCommunityId());
        }
        // 保存仪表信息
        return meterInfoDao.insert(meterInfo) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean generateMeterChargeRecord(Long ownerId, MeterReading meterReading) {
        // 保存抄表记录
        return meterReadingDao.insert(meterReading) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMeterForOwner(Long ownerId, MeterInfo meterInfo) {
        // 保存仪表信息
        return meterInfoDao.insert(meterInfo) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setMeterCategory(Long meterId, Long categoryId) {
        MeterInfo meterInfo = meterInfoDao.selectById(meterId);
        if (meterInfo != null) {
            // 这里假设categoryId实际上是configId
            meterInfo.setConfigId(categoryId);
            return meterInfoDao.updateById(meterInfo) > 0;
        }
        return false;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignNoticeToBuilding(Long buildingId, CommunityNotice notice) {
        Building building = buildingDao.selectById(buildingId);
        if (building != null) {
            notice.setCommunityId(building.getCommunityId());
            // CommunityNotice实体类中没有buildingId字段，所以不设置
            return communityNoticeDao.insert(notice) > 0;
        }
        return false;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignNoticeToCommunity(Long communityId, CommunityNotice notice) {
        notice.setCommunityId(communityId);
        return communityNoticeDao.insert(notice) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignStaffToDepartment(Long staffId, Long departmentId) {
        Staff staff = new Staff();
        staff.setId(staffId);
        staff.setDepartmentId(departmentId);
        return staffDao.updateById(staff) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignIssueToStaff(Long issueId, Long staffId) {
        OwnerIssue ownerIssue = new OwnerIssue();
        ownerIssue.setId(issueId);
        ownerIssue.setAssignedStaffId(staffId);
        ownerIssue.setIssueStatus("处理中");
        return ownerIssueDao.updateById(ownerIssue) > 0;
    }
    
    @Override
    public OwnerParkingInfoVO getParkingInfoByOwnerId(Long ownerId) {
        OwnerParkingInfoVO vo = new OwnerParkingInfoVO();
        
        // 获取业主信息
        Owner owner = ownerDao.selectById(ownerId);
        vo.setOwner(owner);
        
        // 获取车位信息
        LambdaQueryWrapper<ParkingSpace> spaceWrapper = new LambdaQueryWrapper<>();
        spaceWrapper.eq(ParkingSpace::getOwnerId, ownerId);
        List<ParkingSpace> parkingSpaces = parkingSpaceDao.selectList(spaceWrapper);
        vo.setParkingSpaces(parkingSpaces);
        
        // 获取车辆信息
        LambdaQueryWrapper<Vehicle> vehicleWrapper = new LambdaQueryWrapper<>();
        vehicleWrapper.eq(Vehicle::getOwnerId, ownerId);
        List<Vehicle> vehicles = vehicleDao.selectList(vehicleWrapper);
        vo.setVehicles(vehicles);
        
        return vo;
    }
    
    @Override
    public ParkingSpaceDetailVO getParkingSpaceDetailById(Long parkingSpaceId) {
        ParkingSpaceDetailVO vo = new ParkingSpaceDetailVO();
        
        // 获取车位信息
        ParkingSpace parkingSpace = parkingSpaceDao.selectById(parkingSpaceId);
        vo.setParkingSpace(parkingSpace);
        
        // 获取停车场信息
        if (parkingSpace != null && parkingSpace.getParkingLotId() != null) {
            ParkingLot parkingLot = parkingLotDao.selectById(parkingSpace.getParkingLotId());
            vo.setParkingLot(parkingLot);
        }
        
        return vo;
    }
    
    @Override
    public ParkingLotDetailVO getParkingLotDetailById(Long parkingLotId) {
        ParkingLotDetailVO vo = new ParkingLotDetailVO();
        
        // 获取停车场信息
        ParkingLot parkingLot = parkingLotDao.selectById(parkingLotId);
        vo.setParkingLot(parkingLot);
        
        // 获取社区信息
        if (parkingLot != null && parkingLot.getCommunityId() != null) {
            CommunityInfo communityInfo = communityInfoDao.selectById(parkingLot.getCommunityId());
            vo.setCommunityInfo(communityInfo);
        }
        
        return vo;
    }
}