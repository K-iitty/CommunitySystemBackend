package com.community.domain.vo;

import com.community.domain.entity.IssueFollowUp;
import com.community.domain.entity.OwnerIssue;
import com.community.domain.entity.Staff;
import com.community.domain.entity.Owner;
import com.community.domain.entity.House;
import com.community.domain.entity.CommunityInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题跟进详细信息VO类
 */
@Data
public class IssueFollowUpDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 问题跟进信息
     */
    private IssueFollowUp issueFollowUp;
    
    /**
     * 问题信息
     */
    private OwnerIssue ownerIssue;
    
    /**
     * 跟进人信息（物业）
     */
    private Staff staff;
    
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
    
    /**
     * 设置房屋信息
     * @param house 房屋信息
     */
    public void setHouse(House house) {
        this.house = house;
    }
    
    /**
     * 设置社区信息
     * @param communityInfo 社区信息
     */
    public void setCommunityInfo(CommunityInfo communityInfo) {
        this.communityInfo = communityInfo;
    }
}