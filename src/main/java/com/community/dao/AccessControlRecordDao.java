package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.AccessControlRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessControlRecordDao extends BaseMapper<AccessControlRecord> {
}