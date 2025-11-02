package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.House;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseDao extends BaseMapper<House> {
}