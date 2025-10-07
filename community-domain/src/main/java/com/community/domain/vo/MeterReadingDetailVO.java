package com.community.domain.vo;

import com.community.domain.entity.MeterReading;
import com.community.domain.entity.Staff;
import com.community.domain.entity.Owner;
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
}