package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.OwnerIssue;
import com.community.service.OwnerIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ownerIssue")
@Tag(name = "业主问题管理", description = "业主问题相关接口")
@ApiSupport(order = 24, author = "社区管理系统开发团队")
public class OwnerIssueController {

    @Autowired
    private OwnerIssueService ownerIssueService;

    /**
     * 分页查询业主问题信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param ownerIssue 查询条件
     * @return 业主问题信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询业主问题信息", description = "根据条件分页查询业主问题信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       OwnerIssue ownerIssue) {
        IPage<OwnerIssue> page = new Page<>(pageNum, pageSize);
        IPage<OwnerIssue> result = ownerIssueService.selectOwnerIssuePage(page, ownerIssue);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询业主问题信息
     *
     * @param id 业主问题ID
     * @return 业主问题信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询业主问题信息", description = "根据ID查询指定业主问题信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "业主问题ID", required = true, example = "1") @PathVariable Long id) {
        OwnerIssue ownerIssue = ownerIssueService.getById(id);
        return Result.ok().put("data", ownerIssue);
    }

    /**
     * 新增业主问题信息
     *
     * @param communityId 所属社区ID（必填）
     * @param ownerId 业主ID（必填）
     * @param issueTitle 问题标题（必填）
     * @param issueContent 问题详情描述（必填）
     * @param issueType 问题类型（必填）
     * @param contactPhone 联系电话（必填）
     * @param houseId 关联房屋ID
     * @param subType 问题子类型
     * @param locationType 位置类型
     * @param specificLocation 具体位置
     * @param contactName 联系人姓名
     * @param bestContactTime 最佳联系时间
     * @param urgencyLevel 紧急程度
     * @param internalRemark 内部备注
     * @param issueImages 问题图片文件（可选，会保存到 OSS）
     * @param additionalImages 问题描述图片文件（可选，会保存到 OSS）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增业主问题信息", description = "新增业主问题信息（支持图片上传）")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(
            @Parameter(description = "所属社区ID", required = true) @RequestParam Long communityId,
            @Parameter(description = "业主ID", required = true) @RequestParam Long ownerId,
            @Parameter(description = "问题标题", required = true) @RequestParam String issueTitle,
            @Parameter(description = "问题详情描述", required = true) @RequestParam String issueContent,
            @Parameter(description = "问题类型", required = true) @RequestParam String issueType,
            @Parameter(description = "联系电话", required = true) @RequestParam String contactPhone,
            @Parameter(description = "关联房屋ID") @RequestParam(required = false) Long houseId,
            @Parameter(description = "问题子类型") @RequestParam(required = false) String subType,
            @Parameter(description = "位置类型") @RequestParam(required = false) String locationType,
            @Parameter(description = "具体位置") @RequestParam(required = false) String specificLocation,
            @Parameter(description = "联系人姓名") @RequestParam(required = false) String contactName,
            @Parameter(description = "最佳联系时间") @RequestParam(required = false) String bestContactTime,
            @Parameter(description = "紧急程度") @RequestParam(required = false) String urgencyLevel,
            @Parameter(description = "内部备注") @RequestParam(required = false) String internalRemark,
            @Parameter(description = "问题图片文件（可选）") @RequestParam(value = "issueImages", required = false) MultipartFile[] issueImages,
            @Parameter(description = "问题描述图片文件（可选）") @RequestParam(value = "additionalImages", required = false) MultipartFile[] additionalImages) {
        
        // 构建业主问题信息对象
        OwnerIssue ownerIssue = new OwnerIssue();
        ownerIssue.setCommunityId(communityId);
        ownerIssue.setOwnerId(ownerId);
        ownerIssue.setHouseId(houseId);
        ownerIssue.setIssueTitle(issueTitle);
        ownerIssue.setIssueContent(issueContent);
        ownerIssue.setIssueType(issueType);
        ownerIssue.setSubType(subType);
        ownerIssue.setLocationType(locationType);
        ownerIssue.setSpecificLocation(specificLocation);
        ownerIssue.setContactName(contactName);
        ownerIssue.setContactPhone(contactPhone);
        ownerIssue.setBestContactTime(bestContactTime);
        ownerIssue.setUrgencyLevel(urgencyLevel);
        ownerIssue.setInternalRemark(internalRemark);
        
        // 保存业主问题信息
        boolean result = ownerIssueService.save(ownerIssue);
        
        // 如果有问题图片文件，则处理图片上传
//        if (result && issueImages != null && issueImages.length > 0) {
//            try {
//                ownerIssueService.updateIssueImages(ownerIssue.getId(), issueImages);
//            } catch (Exception e) {
//                // 图片上传失败不应该影响问题信息的保存
//                e.printStackTrace();
//            }
//        }
        
        // 如果有问题描述图片文件，则处理图片上传
        if (result && additionalImages != null && additionalImages.length > 0) {
            try {
                ownerIssueService.updateAdditionalImages(ownerIssue.getId(), additionalImages);
            } catch (Exception e) {
                // 图片上传失败不应该影响问题信息的保存
                e.printStackTrace();
            }
        }
        
        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("code", 200);
            response.put("msg", "新增成功");
        } else {
            response.put("code", 500);
            response.put("msg", "新增失败");
        }
        return response;
    }

    /**
     * 修改业主问题信息
     *
     * @param id 业主问题ID
    * @param communityId 所属社区ID
     * @param ownerId 业主ID
     * @param houseId 关联房屋ID
     * @param issueTitle 问题标题
     * @param issueContent 问题详情描述
     * @param issueType 问题类型
     * @param subType 问题子类型
     * @param locationType 位置类型
     * @param specificLocation 具体位置
     * @param contactName 联系人姓名
     * @param contactPhone 联系电话
     * @param bestContactTime 最佳联系时间
     * @param urgencyLevel 紧急程度
     * @param internalRemark 内部备注
     * @param issueImages 问题图片文件（可选）
     * @param additionalImages 问题描述图片文件（可选）
     * @return 操作结果
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改业主问题信息", description = "修改业主问题信息（支持图片上传）")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(
            @Parameter(description = "业主问题ID") @PathVariable Long id,
            @Parameter(description = "所属社区ID") @RequestParam(required = false) Long communityId,
            @Parameter(description = "业主ID") @RequestParam(required = false) Long ownerId,
            @Parameter(description = "关联房屋ID") @RequestParam(required = false) Long houseId,
            @Parameter(description = "问题标题") @RequestParam(required = false) String issueTitle,
            @Parameter(description = "问题详情描述") @RequestParam(required = false) String issueContent,
            @Parameter(description = "问题类型") @RequestParam(required = false) String issueType,
            @Parameter(description = "问题子类型") @RequestParam(required = false) String subType,
            @Parameter(description = "位置类型") @RequestParam(required = false) String locationType,
            @Parameter(description = "具体位置") @RequestParam(required = false) String specificLocation,
            @Parameter(description = "联系人姓名") @RequestParam(required = false) String contactName,
            @Parameter(description = "联系电话") @RequestParam(required = false) String contactPhone,
            @Parameter(description = "最佳联系时间") @RequestParam(required = false) String bestContactTime,
            @Parameter(description = "紧急程度") @RequestParam(required = false) String urgencyLevel,
            @Parameter(description = "内部备注") @RequestParam(required = false) String internalRemark,
            @Parameter(description = "问题图片文件（可选）") @RequestParam(value = "issueImages", required = false) MultipartFile[] issueImages,
            @Parameter(description = "问题描述图片文件（可选）") @RequestParam(value = "additionalImages", required = false) MultipartFile[] additionalImages) {
        
        // 先从数据库获取原始问题信息
        OwnerIssue existingIssue = ownerIssueService.getById(id);
        if (existingIssue == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("msg", "问题信息不存在");
            return response;
        }
        
        // 更新业主问题信息对象
        existingIssue.setCommunityId(communityId);
        existingIssue.setOwnerId(ownerId);
        existingIssue.setHouseId(houseId);
        existingIssue.setIssueTitle(issueTitle);
        existingIssue.setIssueContent(issueContent);
        existingIssue.setIssueType(issueType);
        existingIssue.setSubType(subType);
        existingIssue.setLocationType(locationType);
        existingIssue.setSpecificLocation(specificLocation);
        existingIssue.setContactName(contactName);
        existingIssue.setContactPhone(contactPhone);
        existingIssue.setBestContactTime(bestContactTime);
        existingIssue.setUrgencyLevel(urgencyLevel);
        existingIssue.setInternalRemark(internalRemark);
        
        boolean result = ownerIssueService.updateById(existingIssue);
        
        // 如果有问题图片文件，则处理图片上传
//        if (result && issueImages != null && issueImages.length > 0) {
//            try {
//                ownerIssueService.updateIssueImages(id, issueImages);
//            } catch (Exception e) {
//                // 图片上传失败不应该影响问题信息的更新
//                e.printStackTrace();
//            }
//        }
        
        // 如果有问题描述图片文件，则处理图片上传
        if (result && additionalImages != null && additionalImages.length > 0) {
            try {
                ownerIssueService.updateAdditionalImages(id, additionalImages);
            } catch (Exception e) {
                // 图片上传失败不应该影响问题信息的更新
                e.printStackTrace();
            }
        }
        
        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("code", 200);
            response.put("msg", "修改成功");
        } else {
            response.put("code", 500);
            response.put("msg", "修改失败");
        }
        return response;
    }

    /**
     * 删除业主问题信息
     *
     * @param id 业主问题ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除业主问题信息", description = "根据ID删除业主问题信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "业主问题ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = ownerIssueService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 分页查询待处理的业主问题（工作台-待处理事项组件）
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return 待处理业主问题信息分页数据
     */
    @GetMapping("/pending/page")
    @Operation(summary = "查询待处理业主问题", description = "分页查询待处理状态的业主问题，用于工作台待处理事项展示")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result pendingPage(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                              @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<OwnerIssue> page = new Page<>(pageNum, pageSize);
        IPage<OwnerIssue> result = ownerIssueService.selectPendingIssuePage(page);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 上传问题描述图片
     *
     * @param id 问题ID
     * @param files 问题描述图片文件
     * @return 操作结果
     */
    @PostMapping("/{id}/additional-images")
    @Operation(summary = "上传问题描述图片", description = "为指定问题上传描述图片")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadAdditionalImages(
            @Parameter(description = "问题ID") @PathVariable Long id,
            @Parameter(description = "问题描述图片文件") @RequestParam("files") MultipartFile[] files) {
        try {
            boolean result = ownerIssueService.updateAdditionalImages(id, files);
            if (result) {
                return Result.ok("问题描述图片上传成功");
            } else {
                return Result.error("问题描述图片上传失败");
            }
        } catch (Exception e) {
            return Result.error("问题描述图片上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取问题描述图片
     *
     * @param id 问题ID
     * @return 问题描述图片URL列表
     */
    @GetMapping("/{id}/additional-images")
    @Operation(summary = "获取问题描述图片", description = "获取指定问题的描述图片")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getAdditionalImages(@Parameter(description = "问题ID") @PathVariable Long id) {
        try {
            String photos = ownerIssueService.getAdditionalImages(id);
            return Result.ok().put("data", photos);
        } catch (Exception e) {
            return Result.error("获取问题描述图片失败: " + e.getMessage());
        }
    }
}