package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.CommunityNotice;

public interface CommunityNoticeService extends IService<CommunityNotice> {
    
    /**
     * 分页查询社区公告信息
     * @param page 分页对象
     * @param communityNotice 查询条件
     * @return 社区公告信息分页数据
     */
    IPage<CommunityNotice> selectCommunityNoticePage(IPage<CommunityNotice> page, CommunityNotice communityNotice);
}