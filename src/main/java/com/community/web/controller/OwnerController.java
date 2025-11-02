package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.Owner;
import com.community.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Knife4j增强注解
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

@RestController
@RequestMapping("/api/owner")
@Tag(name = "业主信息管理", description = "业主信息相关接口")
@ApiSupport(order = 4, author = "社区管理系统开发团队")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /**
     * 获取业主总数
     *
     * @return 业主总数
     */
    @GetMapping("/count")
    @Operation(summary = "获取业主总数", description = "获取业主总数")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result count() {
        long count = ownerService.countAll();
        return Result.ok().put("data", count);
    }

    /**
     * 分页查询业主信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param owner 查询条件
     * @return 业主信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询业主信息", description = "根据条件分页查询业主信息列表")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       Owner owner) {
        IPage<Owner> page = new Page<>(pageNum, pageSize);
        IPage<Owner> result = ownerService.selectOwnerPage(page, owner);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据业主姓名或手机号或业主类型或状态分页查询业主信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param name 业主姓名
     * @param phone 手机号
     * @param ownerType 业主类型
     * @param status 状态
     * @return 业主信息分页数据
     */
    @GetMapping("/search")
    @Operation(
        summary = "根据业主姓名或手机号或业主类型或状态分页查询业主信息",
        description = "根据业主姓名或手机号或业主类型或状态分页查询业主信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "name", description = "业主姓名"),
            @Parameter(name = "phone", description = "手机号"),
            @Parameter(name = "ownerType", description = "业主类型"),
            @Parameter(name = "status", description = "状态")
        }
    )
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "业主姓名") @RequestParam(required = false) String name,
                                   @Parameter(description = "手机号") @RequestParam(required = false) String phone,
                                   @Parameter(description = "业主类型") @RequestParam(required = false) String ownerType,
                                   @Parameter(description = "状态") @RequestParam(required = false) String status) {
        IPage<Owner> page = new Page<>(pageNum, pageSize);
        IPage<Owner> result = ownerService.selectOwnerPageByMultiple(page, name, phone, ownerType, status);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 按创建时间倒序分页查询业主信息
     * 最新创建的业主显示在最上面
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return 业主信息分页数据
     */
    @GetMapping("/latest/page")
    @Operation(summary = "按创建时间倒序查询业主", description = "分页查询业主信息，按创建时间倒序排列（最新创建的在最前面）")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result latestPage(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                             @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<Owner> page = new Page<>(pageNum, pageSize);
        Owner owner = new Owner();
        IPage<Owner> result = ownerService.selectOwnerPage(page, owner);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询业主信息
     *
     * @param id 业主ID
     * @return 业主信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询业主信息", description = "根据ID查询指定业主信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "业主ID", required = true, example = "1") @PathVariable Long id) {
        Owner owner = ownerService.getById(id);
        return Result.ok().put("data", owner);
    }

    /**
     * 新增业主信息
     *
     * @param username 登录账号
     * @param password 登录密码
     * @param name 业主姓名
     * @param phone 手机号
     * @param idCard 身份证号
     * @param ownerType 业主类型
     * @param gender 性别
     * @param birthDate 生日
     * @param politicalStatus 政治面貌
     * @param maritalStatus 婚姻状况
     * @param nationality 民族
     * @param householdType 户口类型
     * @param censusRegister 户籍所在地
     * @param temporaryResidentNo 暂住证号
     * @param currentAddress 现住地址
     * @param emergencyContactName 紧急联系人姓名
     * @param emergencyContactRelation 紧急联系人关系
     * @param emergencyContactPhone 紧急联系人手机号码
     * @param residenceType 居住类型
     * @param moveInDate 入住日期
     * @param moveOutDate 迁出日期
     * @param status 状态
     * @param verifyStatus 认证状态
     * @param remark 备注
     * @param idCardPhotos 身份证照片文件（可选）
     * @param accessControlPhotos 门禁卡照片文件（可选）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增业主信息", description = "新增业主信息（支持文件上传）")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(
            @Parameter(description = "登录账号") @RequestParam(required = false) String username,
            @Parameter(description = "登录密码") @RequestParam(required = false) String password,
            @Parameter(description = "业主姓名") @RequestParam(required = false) String name,
            @Parameter(description = "手机号") @RequestParam(required = false) String phone,
            @Parameter(description = "身份证号") @RequestParam(required = false) String idCard,
            @Parameter(description = "业主类型") @RequestParam(required = false) String ownerType,
            @Parameter(description = "性别") @RequestParam(required = false) String gender,
            @Parameter(description = "生日") @RequestParam(required = false) String birthDate,
            @Parameter(description = "政治面貌") @RequestParam(required = false) String politicalStatus,
            @Parameter(description = "婚姻状况") @RequestParam(required = false) String maritalStatus,
            @Parameter(description = "民族") @RequestParam(required = false) String nationality,
            @Parameter(description = "户口类型") @RequestParam(required = false) String householdType,
            @Parameter(description = "户籍所在地") @RequestParam(required = false) String censusRegister,
            @Parameter(description = "暂住证号") @RequestParam(required = false) String temporaryResidentNo,
            @Parameter(description = "现住地址") @RequestParam(required = false) String currentAddress,
            @Parameter(description = "紧急联系人姓名") @RequestParam(required = false) String emergencyContactName,
            @Parameter(description = "紧急联系人关系") @RequestParam(required = false) String emergencyContactRelation,
            @Parameter(description = "紧急联系人手机号码") @RequestParam(required = false) String emergencyContactPhone,
            @Parameter(description = "居住类型") @RequestParam(required = false) String residenceType,
            @Parameter(description = "入住日期") @RequestParam(required = false) String moveInDate,
            @Parameter(description = "迁出日期") @RequestParam(required = false) String moveOutDate,
            @Parameter(description = "状态") @RequestParam(required = false) String status,
            @Parameter(description = "认证状态") @RequestParam(required = false) String verifyStatus,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "身份证照片文件（可选）") @RequestParam(value = "idCardPhotos", required = false) MultipartFile[] idCardPhotos,
            @Parameter(description = "门禁卡照片文件（可选）") @RequestParam(value = "accessControlPhotos", required = false) MultipartFile[] accessControlPhotos) {
        
        // 构建业主信息对象
        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owner.setName(name);
        owner.setPhone(phone);
        owner.setIdCard(idCard);
        owner.setOwnerType(ownerType);
        owner.setGender(gender);
        owner.setPoliticalStatus(politicalStatus);
        owner.setMaritalStatus(maritalStatus);
        owner.setNationality(nationality);
        owner.setHouseholdType(householdType);
        owner.setCensusRegister(censusRegister);
        owner.setTemporaryResidentNo(temporaryResidentNo);
        owner.setCurrentAddress(currentAddress);
        owner.setEmergencyContactName(emergencyContactName);
        owner.setEmergencyContactRelation(emergencyContactRelation);
        owner.setEmergencyContactPhone(emergencyContactPhone);
        owner.setResidenceType(residenceType);
        owner.setStatus(status);
        owner.setVerifyStatus(verifyStatus);
        owner.setRemark(remark);
        
        // 保存业主信息
        boolean result = ownerService.save(owner);
        
        // 如果有照片文件，则处理照片上传
        if (result && idCardPhotos != null && idCardPhotos.length > 0) {
            try {
                ownerService.updateIdCardPhotos(owner.getId(), idCardPhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响业主信息的保存
                e.printStackTrace();
            }
        }
        if (result && accessControlPhotos != null && accessControlPhotos.length > 0) {
            try {
                ownerService.updateAccessControlPhotos(owner.getId(), accessControlPhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响业主信息的保存
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
     * 修改业主信息
     *
     * @param id 业主ID
     * @param username 登录账号
     * @param password 登录密码
     * @param name 业主姓名
     * @param phone 手机号
     * @param idCard 身份证号
     * @param ownerType 业主类型
     * @param gender 性别
     * @param birthDate 生日
     * @param politicalStatus 政治面貌
     * @param maritalStatus 婚姻状况
     * @param nationality 民族
     * @param householdType 户口类型
     * @param censusRegister 户籍所在地
     * @param temporaryResidentNo 暂住证号
     * @param currentAddress 现住地址
     * @param emergencyContactName 紧急联系人姓名
     * @param emergencyContactRelation 紧急联系人关系
     * @param emergencyContactPhone 紧急联系人手机号码
     * @param residenceType 居住类型
     * @param moveInDate 入住日期
     * @param moveOutDate 迁出日期
     * @param status 状态
     * @param verifyStatus 认证状态
     * @param remark 备注
     * @param idCardPhotos 身份证照片文件（可选）
     * @param accessControlPhotos 门禁卡照片文件（可选）
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改业主信息", description = "修改业主信息（支持文件上传）")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(
            @Parameter(description = "业主ID") @RequestParam Long id,
            @Parameter(description = "登录账号") @RequestParam(required = false) String username,
            @Parameter(description = "登录密码") @RequestParam(required = false) String password,
            @Parameter(description = "业主姓名") @RequestParam(required = false) String name,
            @Parameter(description = "手机号") @RequestParam(required = false) String phone,
            @Parameter(description = "身份证号") @RequestParam(required = false) String idCard,
            @Parameter(description = "业主类型") @RequestParam(required = false) String ownerType,
            @Parameter(description = "性别") @RequestParam(required = false) String gender,
            @Parameter(description = "生日") @RequestParam(required = false) String birthDate,
            @Parameter(description = "政治面貌") @RequestParam(required = false) String politicalStatus,
            @Parameter(description = "婚姻状况") @RequestParam(required = false) String maritalStatus,
            @Parameter(description = "民族") @RequestParam(required = false) String nationality,
            @Parameter(description = "户口类型") @RequestParam(required = false) String householdType,
            @Parameter(description = "户籍所在地") @RequestParam(required = false) String censusRegister,
            @Parameter(description = "暂住证号") @RequestParam(required = false) String temporaryResidentNo,
            @Parameter(description = "现住地址") @RequestParam(required = false) String currentAddress,
            @Parameter(description = "紧急联系人姓名") @RequestParam(required = false) String emergencyContactName,
            @Parameter(description = "紧急联系人关系") @RequestParam(required = false) String emergencyContactRelation,
            @Parameter(description = "紧急联系人手机号码") @RequestParam(required = false) String emergencyContactPhone,
            @Parameter(description = "居住类型") @RequestParam(required = false) String residenceType,
            @Parameter(description = "入住日期") @RequestParam(required = false) String moveInDate,
            @Parameter(description = "迁出日期") @RequestParam(required = false) String moveOutDate,
            @Parameter(description = "状态") @RequestParam(required = false) String status,
            @Parameter(description = "认证状态") @RequestParam(required = false) String verifyStatus,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "身份证照片文件（可选）") @RequestParam(value = "idCardPhotos", required = false) MultipartFile[] idCardPhotos,
            @Parameter(description = "门禁卡照片文件（可选）") @RequestParam(value = "accessControlPhotos", required = false) MultipartFile[] accessControlPhotos) {
        
        // 先从数据库获取原始业主信息
        Owner existingOwner = ownerService.getById(id);
        if (existingOwner == null) {
            return Result.error("业主信息不存在");
        }
        
        // 更新业主信息对象
        existingOwner.setUsername(username);
        existingOwner.setPassword(password);
        existingOwner.setName(name);
        existingOwner.setPhone(phone);
        existingOwner.setIdCard(idCard);
        existingOwner.setOwnerType(ownerType);
        existingOwner.setGender(gender);
        existingOwner.setPoliticalStatus(politicalStatus);
        existingOwner.setMaritalStatus(maritalStatus);
        existingOwner.setNationality(nationality);
        existingOwner.setHouseholdType(householdType);
        existingOwner.setCensusRegister(censusRegister);
        existingOwner.setTemporaryResidentNo(temporaryResidentNo);
        existingOwner.setCurrentAddress(currentAddress);
        existingOwner.setEmergencyContactName(emergencyContactName);
        existingOwner.setEmergencyContactRelation(emergencyContactRelation);
        existingOwner.setEmergencyContactPhone(emergencyContactPhone);
        existingOwner.setResidenceType(residenceType);
        existingOwner.setStatus(status);
        existingOwner.setVerifyStatus(verifyStatus);
        existingOwner.setRemark(remark);
        
        // 保存业主信息
        boolean result = ownerService.updateById(existingOwner);
        
        // 如果有照片文件，则处理照片上传
        if (result && idCardPhotos != null && idCardPhotos.length > 0) {
            try {
                ownerService.updateIdCardPhotos(id, idCardPhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响业主信息的更新
                e.printStackTrace();
            }
        }
        if (result && accessControlPhotos != null && accessControlPhotos.length > 0) {
            try {
                ownerService.updateAccessControlPhotos(id, accessControlPhotos);
            } catch (Exception e) {
                // 照片上传失败不应该影响业主信息的更新
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
     * 删除业主信息
     *
     * @param id 业主ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除业主信息", description = "根据ID删除业主信息")
    @ApiOperationSupport(order = 8, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "业主ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = ownerService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}