package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.CommunityNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityNoticeDao extends BaseMapper<CommunityNotice> {
}