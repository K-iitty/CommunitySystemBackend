package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.CommunityNotice;
import org.springframework.web.multipart.MultipartFile;

public interface CommunityNoticeService extends IService<CommunityNotice> {
    
    /**
     * 分页查询社区公告信息
     * @param page 分页对象
     * @param communityNotice 查询条件
     * @return 社区公告信息分页数据
     */
    IPage<CommunityNotice> selectCommunityNoticePage(IPage<CommunityNotice> page, CommunityNotice communityNotice);
    
    /**
     * 更新公告图片
     * @param id 公告ID
     * @param files 公告图片文件
     * @return 是否成功
     */
    boolean updateNoticeImages(Long id, MultipartFile[] files);
    
    /**
     * 获取公告图片
     * @param id 公告ID
     * @return 公告图片URL列表
     */
    String getNoticeImages(Long id);
}