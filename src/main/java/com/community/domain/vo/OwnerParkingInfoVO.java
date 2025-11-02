package com.community.domain.vo;

import com.community.domain.entity.Owner;
import com.community.domain.entity.ParkingSpace;
import com.community.domain.entity.Vehicle;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 业主停车信息VO类（包含车位和车辆信息）
 */
@Data
public class OwnerParkingInfoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 业主信息
     */
    private Owner owner;
    
    /**
     * 车位信息列表
     */
    private List<ParkingSpace> parkingSpaces;
    
    /**
     * 车辆信息列表
     */
    private List<Vehicle> vehicles;
}