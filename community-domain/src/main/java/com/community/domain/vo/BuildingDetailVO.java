package com.community.domain.vo;

import com.community.domain.entity.Building;
import com.community.domain.entity.CommunityInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 楼栋详细信息VO类
 */
@Data
public class BuildingDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 楼栋信息
     */
    private Building building;
    
    /**
     * 社区信息
     */
    private CommunityInfo communityInfo;
}