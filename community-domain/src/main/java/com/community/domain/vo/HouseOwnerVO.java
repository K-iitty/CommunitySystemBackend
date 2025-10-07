package com.community.domain.vo;

import com.community.domain.entity.House;
import com.community.domain.entity.Owner;
import com.community.domain.entity.HouseOwner;
import lombok.Data;

import java.io.Serializable;

/**
 * 房屋业主关联信息VO类
 */
@Data
public class HouseOwnerVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 房屋业主关联信息
     */
    private HouseOwner houseOwner;
    
    /**
     * 房屋信息
     */
    private House house;
    
    /**
     * 业主信息
     */
    private Owner owner;
}