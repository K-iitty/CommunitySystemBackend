package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.Owner;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerDao extends BaseMapper<Owner> {
}