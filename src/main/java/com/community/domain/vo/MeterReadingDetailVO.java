package com.community.domain.vo;

import com.community.domain.entity.MeterReading;
import com.community.domain.entity.Staff;
import com.community.domain.entity.Owner;
import com.community.domain.entity.MeterInfo;
import com.community.domain.entity.MeterConfig;
import lombok.Data;

import java.io.Serializable;

/**
 * 抄表记录详细信息VO类
 */
@Data
public class MeterReadingDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 抄表记录信息
     */
    private MeterReading meterReading;
    
    /**
     * 抄表员信息（物业人员）
     */
    private Staff staff;
    
    /**
     * 业主信息
     */
    private Owner owner;
    
    /**
     * 仪表信息
     */
    private MeterInfo meterInfo;
    
    /**
     * 仪表配置信息
     */
    private MeterConfig meterConfig;
    
    /**
     * 设置仪表信息
     * @param meterInfo 仪表信息
     */
    public void setMeterInfo(MeterInfo meterInfo) {
        this.meterInfo = meterInfo;
    }
    
    /**
     * 设置仪表配置信息
     * @param meterConfig 仪表配置信息
     */
    public void setMeterConfig(MeterConfig meterConfig) {
        this.meterConfig = meterConfig;
    }
}