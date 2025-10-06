package com.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.domain.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffDao extends BaseMapper<Staff> {
}