package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.*;
import com.community.domain.entity.*;
import com.community.domain.vo.*;
import com.community.service.ComplexBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplexBusinessServiceImpl extends ServiceImpl<CommunityInfoDao, CommunityInfo> implements ComplexBusinessService {
    
    @Autowired
    private VehicleDao vehicleDao;
    
    @Autowired
    private OwnerDao ownerDao;
    
    @Autowired
    private HouseDao houseDao;
    
    @Autowired
    private BuildingDao buildingDao;
    
    @Autowired
    private ParkingSpaceDao parkingSpaceDao;
    
    @Autowired
    private ParkingLotDao parkingLotDao;
    
    @Autowired
    private ParkingRecordDao parkingRecordDao;
    
    @Autowired
    private AccessControlDeviceDao accessControlDeviceDao;
    
    @Autowired
    private MeterInfoDao meterInfoDao;
    
    @Autowired
    private MeterConfigDao meterConfigDao;
    
    @Autowired
    private MeterReadingDao meterReadingDao;
    
    @Autowired
    private StaffDao staffDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private HouseOwnerDao houseOwnerDao;
    
    @Autowired
    private CommunityInfoDao communityInfoDao;
    
    @Autowired
    private CommunityNoticeDao communityNoticeDao;
    
    @Autowired
    private OwnerIssueDao ownerIssueDao;
    
    @Autowired
    private IssueFollowUpDao issueFollowUpDao;
    
    @Autowired
    private SystemAdminDao systemAdminDao;
    
    @Autowired
    private AccessControlRecordDao accessControlRecordDao;
    
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
            meterInfo.setConfigId(categoryId);
            return meterInfoDao.updateById(meterInfo) > 0;
        }
        return false;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignNoticeToBuilding(Long buildingId, CommunityNotice notice) {
        // 保存公告信息
        return communityNoticeDao.insert(notice) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignNoticeToCommunity(Long communityId, CommunityNotice notice) {
        // 设置社区ID
        notice.setCommunityId(communityId);
        // 保存公告信息
        return communityNoticeDao.insert(notice) > 0;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignStaffToDepartment(Long staffId, Long departmentId) {
        Staff staff = staffDao.selectById(staffId);
        if (staff != null) {
            staff.setDepartmentId(departmentId);
            return staffDao.updateById(staff) > 0;
        }
        return false;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assignIssueToStaff(Long issueId, Long staffId) {
        OwnerIssue ownerIssue = ownerIssueDao.selectById(issueId);
        if (ownerIssue != null) {
            ownerIssue.setAssignedStaffId(staffId);
            ownerIssue.setIssueStatus("处理中");
            return ownerIssueDao.updateById(ownerIssue) > 0;
        }
        return false;
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
    public VehicleDetailVO getVehicleDetailById(Long vehicleId) {
        VehicleDetailVO vo = new VehicleDetailVO();
        
        // 获取车辆信息
        Vehicle vehicle = vehicleDao.selectById(vehicleId);
        vo.setVehicle(vehicle);
        
        // 获取业主信息
        if (vehicle != null && vehicle.getOwnerId() != null) {
            Owner owner = ownerDao.selectById(vehicle.getOwnerId());
            vo.setOwner(owner);
        }
        
        return vo;
    }
    
    @Override
    public ParkingRecordDetailVO getParkingRecordDetailById(Long recordId) {
        ParkingRecordDetailVO vo = new ParkingRecordDetailVO();
        
        // 获取停车记录信息
        ParkingRecord parkingRecord = parkingRecordDao.selectById(recordId);
        vo.setParkingRecord(parkingRecord);
        
        // 获取车辆信息
        if (parkingRecord != null && parkingRecord.getVehicleId() != null) {
            Vehicle vehicle = vehicleDao.selectById(parkingRecord.getVehicleId());
            vo.setVehicle(vehicle);
            
            // 获取业主信息
            if (vehicle != null && vehicle.getOwnerId() != null) {
                Owner owner = ownerDao.selectById(vehicle.getOwnerId());
                vo.setOwner(owner);
            }
        }
        
        // 获取车位信息
        if (parkingRecord != null && parkingRecord.getParkingSpaceId() != null) {
            ParkingSpace parkingSpace = parkingSpaceDao.selectById(parkingRecord.getParkingSpaceId());
            vo.setParkingSpace(parkingSpace);
            
            // 获取停车场信息
            if (parkingSpace != null && parkingSpace.getParkingLotId() != null) {
                ParkingLot parkingLot = parkingLotDao.selectById(parkingSpace.getParkingLotId());
                vo.setParkingLot(parkingLot);
            }
        }
        
        return vo;
    }
    
    @Override
    public AccessControlDeviceDetailVO getAccessControlDeviceDetailById(Long deviceId) {
        AccessControlDeviceDetailVO vo = new AccessControlDeviceDetailVO();
        
        // 获取门禁设备信息
        AccessControlDevice accessControlDevice = accessControlDeviceDao.selectById(deviceId);
        vo.setAccessControlDevice(accessControlDevice);
        
        // 获取楼栋信息
        if (accessControlDevice != null && accessControlDevice.getBuildingId() != null) {
            Building building = buildingDao.selectById(accessControlDevice.getBuildingId());
            vo.setBuilding(building);
            
            // 获取社区信息
            if (building != null && building.getCommunityId() != null) {
                CommunityInfo communityInfo = communityInfoDao.selectById(building.getCommunityId());
                vo.setCommunityInfo(communityInfo);
            }
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
    public MeterDetailVO getMeterDetailByMeterId(Long meterId) {
        MeterDetailVO vo = new MeterDetailVO();
        
        // 获取仪表信息
        MeterInfo meterInfo = meterInfoDao.selectById(meterId);
        vo.setMeterInfo(meterInfo);
        
        // 获取仪表配置信息
        if (meterInfo != null && meterInfo.getConfigId() != null) {
            MeterConfig meterConfig = meterConfigDao.selectById(meterInfo.getConfigId());
            vo.setMeterConfig(meterConfig);
        }
        
        return vo;
    }
    
    @Override
    public MeterReadingDetailVO getMeterReadingDetailById(Long readingId) {
        MeterReadingDetailVO vo = new MeterReadingDetailVO();
        
        // 获取抄表记录信息
        MeterReading meterReading = meterReadingDao.selectById(readingId);
        vo.setMeterReading(meterReading);
        
        // 获取仪表信息和业主信息
        if (meterReading != null && meterReading.getMeterId() != null) {
            MeterInfo meterInfo = meterInfoDao.selectById(meterReading.getMeterId());
            // 获取业主信息
            if (meterInfo != null && meterInfo.getHouseId() != null) {
                LambdaQueryWrapper<HouseOwner> houseOwnerWrapper = new LambdaQueryWrapper<>();
                houseOwnerWrapper.eq(HouseOwner::getHouseId, meterInfo.getHouseId());
                List<HouseOwner> houseOwners = houseOwnerDao.selectList(houseOwnerWrapper);
                if (!houseOwners.isEmpty()) {
                    Owner owner = ownerDao.selectById(houseOwners.get(0).getOwnerId());
                    vo.setOwner(owner);
                }
            }
        }
        
        return vo;
    }
    
    @Override
    public MeterReadingDetailVO getMeterReadingAndMeterInfoById(Long readingId) {
        return getMeterReadingDetailById(readingId);
    }
    
    @Override
    public BuildingDetailVO getBuildingDetailById(Long buildingId) {
        BuildingDetailVO vo = new BuildingDetailVO();
        
        // 获取楼栋信息
        Building building = buildingDao.selectById(buildingId);
        vo.setBuilding(building);
        
        // 获取社区信息
        if (building != null && building.getCommunityId() != null) {
            CommunityInfo communityInfo = communityInfoDao.selectById(building.getCommunityId());
            vo.setCommunityInfo(communityInfo);
        }
        
        return vo;
    }
    
    @Override
    public HouseDetailVO getHouseDetailById(Long id) {
        if (id == null) {
            return null;
        }
        
        House house = houseDao.selectById(id);
        if (house == null) {
            return null;
        }
        
        HouseDetailVO vo = new HouseDetailVO();
        vo.setHouse(house);
        vo.setFloorPlanImage(house.getFloorPlanImage()); // 确保设置户型图字段
        
        // 获取楼栋信息
        if (house.getBuildingId() != null) {
            Building building = buildingDao.selectById(house.getBuildingId());
            vo.setBuilding(building);
            
            // 获取社区信息
            if (building != null && building.getCommunityId() != null) {
                CommunityInfo communityInfo = communityInfoDao.selectById(building.getCommunityId());
                vo.setCommunityInfo(communityInfo);
            }
        }
        
        return vo;
    }
    
    @Override
    public boolean addHouseOwnerRelation(Long ownerId, Long houseId) {
        HouseOwner houseOwner = new HouseOwner();
        houseOwner.setOwnerId(ownerId);
        houseOwner.setHouseId(houseId);
        houseOwner.setRelationship("业主");
        houseOwner.setStatus("正常");
        houseOwner.setIsPrimary(1);
        return houseOwnerDao.insert(houseOwner) > 0;
    }
    
    @Override
    public ParkingLotDetailVO getParkingLotDetailWithCommunityById(Long parkingLotId) {
        return getParkingLotDetailById(parkingLotId);
    }
    
    @Override
    public ParkingSpaceDetailVO getParkingSpaceDetailWithLotById(Long spaceId) {
        return getParkingSpaceDetailById(spaceId);
    }
    
    @Override
    public StaffDetailVO getStaffDetailById(Long staffId) {
        StaffDetailVO vo = new StaffDetailVO();
        
        // 获取员工信息
        Staff staff = staffDao.selectById(staffId);
        vo.setStaff(staff);
        
        // 获取部门信息
        if (staff != null && staff.getDepartmentId() != null) {
            Department department = departmentDao.selectById(staff.getDepartmentId());
            vo.setDepartment(department);
        }
        
        return vo;
    }
    
    @Override
    public OwnerDetailVO getOwnerDetailById(Long ownerId) {
        OwnerDetailVO vo = new OwnerDetailVO();
        
        // 获取业主基本信息
        Owner owner = ownerDao.selectById(ownerId);
        vo.setOwner(owner);
        
        // 获取房屋拥有信息
        LambdaQueryWrapper<HouseOwner> houseOwnerWrapper = new LambdaQueryWrapper<>();
        houseOwnerWrapper.eq(HouseOwner::getOwnerId, ownerId);
        List<HouseOwner> houseOwners = houseOwnerDao.selectList(houseOwnerWrapper);
        vo.setHouseOwners(houseOwners);
        
        // 获取房屋信息
        if (!houseOwners.isEmpty()) {
            List<Long> houseIds = houseOwners.stream().map(HouseOwner::getHouseId).toList();
            LambdaQueryWrapper<House> houseWrapper = new LambdaQueryWrapper<>();
            houseWrapper.in(House::getId, houseIds);
            List<House> houses = houseDao.selectList(houseWrapper);
            vo.setHouses(houses);
        }
        
        // 获取车位所属信息
        LambdaQueryWrapper<ParkingSpace> parkingSpaceWrapper = new LambdaQueryWrapper<>();
        parkingSpaceWrapper.eq(ParkingSpace::getOwnerId, ownerId);
        List<ParkingSpace> parkingSpaces = parkingSpaceDao.selectList(parkingSpaceWrapper);
        vo.setParkingSpaces(parkingSpaces);
        
        // 获取车辆所属信息
        LambdaQueryWrapper<Vehicle> vehicleWrapper = new LambdaQueryWrapper<>();
        vehicleWrapper.eq(Vehicle::getOwnerId, ownerId);
        List<Vehicle> vehicles = vehicleDao.selectList(vehicleWrapper);
        vo.setVehicles(vehicles);
        
        return vo;
    }
    
    @Override
    public CommunityNoticeDetailVO getCommunityNoticeDetailById(Long noticeId) {
        CommunityNoticeDetailVO vo = new CommunityNoticeDetailVO();
        
        // 获取社区公告信息
        CommunityNotice communityNotice = communityNoticeDao.selectById(noticeId);
        vo.setCommunityNotice(communityNotice);
        
        // 获取社区信息
        if (communityNotice != null && communityNotice.getCommunityId() != null) {
            CommunityInfo communityInfo = communityInfoDao.selectById(communityNotice.getCommunityId());
            vo.setCommunityInfo(communityInfo);
        }
        
        // 获取发布人信息
        if (communityNotice != null && communityNotice.getCreatedBy() != null) {
            SystemAdmin publisher = systemAdminDao.selectById(communityNotice.getCreatedBy());
            vo.setPublisher(publisher);
        }
        
        return vo;
    }
    
    @Override
    public OwnerIssueDetailVO getOwnerIssueDetailById(Long issueId) {
        OwnerIssueDetailVO vo = new OwnerIssueDetailVO();
        
        // 获取业主问题信息
        OwnerIssue ownerIssue = ownerIssueDao.selectById(issueId);
        vo.setOwnerIssue(ownerIssue);
        
        // 获取业主信息
        if (ownerIssue != null && ownerIssue.getOwnerId() != null) {
            Owner owner = ownerDao.selectById(ownerIssue.getOwnerId());
            vo.setOwner(owner);
        }
        
        // 获取房屋信息
        if (ownerIssue != null && ownerIssue.getHouseId() != null) {
            House house = houseDao.selectById(ownerIssue.getHouseId());
            vo.setHouse(house);
            
            // 获取社区信息
            if (house != null && house.getCommunityId() != null) {
                CommunityInfo communityInfo = communityInfoDao.selectById(house.getCommunityId());
                vo.setCommunityInfo(communityInfo);
            }
        }
        
        return vo;
    }
    
    @Override
    public IssueFollowUpDetailVO getIssueFollowUpDetailById(Long followUpId) {
        IssueFollowUpDetailVO vo = new IssueFollowUpDetailVO();
        
        // 获取问题跟进信息
        IssueFollowUp issueFollowUp = issueFollowUpDao.selectById(followUpId);
        vo.setIssueFollowUp(issueFollowUp);
        
        // 获取问题信息
        if (issueFollowUp != null && issueFollowUp.getIssueId() != null) {
            OwnerIssue ownerIssue = ownerIssueDao.selectById(issueFollowUp.getIssueId());
            vo.setOwnerIssue(ownerIssue);
            
            // 获取业主信息
            if (ownerIssue != null && ownerIssue.getOwnerId() != null) {
                Owner owner = ownerDao.selectById(ownerIssue.getOwnerId());
                vo.setOwner(owner);
            }
            
            // 获取房屋信息
            if (ownerIssue != null && ownerIssue.getHouseId() != null) {
                House house = houseDao.selectById(ownerIssue.getHouseId());
                vo.setHouse(house);
                
                // 获取社区信息
                if (house != null && house.getCommunityId() != null) {
                    CommunityInfo communityInfo = communityInfoDao.selectById(house.getCommunityId());
                    vo.setCommunityInfo(communityInfo);
                }
            }
        }
        
        return vo;
    }
    
    @Override
    public OwnerMeterVO getMeterInfoByOwnerId(Long ownerId) {
        OwnerMeterVO vo = new OwnerMeterVO();
        
        // 获取业主关联的房屋信息
        LambdaQueryWrapper<HouseOwner> houseOwnerWrapper = new LambdaQueryWrapper<>();
        houseOwnerWrapper.eq(HouseOwner::getOwnerId, ownerId);
        List<HouseOwner> houseOwners = houseOwnerDao.selectList(houseOwnerWrapper);
        
        // 获取房屋关联的仪表信息
        if (!houseOwners.isEmpty()) {
            List<Long> houseIds = houseOwners.stream().map(HouseOwner::getHouseId).toList();
            LambdaQueryWrapper<MeterInfo> meterWrapper = new LambdaQueryWrapper<>();
            meterWrapper.in(MeterInfo::getHouseId, houseIds);
            List<MeterInfo> meterInfos = meterInfoDao.selectList(meterWrapper);
            vo.setMeterInfos(meterInfos);
            
            // 获取仪表配置信息
            List<Long> configIds = meterInfos.stream()
                    .filter(meter -> meter.getConfigId() != null)
                    .map(MeterInfo::getConfigId)
                    .distinct()
                    .toList();
            
            if (!configIds.isEmpty()) {
                LambdaQueryWrapper<MeterConfig> configWrapper = new LambdaQueryWrapper<>();
                configWrapper.in(MeterConfig::getId, configIds);
                List<MeterConfig> meterConfigs = meterConfigDao.selectList(configWrapper);
                vo.setMeterConfigs(meterConfigs);
            }
        }
        
        return vo;
    }
    
    @Override
    public AccessControlRecordDetailVO getAccessControlRecordDetailById(Long recordId) {
        AccessControlRecordDetailVO vo = new AccessControlRecordDetailVO();
        
        // 获取门禁记录信息
        AccessControlRecord accessControlRecord = accessControlRecordDao.selectById(recordId);
        vo.setAccessControlRecord(accessControlRecord);
        
        // 获取门禁设备信息
        if (accessControlRecord != null && accessControlRecord.getDeviceId() != null) {
            AccessControlDevice device = accessControlDeviceDao.selectById(accessControlRecord.getDeviceId());
            vo.setAccessControlDevice(device);
        }
        
        return vo;
    }
    
    @Override
    public MeterInfoDetailVO getMeterInfoDetailById(Long meterId) {
        MeterInfoDetailVO vo = new MeterInfoDetailVO();
        
        // 获取仪表信息
        MeterInfo meterInfo = meterInfoDao.selectById(meterId);
        vo.setMeterInfo(meterInfo);
        
        // 获取房屋信息
        if (meterInfo != null && meterInfo.getHouseId() != null) {
            House house = houseDao.selectById(meterInfo.getHouseId());
            vo.setHouse(house);
            
            // 获取楼栋信息
            if (house != null && house.getBuildingId() != null) {
                Building building = buildingDao.selectById(house.getBuildingId());
                vo.setBuilding(building);
                
                // 获取社区信息
                if (building != null && building.getCommunityId() != null) {
                    CommunityInfo communityInfo = communityInfoDao.selectById(building.getCommunityId());
                    vo.setCommunityInfo(communityInfo);
                }
            }
        }
        
        // 获取仪表配置信息
        if (meterInfo != null && meterInfo.getConfigId() != null) {
            MeterConfig meterConfig = meterConfigDao.selectById(meterInfo.getConfigId());
            vo.setMeterConfig(meterConfig);
        }
        
        return vo;
    }
}