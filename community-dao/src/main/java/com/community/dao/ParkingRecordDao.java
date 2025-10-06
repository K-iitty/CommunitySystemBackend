package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.ParkingRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingRecordDao extends BaseMapper<ParkingRecord> {
}