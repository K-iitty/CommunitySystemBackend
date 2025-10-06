package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.HouseOwner;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseOwnerDao extends BaseMapper<HouseOwner> {
}