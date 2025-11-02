package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.common.service.OssService;
import com.community.dao.StaffDao;
import com.community.domain.entity.Staff;
import com.community.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffDao, Staff> implements StaffService {
    
    @Autowired
    private OssService ossService;

    @Override
    public IPage<Staff> selectStaffPage(IPage<Staff> page, Staff staff) {
        LambdaQueryWrapper<Staff> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据用户名模糊查询
        if (StringUtils.isNotBlank(staff.getUsername())) {
            queryWrapper.like(Staff::getUsername, staff.getUsername());
        }
        
        // 根据姓名模糊查询
        if (StringUtils.isNotBlank(staff.getName())) {
            queryWrapper.like(Staff::getName, staff.getName());
        }
        
        // 根据手机号查询
        if (StringUtils.isNotBlank(staff.getPhone())) {
            queryWrapper.eq(Staff::getPhone, staff.getPhone());
        }
        
        // 根据工号查询
        if (StringUtils.isNotBlank(staff.getWorkNo())) {
            queryWrapper.eq(Staff::getWorkNo, staff.getWorkNo());
        }
        
        // 根据部门ID查询
        if (staff.getDepartmentId() != null) {
            queryWrapper.eq(Staff::getDepartmentId, staff.getDepartmentId());
        }
        
        // 根据角色ID查询
        if (staff.getRoleId() != null) {
            queryWrapper.eq(Staff::getRoleId, staff.getRoleId());
        }
        
        // 根据工作状态查询
        if (StringUtils.isNotBlank(staff.getWorkStatus())) {
            queryWrapper.eq(Staff::getWorkStatus, staff.getWorkStatus());
        }
        
        // 根据账号状态查询
        if (StringUtils.isNotBlank(staff.getAccountStatus())) {
            queryWrapper.eq(Staff::getAccountStatus, staff.getAccountStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Staff::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<Staff> selectStaffPageByMultiple(IPage<Staff> page, String name, String phone, String workNo, String workStatus) {
        LambdaQueryWrapper<Staff> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据姓名模糊查询
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Staff::getName, name);
        }
        
        // 根据手机号查询
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.eq(Staff::getPhone, phone);
        }
        
        // 根据工号查询
        if (StringUtils.isNotBlank(workNo)) {
            queryWrapper.eq(Staff::getWorkNo, workNo);
        }
        
        // 根据工作状态查询
        if (StringUtils.isNotBlank(workStatus)) {
            queryWrapper.eq(Staff::getWorkStatus, workStatus);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Staff::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateIdCardPhotos(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            // 限制最多2张身份证照片
            int count = Math.min(files.length, 2);
            for (int i = 0; i < count; i++) {
                MultipartFile file = files[i];
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "staff/id_card/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            Staff staff = this.getById(id);
            if (staff != null) {
                String photos = String.join(",", photoUrls);
                staff.setIdCardPhotos(photos);
                return this.updateById(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getIdCardPhotos(Long id) {
        if (id == null) {
            return null;
        }

        Staff staff = this.getById(id);
        return staff != null ? staff.getIdCardPhotos() : null;
    }

    @Override
    public boolean updateCertificatePhotos(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "staff/certificate/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            Staff staff = this.getById(id);
            if (staff != null) {
                String photos = String.join(",", photoUrls);
                staff.setCertificatePhotos(photos);
                return this.updateById(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getCertificatePhotos(Long id) {
        if (id == null) {
            return null;
        }

        Staff staff = this.getById(id);
        return staff != null ? staff.getCertificatePhotos() : null;
    }

    private String saveFile(MultipartFile file, String subPath) {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}