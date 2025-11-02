package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.SystemAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemAdminDao extends BaseMapper<SystemAdmin> {
}