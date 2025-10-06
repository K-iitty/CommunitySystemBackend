package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.CommunityInfo;

public interface CommunityInfoService extends IService<CommunityInfo> {
    
    /**
     * 分页查询社区信息
     * @param page 分页对象
     * @param communityInfo 查询条件
     * @return 社区信息分页数据
     */
    IPage<CommunityInfo> selectCommunityInfoPage(IPage<CommunityInfo> page, CommunityInfo communityInfo);
}