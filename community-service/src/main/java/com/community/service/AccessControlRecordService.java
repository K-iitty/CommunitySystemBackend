package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.AccessControlRecord;

public interface AccessControlRecordService extends IService<AccessControlRecord> {
    
    /**
     * 分页查询门禁记录信息
     * @param page 分页对象
     * @param accessControlRecord 查询条件
     * @return 门禁记录信息分页数据
     */
    IPage<AccessControlRecord> selectAccessControlRecordPage(IPage<AccessControlRecord> page, AccessControlRecord accessControlRecord);
}