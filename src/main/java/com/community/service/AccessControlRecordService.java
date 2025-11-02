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

    /**
     * 根据设备ID或人员姓名或通行方式或验证结果分页查询门禁记录信息
     * @param page 分页对象
     * @param deviceId 设备ID
     * @param personName 人员姓名
     * @param accessMethod 通行方式
     * @param verifyResult 验证结果
     * @return 门禁记录信息分页数据
     */
    IPage<AccessControlRecord> selectAccessControlRecordPageByMultiple(IPage<AccessControlRecord> page, Long deviceId, String personName, String accessMethod, String verifyResult);
}