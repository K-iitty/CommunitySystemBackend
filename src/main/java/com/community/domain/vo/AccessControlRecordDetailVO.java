package com.community.domain.vo;

import com.community.domain.entity.AccessControlRecord;
import com.community.domain.entity.AccessControlDevice;
import com.community.domain.entity.Owner;
import com.community.domain.entity.Staff;
import lombok.Data;

import java.io.Serializable;

/**
 * 门禁记录详细信息VO类
 */
@Data
public class AccessControlRecordDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 门禁记录信息
     */
    private AccessControlRecord accessControlRecord;
    
    /**
     * 门禁设备信息
     */
    private AccessControlDevice accessControlDevice;
    
    /**
     * 人员信息（业主或物业）
     */
    private Object person; // 可能是Owner或Staff
    
    /**
     * 人员类型
     */
    private String personType; // "owner" 或 "staff"
}