package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.ParkingLot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingLotDao extends BaseMapper<ParkingLot> {
}