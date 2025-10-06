package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.AccessControlDevice;

public interface AccessControlDeviceService extends IService<AccessControlDevice> {
    
    /**
     * 分页查询门禁设备信息
     * @param page 分页对象
     * @param accessControlDevice 查询条件
     * @return 门禁设备信息分页数据
     */
    IPage<AccessControlDevice> selectAccessControlDevicePage(IPage<AccessControlDevice> page, AccessControlDevice accessControlDevice);
}