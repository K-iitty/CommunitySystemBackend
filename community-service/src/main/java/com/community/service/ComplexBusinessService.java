package com.community.service;

import com.community.domain.vo.*;

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
    
    /**
     * 根据业主ID查询车位和车辆信息
     * @param ownerId 业主ID
     * @return 业主停车信息
     */
    OwnerParkingInfoVO getParkingInfoByOwnerId(Long ownerId);
    
    /**
     * 根据车位ID查询车位和停车场详细信息
     * @param parkingSpaceId 车位ID
     * @return 车位详细信息
     */
    ParkingSpaceDetailVO getParkingSpaceDetailById(Long parkingSpaceId);
    
    /**
     * 根据停车场ID查询停车场和社区详细信息
     * @param parkingLotId 停车场ID
     * @return 停车场详细信息
     */
    ParkingLotDetailVO getParkingLotDetailById(Long parkingLotId);
    
    /**
     * 根据业主ID查询仪表信息和仪表配置信息
     * @param ownerId 业主ID
     * @return 业主仪表信息
     */
    OwnerMeterVO getMeterInfoByOwnerId(Long ownerId);
    
    /**
     * 根据抄表ID查询抄表员信息和业主信息
     * @param readingId 抄表记录ID
     * @return 抄表记录详细信息
     */
    MeterReadingDetailVO getMeterReadingDetailById(Long readingId);
    
    /**
     * 根据抄表记录中的仪表ID查询仪表信息和仪表配置信息
     * @param meterId 仪表ID
     * @return 仪表详细信息
     */
    MeterDetailVO getMeterDetailByMeterId(Long meterId);
    
    /**
     * 根据楼栋ID获取楼栋信息和社区信息
     * @param buildingId 楼栋ID
     * @return 楼栋详细信息
     */
    BuildingDetailVO getBuildingDetailById(Long buildingId);
    
    /**
     * 根据房屋ID查询房屋信息、楼栋信息、社区信息
     * @param houseId 房屋ID
     * @return 房屋详细信息
     */
    HouseDetailVO getHouseDetailById(Long houseId);
    
    /**
     * 新增房屋业主关联
     * @param ownerId 业主ID
     * @param houseId 房屋ID
     * @return 是否成功
     */
    boolean addHouseOwnerRelation(Long ownerId, Long houseId);
    
    /**
     * 根据停车场ID查询停车场信息和社区信息
     * @param parkingLotId 停车场ID
     * @return 停车场详细信息
     */
    ParkingLotDetailVO getParkingLotDetailWithCommunityById(Long parkingLotId);
    
    /**
     * 根据车位ID查询停车场信息和车位信息
     * @param spaceId 车位ID
     * @return 车位详细信息
     */
    ParkingSpaceDetailVO getParkingSpaceDetailWithLotById(Long spaceId);
    
    /**
     * 根据车辆ID查询车辆信息和业主信息
     * @param vehicleId 车辆ID
     * @return 车辆详细信息
     */
    VehicleDetailVO getVehicleDetailById(Long vehicleId);
    
    /**
     * 根据停车记录ID查询车辆信息、业主信息、停车位信息和停车场信息
     * @param recordId 停车记录ID
     * @return 停车记录详细信息
     */
    ParkingRecordDetailVO getParkingRecordDetailById(Long recordId);
    
    /**
     * 根据门禁设备ID查询楼栋信息、社区信息和门禁设备信息
     * @param deviceId 门禁设备ID
     * @return 门禁设备详细信息
     */
    AccessControlDeviceDetailVO getAccessControlDeviceDetailById(Long deviceId);
    
    /**
     * 根据门禁记录ID查询人员信息和门禁设备信息
     * @param recordId 门禁记录ID
     * @return 门禁记录详细信息
     */
    AccessControlRecordDetailVO getAccessControlRecordDetailById(Long recordId);
    
    /**
     * 根据仪表信息ID查询仪表配置信息和所属的房屋信息或楼栋信息
     * @param meterId 仪表信息ID
     * @return 仪表信息详细信息
     */
    MeterInfoDetailVO getMeterInfoDetailById(Long meterId);
    
    /**
     * 根据抄表记录ID查询仪表信息和抄表记录信息
     * @param readingId 抄表记录ID
     * @return 抄表记录和仪表信息
     */
    MeterReadingDetailVO getMeterReadingAndMeterInfoById(Long readingId);
    
    /**
     * 根据员工ID查询部门信息和员工信息
     * @param staffId 员工ID
     * @return 员工详细信息
     */
    StaffDetailVO getStaffDetailById(Long staffId);
    
    /**
     * 根据业主ID查询基本信息、房屋拥有信息、车位所属信息、车辆所属信息
     * @param ownerId 业主ID
     * @return 业主详细信息
     */
    OwnerDetailVO getOwnerDetailById(Long ownerId);
    
    /**
     * 根据社区公告ID获取社区公告信息和社区信息
     * @param noticeId 社区公告ID
     * @return 社区公告详细信息
     */
    CommunityNoticeDetailVO getCommunityNoticeDetailById(Long noticeId);
    
    /**
     * 根据问题ID查询业主信息、房屋信息、社区信息
     * @param issueId 问题ID
     * @return 问题详细信息
     */
    OwnerIssueDetailVO getOwnerIssueDetailById(Long issueId);
    
    /**
     * 根据问题跟进ID获取问题信息、跟进人信息、业主信息
     * @param followUpId 问题跟进ID
     * @return 问题跟进详细信息
     */
    IssueFollowUpDetailVO getIssueFollowUpDetailById(Long followUpId);
}