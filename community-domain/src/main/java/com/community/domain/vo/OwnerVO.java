package com.community.domain.vo;

import com.community.domain.entity.Owner;
import lombok.Data;

import java.io.Serializable;

/**
 * 业主信息VO类
 */
@Data
public class OwnerVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 业主信息
     */
    private Owner owner;
}