package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.AccessControlRecordDao;
import com.community.domain.entity.AccessControlRecord;
import com.community.service.AccessControlRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AccessControlRecordServiceImpl extends ServiceImpl<AccessControlRecordDao, AccessControlRecord> implements AccessControlRecordService {

    @Override
    public IPage<AccessControlRecord> selectAccessControlRecordPage(IPage<AccessControlRecord> page, AccessControlRecord accessControlRecord) {
        LambdaQueryWrapper<AccessControlRecord> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (accessControlRecord.getCommunityId() != null) {
            queryWrapper.eq(AccessControlRecord::getCommunityId, accessControlRecord.getCommunityId());
        }
        
        // 根据设备ID查询
        if (accessControlRecord.getDeviceId() != null) {
            queryWrapper.eq(AccessControlRecord::getDeviceId, accessControlRecord.getDeviceId());
        }
        
        // 根据人员ID查询
        if (accessControlRecord.getPersonId() != null) {
            queryWrapper.eq(AccessControlRecord::getPersonId, accessControlRecord.getPersonId());
        }
        
        // 根据人员类型查询
        if (StringUtils.isNotBlank(accessControlRecord.getPersonType())) {
            queryWrapper.eq(AccessControlRecord::getPersonType, accessControlRecord.getPersonType());
        }
        
        // 根据姓名模糊查询
        if (StringUtils.isNotBlank(accessControlRecord.getPersonName())) {
            queryWrapper.like(AccessControlRecord::getPersonName, accessControlRecord.getPersonName());
        }
        
        // 根据出入类型查询
        if (StringUtils.isNotBlank(accessControlRecord.getAccessType())) {
            queryWrapper.eq(AccessControlRecord::getAccessType, accessControlRecord.getAccessType());
        }
        
        // 根据验证结果查询
        if (StringUtils.isNotBlank(accessControlRecord.getVerifyResult())) {
            queryWrapper.eq(AccessControlRecord::getVerifyResult, accessControlRecord.getVerifyResult());
        }
        
        // 默认按出入时间倒序排列
        queryWrapper.orderByDesc(AccessControlRecord::getAccessTime);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<AccessControlRecord> selectAccessControlRecordPageByMultiple(IPage<AccessControlRecord> page, Long deviceId, String personName, String accessMethod, String verifyResult) {
        LambdaQueryWrapper<AccessControlRecord> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据设备ID查询
        if (deviceId != null) {
            queryWrapper.eq(AccessControlRecord::getDeviceId, deviceId);
        }
        
        // 根据人员姓名模糊查询
        if (StringUtils.isNotBlank(personName)) {
            queryWrapper.like(AccessControlRecord::getPersonName, personName);
        }
        
        // 根据通行方式查询
        if (StringUtils.isNotBlank(accessMethod)) {
            queryWrapper.eq(AccessControlRecord::getAccessMethod, accessMethod);
        }
        
        // 根据验证结果查询
        if (StringUtils.isNotBlank(verifyResult)) {
            queryWrapper.eq(AccessControlRecord::getVerifyResult, verifyResult);
        }
        
        // 默认按出入时间倒序排列
        queryWrapper.orderByDesc(AccessControlRecord::getAccessTime);
        
        return this.page(page, queryWrapper);
    }
}