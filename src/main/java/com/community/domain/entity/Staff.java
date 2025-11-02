package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 员工/物业表（整合版）实体类
 */
@Data
@TableName("staff")
public class Staff {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 登录账号
     */
    @TableField("username")
    private String username;
    
    /**
     * 登录密码
     */
    @TableField("password")
    private String password;
    
    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;
    
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    
    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;
    
    /**
     * 工号
     */
    @TableField("work_no")
    private String workNo;
    
    /**
     * 性别:男/女/保密
     */
    @TableField("gender")
    private String gender;
    
    /**
     * 员工生日
     */
    @TableField("birth_date")
    private LocalDate birthDate;
    
    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;
    
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    
    /**
     * 微信账号
     */
    @TableField("wechat")
    private String wechat;
    
    /**
     * 电话区号
     */
    @TableField("telephone_area_code")
    private String telephoneAreaCode;
    
    /**
     * 电话号码
     */
    @TableField("telephone_number")
    private String telephoneNumber;
    
    /**
     * 分机号码
     */
    @TableField("telephone_extension")
    private String telephoneExtension;
    
    /**
     * 紧急联系人
     */
    @TableField("emergency_contact")
    private String emergencyContact;
    
    /**
     * 紧急联系电话
     */
    @TableField("emergency_phone")
    private String emergencyPhone;
    
    /**
     * 毕业院校
     */
    @TableField("graduate_school")
    private String graduateSchool;
    
    /**
     * 毕业时间
     */
    @TableField("graduation_date")
    private LocalDate graduationDate;
    
    /**
     * 学历
     */
    @TableField("education_level")
    private String educationLevel;
    
    /**
     * 所学专业
     */
    @TableField("major")
    private String major;
    
    /**
     * 所属部门ID
     */
    @TableField("department_id")
    private Long departmentId;
    
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;
    
    /**
     * 职位
     */
    @TableField("position")
    private String position;
    
    /**
     * 职称
     */
    @TableField("job_title")
    private String jobTitle;
    
    /**
     * 入职日期
     */
    @TableField("hire_date")
    private LocalDate hireDate;
    
    /**
     * 工作状态:在职/休假/离职
     */
    @TableField("work_status")
    private String workStatus;
    
    /**
     * 是否可担任负责人
     */
    @TableField("is_manager")
    private Integer isManager;
    
    /**
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;
    
    /**
     * 账号状态:正常/禁用/锁定
     */
    @TableField("account_status")
    private String accountStatus;
    
    /**
     * 在线状态:0-离线,1-在线
     */
    @TableField("online_status")
    private Integer onlineStatus;
    
    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
    
    /**
     * 最后登录IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    
    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;

    /**
     * 身份证照片（JSON数组格式，0-2张）
     */
    @TableField("id_card_photos")
    private String idCardPhotos;

    /**
     * 证件照等（JSON数组格式，0-多张）
     */
    @TableField("certificate_photos")
    private String certificatePhotos;
    
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    
    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;
    
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    
    /**
     * 获取身份证照片URL列表
     * @return 身份证照片URL列表
     */
    public List<String> getIdCardPhotosList() {
        if (idCardPhotos == null || idCardPhotos.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(idCardPhotos.split(","));
    }
    
    /**
     * 获取证件照片URL列表
     * @return 证件照片URL列表
     */
    public List<String> getCertificatePhotosList() {
        if (certificatePhotos == null || certificatePhotos.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(certificatePhotos.split(","));
    }
    
    /**
     * 判断是否有身份证照片
     * @return 是否有身份证照片
     */
    public boolean hasIdCardPhotos() {
        return idCardPhotos != null && !idCardPhotos.isEmpty();
    }
    
    /**
     * 判断是否有证件照片
     * @return 是否有证件照片
     */
    public boolean hasCertificatePhotos() {
        return certificatePhotos != null && !certificatePhotos.isEmpty();
    }
}