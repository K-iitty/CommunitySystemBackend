package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Owner;
import org.springframework.web.multipart.MultipartFile;

public interface OwnerService extends IService<Owner> {
    
    /**
     * 分页查询业主信息
     * @param page 分页对象
     * @param owner 查询条件
     * @return 业主信息分页数据
     */
    IPage<Owner> selectOwnerPage(IPage<Owner> page, Owner owner);

    /**
     * 根据业主姓名或手机号或业主类型或状态分页查询业主信息
     * @param page 分页对象
     * @param name 业主姓名
     * @param phone 手机号
     * @param ownerType 业主类型
     * @param status 状态
     * @return 业主信息分页数据
     */
    IPage<Owner> selectOwnerPageByMultiple(IPage<Owner> page, String name, String phone, String ownerType, String status);
    
    /**
     * 获取业主总数
     * @return 业主总数
     */
    long countAll();
    
    /**
     * 更新门禁照片
     * @param id 业主ID
     * @param files 门禁照片文件
     * @return 是否成功
     */
    boolean updateAccessControlPhotos(Long id, MultipartFile[] files);
    
    /**
     * 获取门禁照片
     * @param id 业主ID
     * @return 门禁照片URL列表
     */
    String getAccessControlPhotos(Long id);
    
    /**
     * 更新身份证照片
     * @param id 业主ID
     * @param files 身份证照片文件
     * @return 是否成功
     */
    boolean updateIdCardPhotos(Long id, MultipartFile[] files);
    
    /**
     * 获取身份证照片
     * @param id 业主ID
     * @return 身份证照片URL列表
     */
    String getIdCardPhotos(Long id);
}