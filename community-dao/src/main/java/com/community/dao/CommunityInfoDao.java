package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.CommunityInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityInfoDao extends BaseMapper<CommunityInfo> {
}