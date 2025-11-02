package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.Staff;
import com.community.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/staff")
@Tag(name = "员工信息管理", description = "员工信息相关接口")
@ApiSupport(order = 13, author = "社区管理系统开发团队")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 分页查询员工信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param staff 查询条件
     * @return 员工信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询员工信息", description = "根据条件分页查询员工信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Staff staff) {
        IPage<Staff> page = new Page<>(pageNum, pageSize);
        IPage<Staff> result = staffService.selectStaffPage(page, staff);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据员工姓名或手机号或工号或工作状态分页查询员工信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param name 员工姓名
     * @param phone 手机号
     * @param workNo 工号
     * @param workStatus 工作状态
     * @return 员工信息分页数据
     */
    @GetMapping("/search")
    @Operation(
        summary = "根据员工姓名或手机号或工号或工作状态分页查询员工信息",
        description = "根据员工姓名或手机号或工号或工作状态分页查询员工信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "name", description = "员工姓名"),
            @Parameter(name = "phone", description = "手机号"),
            @Parameter(name = "workNo", description = "工号"),
            @Parameter(name = "workStatus", description = "工作状态")
        }
    )
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "员工姓名") @RequestParam(required = false) String name,
                                   @Parameter(description = "手机号") @RequestParam(required = false) String phone,
                                   @Parameter(description = "工号") @RequestParam(required = false) String workNo,
                                   @Parameter(description = "工作状态") @RequestParam(required = false) String workStatus) {
        IPage<Staff> page = new Page<>(pageNum, pageSize);
        IPage<Staff> result = staffService.selectStaffPageByMultiple(page, name, phone, workNo, workStatus);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询员工信息
     *
     * @param id 员工ID
     * @return 员工信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询员工信息", description = "根据ID查询指定员工信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "员工ID", required = true, example = "1") @PathVariable Long id) {
        Staff staff = staffService.getById(id);
        return Result.ok().put("data", staff);
    }

    /**
     * 新增员工信息
     *
     * @param username 登录账号
     * @param password 登录密码
     * @param name 员工姓名
     * @param phone 手机号
     * @param idCard 身份证号
     * @param workNo 工号
     * @param gender 性别
     * @param birthDate 生日
     * @param avatar 头像
     * @param email 邮箱
     * @param wechat 微信账号
     * @param telephoneAreaCode 电话区号
     * @param telephoneNumber 电话号码
     * @param telephoneExtension 分机号码
     * @param emergencyContact 紧急联系人
     * @param emergencyPhone 紧急联系电话
     * @param graduateSchool 毕业院校
     * @param graduationDate 毕业时间
     * @param educationLevel 学历
     * @param major 所学专业
     * @param departmentId 所属部门ID
     * @param roleId 角色ID
     * @param position 职位
     * @param jobTitle 职称
     * @param hireDate 入职日期
     * @param workStatus 工作状态
     * @param isManager 是否可担任负责人
     * @param nativePlace 籍贯
     * @param accountStatus 账号状态
     * @param remark 备注
     * @param idCardPhotos 身份证照片文件（可选）
     * @param certificatePhotos 证件照文件（可选）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增员工信息", description = "新增员工信息（支持文件上传）")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(
            @Parameter(description = "登录账号") @RequestParam(required = false) String username,
            @Parameter(description = "登录密码") @RequestParam(required = false) String password,
            @Parameter(description = "员工姓名") @RequestParam(required = false) String name,
            @Parameter(description = "手机号") @RequestParam(required = false) String phone,
            @Parameter(description = "身份证号") @RequestParam(required = false) String idCard,
            @Parameter(description = "工号") @RequestParam(required = false) String workNo,
            @Parameter(description = "性别") @RequestParam(required = false) String gender,
            @Parameter(description = "生日") @RequestParam(required = false) String birthDate,
            @Parameter(description = "头像") @RequestParam(required = false) String avatar,
            @Parameter(description = "邮箱") @RequestParam(required = false) String email,
            @Parameter(description = "微信账号") @RequestParam(required = false) String wechat,
            @Parameter(description = "电话区号") @RequestParam(required = false) String telephoneAreaCode,
            @Parameter(description = "电话号码") @RequestParam(required = false) String telephoneNumber,
            @Parameter(description = "分机号码") @RequestParam(required = false) String telephoneExtension,
            @Parameter(description = "紧急联系人") @RequestParam(required = false) String emergencyContact,
            @Parameter(description = "紧急联系电话") @RequestParam(required = false) String emergencyPhone,
            @Parameter(description = "毕业院校") @RequestParam(required = false) String graduateSchool,
            @Parameter(description = "毕业时间") @RequestParam(required = false) String graduationDate,
            @Parameter(description = "学历") @RequestParam(required = false) String educationLevel,
            @Parameter(description = "所学专业") @RequestParam(required = false) String major,
            @Parameter(description = "所属部门ID") @RequestParam(required = false) Long departmentId,
            @Parameter(description = "角色ID") @RequestParam(required = false) Long roleId,
            @Parameter(description = "职位") @RequestParam(required = false) String position,
            @Parameter(description = "职称") @RequestParam(required = false) String jobTitle,
            @Parameter(description = "入职日期") @RequestParam(required = false) String hireDate,
            @Parameter(description = "工作状态") @RequestParam(required = false) String workStatus,
            @Parameter(description = "是否可担任负责人") @RequestParam(required = false) Integer isManager,
            @Parameter(description = "籍贯") @RequestParam(required = false) String nativePlace,
            @Parameter(description = "账号状态") @RequestParam(required = false) String accountStatus,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "身份证照片文件（可选）") @RequestParam(value = "idCardPhotos", required = false) MultipartFile[] idCardPhotos,
            @Parameter(description = "证件照文件（可选）") @RequestParam(value = "certificatePhotos", required = false) MultipartFile[] certificatePhotos) {
        
        // 构建员工信息对象
        Staff staff = new Staff();
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setName(name);
        staff.setPhone(phone);
        staff.setIdCard(idCard);
        staff.setWorkNo(workNo);
        staff.setGender(gender);
        staff.setAvatar(avatar);
        staff.setEmail(email);
        staff.setWechat(wechat);
        staff.setTelephoneAreaCode(telephoneAreaCode);
        staff.setTelephoneNumber(telephoneNumber);
        staff.setTelephoneExtension(telephoneExtension);
        staff.setEmergencyContact(emergencyContact);
        staff.setEmergencyPhone(emergencyPhone);
        staff.setGraduateSchool(graduateSchool);
        staff.setEducationLevel(educationLevel);
        staff.setMajor(major);
        staff.setDepartmentId(departmentId);
        staff.setRoleId(roleId);
        staff.setPosition(position);
        staff.setJobTitle(jobTitle);
        staff.setWorkStatus(workStatus);
        staff.setIsManager(isManager);
        staff.setNativePlace(nativePlace);
        staff.setAccountStatus(accountStatus);
        staff.setRemark(remark);
        
        // 保存员工信息
        boolean result = staffService.save(staff);
        
        // 如果有照片文件，则处理照片上传
        if (result && idCardPhotos != null && idCardPhotos.length > 0) {
            try {
                staffService.updateIdCardPhotos(staff.getId(), idCardPhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响员工信息的保存
                e.printStackTrace();
            }
        }
        if (result && certificatePhotos != null && certificatePhotos.length > 0) {
            try {
                staffService.updateCertificatePhotos(staff.getId(), certificatePhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响员工信息的保存
                e.printStackTrace();
            }
        }

        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改员工信息
     *
     * @param id 员工ID
     * @param username 登录账号
     * @param password 登录密码
     * @param name 员工姓名
     * @param phone 手机号
     * @param idCard 身份证号
     * @param workNo 工号
     * @param gender 性别
     * @param birthDate 生日
     * @param avatar 头像
     * @param email 邮箱
     * @param wechat 微信账号
     * @param telephoneAreaCode 电话区号
     * @param telephoneNumber 电话号码
     * @param telephoneExtension 分机号码
     * @param emergencyContact 紧急联系人
     * @param emergencyPhone 紧急联系电话
     * @param graduateSchool 毕业院校
     * @param graduationDate 毕业时间
     * @param educationLevel 学历
     * @param major 所学专业
     * @param departmentId 所属部门ID
     * @param roleId 角色ID
     * @param position 职位
     * @param jobTitle 职称
     * @param hireDate 入职日期
     * @param workStatus 工作状态
     * @param isManager 是否可担任负责人
     * @param nativePlace 籍贯
     * @param accountStatus 账号状态
     * @param remark 备注
     * @param idCardPhotos 身份证照片文件（可选）
     * @param certificatePhotos 证件照文件（可选）
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改员工信息", description = "修改员工信息（支持文件上传）")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(
            @Parameter(description = "员工ID") @RequestParam Long id,
            @Parameter(description = "登录账号") @RequestParam(required = false) String username,
            @Parameter(description = "登录密码") @RequestParam(required = false) String password,
            @Parameter(description = "员工姓名") @RequestParam(required = false) String name,
            @Parameter(description = "手机号") @RequestParam(required = false) String phone,
            @Parameter(description = "身份证号") @RequestParam(required = false) String idCard,
            @Parameter(description = "工号") @RequestParam(required = false) String workNo,
            @Parameter(description = "性别") @RequestParam(required = false) String gender,
            @Parameter(description = "生日") @RequestParam(required = false) String birthDate,
            @Parameter(description = "头像") @RequestParam(required = false) String avatar,
            @Parameter(description = "邮箱") @RequestParam(required = false) String email,
            @Parameter(description = "微信账号") @RequestParam(required = false) String wechat,
            @Parameter(description = "电话区号") @RequestParam(required = false) String telephoneAreaCode,
            @Parameter(description = "电话号码") @RequestParam(required = false) String telephoneNumber,
            @Parameter(description = "分机号码") @RequestParam(required = false) String telephoneExtension,
            @Parameter(description = "紧急联系人") @RequestParam(required = false) String emergencyContact,
            @Parameter(description = "紧急联系电话") @RequestParam(required = false) String emergencyPhone,
            @Parameter(description = "毕业院校") @RequestParam(required = false) String graduateSchool,
            @Parameter(description = "毕业时间") @RequestParam(required = false) String graduationDate,
            @Parameter(description = "学历") @RequestParam(required = false) String educationLevel,
            @Parameter(description = "所学专业") @RequestParam(required = false) String major,
            @Parameter(description = "所属部门ID") @RequestParam(required = false) Long departmentId,
            @Parameter(description = "角色ID") @RequestParam(required = false) Long roleId,
            @Parameter(description = "职位") @RequestParam(required = false) String position,
            @Parameter(description = "职称") @RequestParam(required = false) String jobTitle,
            @Parameter(description = "入职日期") @RequestParam(required = false) String hireDate,
            @Parameter(description = "工作状态") @RequestParam(required = false) String workStatus,
            @Parameter(description = "是否可担任负责人") @RequestParam(required = false) Integer isManager,
            @Parameter(description = "籍贯") @RequestParam(required = false) String nativePlace,
            @Parameter(description = "账号状态") @RequestParam(required = false) String accountStatus,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "身份证照片文件（可选）") @RequestParam(value = "idCardPhotos", required = false) MultipartFile[] idCardPhotos,
            @Parameter(description = "证件照文件（可选）") @RequestParam(value = "certificatePhotos", required = false) MultipartFile[] certificatePhotos) {
        
        // 先从数据库获取原始员工信息
        Staff existingStaff = staffService.getById(id);
        if (existingStaff == null) {
            return Result.error("员工信息不存在");
        }
        
        // 更新员工信息对象
        existingStaff.setUsername(username);
        existingStaff.setPassword(password);
        existingStaff.setName(name);
        existingStaff.setPhone(phone);
        existingStaff.setIdCard(idCard);
        existingStaff.setWorkNo(workNo);
        existingStaff.setGender(gender);
        existingStaff.setAvatar(avatar);
        existingStaff.setEmail(email);
        existingStaff.setWechat(wechat);
        existingStaff.setTelephoneAreaCode(telephoneAreaCode);
        existingStaff.setTelephoneNumber(telephoneNumber);
        existingStaff.setTelephoneExtension(telephoneExtension);
        existingStaff.setEmergencyContact(emergencyContact);
        existingStaff.setEmergencyPhone(emergencyPhone);
        existingStaff.setGraduateSchool(graduateSchool);
        existingStaff.setEducationLevel(educationLevel);
        existingStaff.setMajor(major);
        existingStaff.setDepartmentId(departmentId);
        existingStaff.setRoleId(roleId);
        existingStaff.setPosition(position);
        existingStaff.setJobTitle(jobTitle);
        existingStaff.setWorkStatus(workStatus);
        existingStaff.setIsManager(isManager);
        existingStaff.setNativePlace(nativePlace);
        existingStaff.setAccountStatus(accountStatus);
        existingStaff.setRemark(remark);
        
        // 保存员工信息
        boolean result = staffService.updateById(existingStaff);
        
        // 如果有照片文件，则处理照片上传
        if (result && idCardPhotos != null && idCardPhotos.length > 0) {
            try {
                staffService.updateIdCardPhotos(id, idCardPhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响员工信息的更新
                e.printStackTrace();
            }
        }
        if (result && certificatePhotos != null && certificatePhotos.length > 0) {
            try {
                staffService.updateCertificatePhotos(id, certificatePhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响员工信息的更新
                e.printStackTrace();
            }
        }

        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除员工信息
     *
     * @param id 员工ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除员工信息", description = "根据ID删除员工信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "员工ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = staffService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 上传身份证照片
     *
     * @param id 员工ID
     * @param files 身份证照片文件
     * @return 操作结果
     */
    @PostMapping("/{id}/id-card-photos")
    @Operation(summary = "上传身份证照片", description = "为指定员工上传身份证照片")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadIdCardPhotos(
            @Parameter(description = "员工ID") @PathVariable Long id,
            @Parameter(description = "身份证照片文件") @RequestParam("files") MultipartFile[] files) {
        try {
            boolean result = staffService.updateIdCardPhotos(id, files);
            if (result) {
                return Result.ok("身份证照片上传成功");
            } else {
                return Result.error("身份证照片上传失败");
            }
        } catch (Exception e) {
            return Result.error("身份证照片上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取身份证照片
     *
     * @param id 员工ID
     * @return 身份证照片URL列表
     */
    @GetMapping("/{id}/id-card-photos")
    @Operation(summary = "获取身份证照片", description = "获取指定员工的身份证照片")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getIdCardPhotos(@Parameter(description = "员工ID") @PathVariable Long id) {
        try {
            String photos = staffService.getIdCardPhotos(id);
            return Result.ok().put("data", photos);
        } catch (Exception e) {
            return Result.error("获取身份证照片失败: " + e.getMessage());
        }
    }

    /**
     * 上传证件照
     *
     * @param id 员工ID
     * @param files 证件照文件
     * @return 操作结果
     */
    @PostMapping("/{id}/certificate-photos")
    @Operation(summary = "上传证件照", description = "为指定员工上传证件照")
    @ApiOperationSupport(order = 8, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadCertificatePhotos(
            @Parameter(description = "员工ID") @PathVariable Long id,
            @Parameter(description = "证件照文件") @RequestParam("files") MultipartFile[] files) {
        try {
            boolean result = staffService.updateCertificatePhotos(id, files);
            if (result) {
                return Result.ok("证件照上传成功");
            } else {
                return Result.error("证件照上传失败");
            }
        } catch (Exception e) {
            return Result.error("证件照上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取证件照
     *
     * @param id 员工ID
     * @return 证件照URL列表
     */
    @GetMapping("/{id}/certificate-photos")
    @Operation(summary = "获取证件照", description = "获取指定员工的证件照")
    @ApiOperationSupport(order = 9, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getCertificatePhotos(@Parameter(description = "员工ID") @PathVariable Long id) {
        try {
            String photos = staffService.getCertificatePhotos(id);
            return Result.ok().put("data", photos);
        } catch (Exception e) {
            return Result.error("获取证件照失败: " + e.getMessage());
        }
    }
}