package com.community.domain.vo;

import com.community.domain.entity.Staff;
import com.community.domain.entity.Department;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工详细信息VO类
 */
@Data
public class StaffDetailVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 员工信息
     */
    private Staff staff;
    
    /**
     * 部门信息
     */
    private Department department;
}