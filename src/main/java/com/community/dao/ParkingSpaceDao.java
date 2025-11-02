package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.ParkingSpace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingSpaceDao extends BaseMapper<ParkingSpace> {
}