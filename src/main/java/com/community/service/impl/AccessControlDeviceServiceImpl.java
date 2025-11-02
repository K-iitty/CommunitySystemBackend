package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.AccessControlDeviceDao;
import com.community.domain.entity.AccessControlDevice;
import com.community.service.AccessControlDeviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AccessControlDeviceServiceImpl extends ServiceImpl<AccessControlDeviceDao, AccessControlDevice> implements AccessControlDeviceService {

    @Override
    public IPage<AccessControlDevice> selectAccessControlDevicePage(IPage<AccessControlDevice> page, AccessControlDevice accessControlDevice) {
        LambdaQueryWrapper<AccessControlDevice> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据社区ID查询
        if (accessControlDevice.getCommunityId() != null) {
            queryWrapper.eq(AccessControlDevice::getCommunityId, accessControlDevice.getCommunityId());
        }
        
        // 根据楼栋ID查询
        if (accessControlDevice.getBuildingId() != null) {
            queryWrapper.eq(AccessControlDevice::getBuildingId, accessControlDevice.getBuildingId());
        }
        
        // 根据设备名称模糊查询
        if (StringUtils.isNotBlank(accessControlDevice.getDeviceName())) {
            queryWrapper.like(AccessControlDevice::getDeviceName, accessControlDevice.getDeviceName());
        }
        
        // 根据设备编码查询
        if (StringUtils.isNotBlank(accessControlDevice.getDeviceCode())) {
            queryWrapper.eq(AccessControlDevice::getDeviceCode, accessControlDevice.getDeviceCode());
        }
        
        // 根据设备状态查询
        if (StringUtils.isNotBlank(accessControlDevice.getDeviceStatus())) {
            queryWrapper.eq(AccessControlDevice::getDeviceStatus, accessControlDevice.getDeviceStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(AccessControlDevice::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<AccessControlDevice> selectAccessControlDevicePageByMultiple(IPage<AccessControlDevice> page, String deviceName, String deviceCode) {
        LambdaQueryWrapper<AccessControlDevice> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据设备名称模糊查询
        if (StringUtils.isNotBlank(deviceName)) {
            queryWrapper.like(AccessControlDevice::getDeviceName, deviceName);
        }
        
        // 根据设备编码查询
        if (StringUtils.isNotBlank(deviceCode)) {
            queryWrapper.eq(AccessControlDevice::getDeviceCode, deviceCode);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(AccessControlDevice::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}