package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.MeterReading;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeterReadingDao extends BaseMapper<MeterReading> {
}