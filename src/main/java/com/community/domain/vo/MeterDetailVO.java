package com.community.domain.vo;

import com.community.domain.entity.MeterInfo;
import com.community.domain.entity.MeterConfig;
import lombok.Data;

import java.io.Serializable;

/**
 * 仪表详细信息VO类
 */
@Data
public class MeterDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 仪表信息
     */
    private MeterInfo meterInfo;
    
    /**
     * 仪表配置信息
     */
    private MeterConfig meterConfig;
}