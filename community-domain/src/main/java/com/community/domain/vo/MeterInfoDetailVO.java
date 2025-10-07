package com.community.domain.vo;

import com.community.domain.entity.MeterInfo;
import com.community.domain.entity.MeterConfig;
import com.community.domain.entity.House;
import com.community.domain.entity.Building;
import lombok.Data;

import java.io.Serializable;

/**
 * 仪表信息详细信息VO类
 */
@Data
public class MeterInfoDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 仪表信息
     */
    private MeterInfo meterInfo;
    
    /**
     * 仪表配置信息
     */
    private MeterConfig meterConfig;
    
    /**
     * 房屋信息（如果仪表关联到房屋）
     */
    private House house;
    
    /**
     * 楼栋信息（如果仪表关联到楼栋）
     */
    private Building building;
}