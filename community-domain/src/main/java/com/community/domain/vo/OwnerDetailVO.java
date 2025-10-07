package com.community.domain.vo;

import com.community.domain.entity.Owner;
import com.community.domain.entity.House;
import com.community.domain.entity.ParkingSpace;
import com.community.domain.entity.Vehicle;
import com.community.domain.entity.HouseOwner;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 业主详细信息VO类
 */
@Data
public class OwnerDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 业主基本信息
     */
    private Owner owner;
    
    /**
     * 房屋拥有信息列表
     */
    private List<HouseOwner> houseOwners;
    
    /**
     * 房屋信息列表
     */
    private List<House> houses;
    
    /**
     * 车位所属信息列表
     */
    private List<ParkingSpace> parkingSpaces;
    
    /**
     * 车辆所属信息列表
     */
    private List<Vehicle> vehicles;
}