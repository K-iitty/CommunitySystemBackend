package com.community.domain.vo;

import com.community.domain.entity.OwnerIssue;
import com.community.domain.entity.Owner;
import com.community.domain.entity.House;
import com.community.domain.entity.CommunityInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 业主问题详细信息VO类
 */
@Data
public class OwnerIssueDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 业主问题信息
     */
    private OwnerIssue ownerIssue;
    
    /**
     * 业主信息
     */
    private Owner owner;
    
    /**
     * 房屋信息
     */
    private House house;
    
    /**
     * 社区信息
     */
    private CommunityInfo communityInfo;
}