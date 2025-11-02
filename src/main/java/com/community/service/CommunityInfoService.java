package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.CommunityInfo;
import com.community.domain.vo.CommunityStatisticsVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityInfoService extends IService<CommunityInfo> {
    
    /**
     * 分页查询社区信息
     * @param page 分页对象
     * @param communityInfo 查询条件
     * @return 社区信息分页数据
     */
    IPage<CommunityInfo> selectCommunityInfoPage(IPage<CommunityInfo> page, CommunityInfo communityInfo);

    /**
     * 根据社区名称或详细地址分页查询社区信息
     * @param page 分页对象
     * @param communityName 社区名称
     * @param detailAddress 详细地址
     * @return 社区信息分页数据
     */
    IPage<CommunityInfo> selectCommunityInfoPageByMultiple(IPage<CommunityInfo> page, String communityName, String detailAddress);
    
    /**
     * 获取社区总数
     * @return 社区总数
     */
    long countAll();
    
    /**
     * 更新社区图片
     * @param id 社区ID
     * @param files 社区图片文件
     * @return 是否成功
     */
    boolean updateCommunityImages(Long id, MultipartFile[] files);
    
    /**
     * 获取社区图片
     * @param id 社区ID
     * @return 社区图片URL列表
     */
    String getCommunityImages(Long id);
    
    /**
     * 统计各社区的房屋、业主、楼栋、停车场数量
     * @return 社区统计数据列表
     */
    List<CommunityStatisticsVO> getCommunityStatistics();
}