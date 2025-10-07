package com.community.domain.vo;

import com.community.domain.entity.Vehicle;
import com.community.domain.entity.Owner;
import lombok.Data;

import java.io.Serializable;

/**
 * 车辆详细信息VO类
 */
@Data
public class VehicleDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 车辆信息
     */
    private Vehicle vehicle;
    
    /**
     * 业主信息
     */
    private Owner owner;
}