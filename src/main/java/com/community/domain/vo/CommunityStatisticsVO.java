package com.community.domain.vo;

import lombok.Data;

@Data
public class CommunityStatisticsVO {
    /**
     * 社区ID
     */
    private Long communityId;
    
    /**
     * 社区名称
     */
    private String communityName;
    
    /**
     * 房屋总数
     */
    private Long houseCount;
    
    /**
     * 业主总数
     */
    private Long ownerCount;
    
    /**
     * 楼栋总数
     */
    private Long buildingCount;
    
    /**
     * 停车场总数
     */
    private Long parkingLotCount;
}