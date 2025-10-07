package com.community.domain.vo;

import com.community.domain.entity.ParkingLot;
import com.community.domain.entity.CommunityInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 停车场详细信息VO类（包含社区信息）
 */
@Data
public class ParkingLotDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 停车场信息
     */
    private ParkingLot parkingLot;
    
    /**
     * 社区信息
     */
    private CommunityInfo communityInfo;
}