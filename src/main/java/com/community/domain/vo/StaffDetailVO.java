package com.community.domain.vo;

import com.community.domain.entity.Staff;
import com.community.domain.entity.Department;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    
    /**
     * 获取员工身份证照片URL列表
     * @return 身份证照片URL列表
     */
    public List<String> getStaffIdCardPhotosList() {
        if (staff != null) {
            return staff.getIdCardPhotosList();
        }
        return List.of();
    }
    
    /**
     * 获取员工证件照片URL列表
     * @return 证件照片URL列表
     */
    public List<String> getStaffCertificatePhotosList() {
        if (staff != null) {
            return staff.getCertificatePhotosList();
        }
        return List.of();
    }
    
    /**
     * 判断员工是否有身份证照片
     * @return 是否有身份证照片
     */
    public boolean hasStaffIdCardPhotos() {
        if (staff != null) {
            return staff.hasIdCardPhotos();
        }
        return false;
    }
    
    /**
     * 判断员工是否有证件照片
     * @return 是否有证件照片
     */
    public boolean hasStaffCertificatePhotos() {
        if (staff != null) {
            return staff.hasCertificatePhotos();
        }
        return false;
    }
}