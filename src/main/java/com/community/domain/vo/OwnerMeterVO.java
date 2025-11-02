package com.community.domain.vo;

import com.community.domain.entity.MeterInfo;
import com.community.domain.entity.MeterConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 业主仪表信息VO类
 */
@Data
public class OwnerMeterVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 仪表信息列表
     */
    private List<MeterInfo> meters;
    
    /**
     * 仪表配置信息列表
     */
    private List<MeterConfig> meterConfigs;
    
    /**
     * 设置仪表信息列表
     * @param meters 仪表信息列表
     */
    public void setMeterInfos(List<MeterInfo> meters) {
        this.meters = meters;
    }
    
    /**
     * 设置仪表配置信息列表
     * @param meterConfigs 仪表配置信息列表
     */
    public void setMeterConfigs(List<MeterConfig> meterConfigs) {
        this.meterConfigs = meterConfigs;
    }
}