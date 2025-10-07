package com.community.domain.vo;

import com.community.domain.entity.ParkingSpace;
import com.community.domain.entity.ParkingLot;
import lombok.Data;

import java.io.Serializable;

/**
 * 车位详细信息VO类（包含停车场信息）
 */
@Data
public class ParkingSpaceDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 车位信息
     */
    private ParkingSpace parkingSpace;
    
    /**
     * 停车场信息
     */
    private ParkingLot parkingLot;
}