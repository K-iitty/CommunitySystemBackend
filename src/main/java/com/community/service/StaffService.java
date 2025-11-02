package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Staff;
import org.springframework.web.multipart.MultipartFile;

public interface StaffService extends IService<Staff> {
    
    /**
     * 分页查询员工信息
     * @param page 分页对象
     * @param staff 查询条件
     * @return 员工信息分页数据
     */
    IPage<Staff> selectStaffPage(IPage<Staff> page, Staff staff);

    /**
     * 根据员工姓名或手机号或工号或工作状态分页查询员工信息
     * @param page 分页对象
     * @param name 员工姓名
     * @param phone 手机号
     * @param workNo 工号
     * @param workStatus 工作状态
     * @return 员工信息分页数据
     */
    IPage<Staff> selectStaffPageByMultiple(IPage<Staff> page, String name, String phone, String workNo, String workStatus);
    
    /**
     * 更新身份证照片
     * @param id 员工ID
     * @param files 身份证照片文件
     * @return 是否成功
     */
    boolean updateIdCardPhotos(Long id, MultipartFile[] files);
    
    /**
     * 获取身份证照片
     * @param id 员工ID
     * @return 身份证照片URL列表
     */
    String getIdCardPhotos(Long id);
    
    /**
     * 更新证件照
     * @param id 员工ID
     * @param files 证件照文件
     * @return 是否成功
     */
    boolean updateCertificatePhotos(Long id, MultipartFile[] files);
    
    /**
     * 获取证件照
     * @param id 员工ID
     * @return 证件照URL列表
     */
    String getCertificatePhotos(Long id);
}