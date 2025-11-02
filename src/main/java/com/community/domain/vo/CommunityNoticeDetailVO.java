package com.community.domain.vo;

import com.community.domain.entity.CommunityNotice;
import com.community.domain.entity.CommunityInfo;
import com.community.domain.entity.SystemAdmin;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 社区公告详细信息VO类
 */
@Data
public class CommunityNoticeDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 社区公告信息
     */
    private CommunityNotice communityNotice;
    
    /**
     * 社区信息
     */
    private CommunityInfo communityInfo;
    
    /**
     * 发布人信息
     */
    private SystemAdmin publisher;
    
    /**
     * 获取公告图片URL列表
     * @return 公告图片URL列表
     */
    public List<String> getNoticeImagesList() {
        if (communityNotice != null) {
            return communityNotice.getNoticeImagesList();
        }
        return List.of();
    }
    
    /**
     * 判断公告是否有图片
     * @return 是否有公告图片
     */
    public boolean hasNoticeImages() {
        if (communityNotice != null) {
            return communityNotice.hasNoticeImages();
        }
        return false;
    }
}