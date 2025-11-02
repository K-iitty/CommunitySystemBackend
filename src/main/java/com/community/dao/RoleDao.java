package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
}