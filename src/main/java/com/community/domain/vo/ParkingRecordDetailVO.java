package com.community.domain.vo;

import com.community.domain.entity.ParkingRecord;
import com.community.domain.entity.Vehicle;
import com.community.domain.entity.Owner;
import com.community.domain.entity.ParkingSpace;
import com.community.domain.entity.ParkingLot;
import lombok.Data;

import java.io.Serializable;

/**
 * 停车记录详细信息VO类
 */
@Data
public class ParkingRecordDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 停车记录信息
     */
    private ParkingRecord parkingRecord;
    
    /**
     * 车辆信息
     */
    private Vehicle vehicle;
    
    /**
     * 业主信息
     */
    private Owner owner;
    
    /**
     * 车位信息
     */
    private ParkingSpace parkingSpace;
    
    /**
     * 停车场信息
     */
    private ParkingLot parkingLot;
}