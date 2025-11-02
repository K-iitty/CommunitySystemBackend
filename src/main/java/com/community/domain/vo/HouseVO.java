package com.community.domain.vo;

import com.community.domain.entity.House;
import com.community.domain.entity.CommunityInfo;
import com.community.domain.entity.Building;
import lombok.Data;

import java.io.Serializable;

/**
 * 房屋信息VO类
 */
@Data
public class HouseVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 房屋信息
     */
    private House house;
    
    /**
     * 社区信息
     */
    private CommunityInfo communityInfo;
    
    /**
     * 楼栋信息
     */
    private Building building;
    
    /**
     * 判断是否有户型图
     * @return 是否有户型图
     */
    public boolean hasFloorPlanImage() {
        if (house != null) {
            return house.hasFloorPlanImage();
        }
        return false;
    }
}