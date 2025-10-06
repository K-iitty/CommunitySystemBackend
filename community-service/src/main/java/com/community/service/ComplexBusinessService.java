package com.community.service;

public interface ComplexBusinessService {
    
    /**
     * 为业主添加车辆信息
     * @param ownerId 业主ID
     * @param vehicle 车辆信息
     * @return 是否成功
     */
    boolean addVehicleForOwner(Long ownerId, com.community.domain.entity.Vehicle vehicle);
    
    /**
     * 为业主添加车位信息
     * @param ownerId 业主ID
     * @param parkingSpace 车位信息
     * @return 是否成功
     */
    boolean addParkingSpaceForOwner(Long ownerId, com.community.domain.entity.ParkingSpace parkingSpace);
    
    /**
     * 为车位关联停车场信息
     * @param parkingSpaceId 车位ID
     * @param parkingLotId 停车场ID
     * @return 是否成功
     */
    boolean linkParkingSpaceToParkingLot(Long parkingSpaceId, Long parkingLotId);
    
    /**
     * 为房屋关联业主信息
     * @param houseId 房屋ID
     * @param ownerId 业主ID
     * @param relationship 关系类型
     * @return 是否成功
     */
    boolean linkHouseToOwner(Long houseId, Long ownerId, String relationship);
    
    /**
     * 为业主关联家属信息
     * @param ownerId 业主ID
     * @param familyMember 家属信息
     * @return 是否成功
     */
    boolean addFamilyMemberForOwner(Long ownerId, com.community.domain.entity.Owner familyMember);
    
    /**
     * 为业主关联房屋的仪表信息
     * @param ownerId 业主ID
     * @param houseId 房屋ID
     * @param meterInfo 仪表信息
     * @return 是否成功
     */
    boolean addMeterInfoForOwnerHouse(Long ownerId, Long houseId, com.community.domain.entity.MeterInfo meterInfo);
    
    /**
     * 为业主生成仪表收费记录
     * @param ownerId 业主ID
     * @param meterReading 抄表记录
     * @return 是否成功
     */
    boolean generateMeterChargeRecord(Long ownerId, com.community.domain.entity.MeterReading meterReading);
    
    /**
     * 为业主添加仪表
     * @param ownerId 业主ID
     * @param meterInfo 仪表信息
     * @return 是否成功
     */
    boolean addMeterForOwner(Long ownerId, com.community.domain.entity.MeterInfo meterInfo);
    
    /**
     * 为仪表设置分类
     * @param meterId 仪表ID
     * @param categoryId 分类ID
     * @return 是否成功
     */
    boolean setMeterCategory(Long meterId, Long categoryId);
    
    /**
     * 为楼栋分配公告
     * @param buildingId 楼栋ID
     * @param notice 公告信息
     * @return 是否成功
     */
    boolean assignNoticeToBuilding(Long buildingId, com.community.domain.entity.CommunityNotice notice);
    
    /**
     * 为社区分配公告
     * @param communityId 社区ID
     * @param notice 公告信息
     * @return 是否成功
     */
    boolean assignNoticeToCommunity(Long communityId, com.community.domain.entity.CommunityNotice notice);
    
    /**
     * 为物业人员设置不同的部门
     * @param staffId 物业人员ID
     * @param departmentId 部门ID
     * @return 是否成功
     */
    boolean assignStaffToDepartment(Long staffId, Long departmentId);
    
    /**
     * 将业主问题分配给对应类型的物业人员负责解决问题
     * @param issueId 问题ID
     * @param staffId 物业人员ID
     * @return 是否成功
     */
    boolean assignIssueToStaff(Long issueId, Long staffId);
}