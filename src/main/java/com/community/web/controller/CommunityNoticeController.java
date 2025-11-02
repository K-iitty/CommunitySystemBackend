package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.CommunityNotice;
import com.community.service.CommunityNoticeService;
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
@RequestMapping("/api/communityNotice")
@Tag(name = "社区公告管理", description = "社区公告相关接口")
@ApiSupport(order = 10, author = "社区管理系统开发团队")
public class CommunityNoticeController {

    @Autowired
    private CommunityNoticeService communityNoticeService;

    /**
     * 分页查询社区公告信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param communityNotice 查询条件
     * @return 社区公告信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询社区公告信息", description = "根据条件分页查询社区公告信息列表")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       CommunityNotice communityNotice) {
        IPage<CommunityNotice> page = new Page<>(pageNum, pageSize);
        IPage<CommunityNotice> result = communityNoticeService.selectCommunityNoticePage(page, communityNotice);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询社区公告信息
     *
     * @param id 社区公告ID
     * @return 社区公告信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询社区公告信息", description = "根据ID查询指定社区公告信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(name = "id", description = "社区公告ID", required = true, example = "1") @PathVariable Long id) {
        CommunityNotice communityNotice = communityNoticeService.getById(id);
        return Result.ok().put("data", communityNotice);
    }

    /**
     * 新增社区公告信息
     *
     * @param communityId 所属社区ID（必填）
     * @param createdBy 发布人ID（必填）
     * @param title 公告标题（必填）
     * @param noticeType 公告类型（必填）
     * @param content 公告内容（必填）
     * @param startTime 生效时间（必填）
     * @param endTime 失效时间（必填）
     * @param activityDate 活动日期
     * @param activityTime 活动时间
     * @param activityLocation 活动地点
     * @param activityOrganizer 活动组织者
     * @param activityContact 活动联系人
     * @param activityContactPhone 活动联系电话
     * @param targetAudience 目标受众
     * @param targetBuildings 目标楼栋JSON数组
     * @param targetOwnerTypes 目标业主类型
     * @param publishTime 发布时间
     * @param isUrgent 是否紧急
     * @param isTop 是否置顶
     * @param topEndTime 置顶结束时间
     * @param attachments 附件信息JSON格式
     * @param status 状态
     * @param approvalStatus 审核状态
     * @param approvedBy 审核人ID
     * @param approvedAt 审核时间
     * @param remark 备注
     * @param noticeImages 公告图片文件（可选）
     * @param files 公告图片文件（可选，与noticeImages等价）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增社区公告信息", description = "新增社区公告信息（支持文件上传）")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(
            @Parameter(description = "所属社区ID", required = true) @RequestParam Long communityId,
            @Parameter(description = "发布人ID", required = true) @RequestParam Long createdBy,
            @Parameter(description = "公告标题", required = true) @RequestParam String title,
            @Parameter(description = "公告类型", required = true) @RequestParam String noticeType,
            @Parameter(description = "公告内容", required = true) @RequestParam String content,
            @Parameter(description = "生效时间", required = true) @RequestParam String startTime,
            @Parameter(description = "失效时间", required = true) @RequestParam String endTime,
            @Parameter(description = "活动日期") @RequestParam(required = false) String activityDate,
            @Parameter(description = "活动时间") @RequestParam(required = false) String activityTime,
            @Parameter(description = "活动地点") @RequestParam(required = false) String activityLocation,
            @Parameter(description = "活动组织者") @RequestParam(required = false) String activityOrganizer,
            @Parameter(description = "活动联系人") @RequestParam(required = false) String activityContact,
            @Parameter(description = "活动联系电话") @RequestParam(required = false) String activityContactPhone,
            @Parameter(description = "目标受众") @RequestParam(required = false) String targetAudience,
            @Parameter(description = "目标楼栋JSON数组") @RequestParam(required = false) String targetBuildings,
            @Parameter(description = "目标业主类型") @RequestParam(required = false) String targetOwnerTypes,
            @Parameter(description = "发布时间") @RequestParam(required = false) String publishTime,
            @Parameter(description = "是否紧急") @RequestParam(required = false) Integer isUrgent,
            @Parameter(description = "是否置顶") @RequestParam(required = false) Integer isTop,
            @Parameter(description = "置顶结束时间") @RequestParam(required = false) String topEndTime,
            @Parameter(description = "附件信息JSON格式") @RequestParam(required = false) String attachments,
            @Parameter(description = "状态") @RequestParam(required = false) String status,
            @Parameter(description = "审核状态") @RequestParam(required = false) String approvalStatus,
            @Parameter(description = "审核人ID") @RequestParam(required = false) Long approvedBy,
            @Parameter(description = "审核时间") @RequestParam(required = false) String approvedAt,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "公告图片文件（可选）") @RequestParam(value = "noticeImages", required = false) MultipartFile[] noticeImages,
            @Parameter(description = "公告图片文件（可选，与noticeImages等价）") @RequestParam(value = "files", required = false) MultipartFile[] files) {
        
        // 构建公告信息对象
        CommunityNotice communityNotice = new CommunityNotice();
        communityNotice.setCommunityId(communityId);
        communityNotice.setCreatedBy(createdBy);
        communityNotice.setTitle(title);
        communityNotice.setContent(content);
        communityNotice.setNoticeType(noticeType);
        
        // 解析日期和时间字段
        java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        if (activityDate != null && !activityDate.isEmpty()) {
            try {
                communityNotice.setActivityDate(java.time.LocalDate.parse(activityDate, dateFormatter));
            } catch (Exception e) {
                // 忽略日期解析错误
            }
        }
        
        communityNotice.setActivityTime(activityTime);
        communityNotice.setActivityLocation(activityLocation);
        communityNotice.setActivityOrganizer(activityOrganizer);
        communityNotice.setActivityContact(activityContact);
        communityNotice.setActivityContactPhone(activityContactPhone);
        communityNotice.setTargetAudience(targetAudience);
        communityNotice.setTargetBuildings(targetBuildings);
        communityNotice.setTargetOwnerTypes(targetOwnerTypes);
        
        if (publishTime != null && !publishTime.isEmpty()) {
            try {
                communityNotice.setPublishTime(java.time.LocalDateTime.parse(publishTime, dateTimeFormatter));
            } catch (Exception e) {
                // 忽略时间解析错误
            }
        }
        
        communityNotice.setStartTime(java.time.LocalDateTime.parse(startTime, dateTimeFormatter));
        communityNotice.setEndTime(java.time.LocalDateTime.parse(endTime, dateTimeFormatter));
        communityNotice.setIsUrgent(isUrgent);
        communityNotice.setIsTop(isTop);
        
        if (topEndTime != null && !topEndTime.isEmpty()) {
            try {
                communityNotice.setTopEndTime(java.time.LocalDateTime.parse(topEndTime, dateTimeFormatter));
            } catch (Exception e) {
                // 忽略时间解析错误
            }
        }
        
        communityNotice.setAttachments(attachments);
        communityNotice.setStatus(status);
        communityNotice.setApprovalStatus(approvalStatus);
        communityNotice.setApprovedBy(approvedBy);
        
        if (approvedAt != null && !approvedAt.isEmpty()) {
            try {
                communityNotice.setApprovedAt(java.time.LocalDateTime.parse(approvedAt, dateTimeFormatter));
            } catch (Exception e) {
                // 忽略时间解析错误
            }
        }
        
        communityNotice.setRemark(remark);
        
        // 保存公告信息
        boolean result = communityNoticeService.save(communityNotice);
        
        // 如果有图片文件，则处理图片上传（支持noticeImages和files两种参数名）
        MultipartFile[] imagesToUpload = null;
        if (noticeImages != null && noticeImages.length > 0) {
            imagesToUpload = noticeImages;
        } else if (files != null && files.length > 0) {
            imagesToUpload = files;
        }
        
        if (result && imagesToUpload != null) {
            try {
                communityNoticeService.updateNoticeImages(communityNotice.getId(), imagesToUpload);
            } catch (Exception e) {
                // 图片上传失败不应该影响公告信息的保存
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
     * 修改社区公告信息
     *
     * @param id 公告ID
     * @param communityId 所属社区ID
     * @param createdBy 发布人ID
     * @param title 公告标题
     * @param content 公告内容
     * @param noticeType 公告类型
     * @param activityDate 活动日期
     * @param activityTime 活动时间
     * @param activityLocation 活动地点
     * @param activityOrganizer 活动组织者
     * @param activityContact 活动联系人
     * @param activityContactPhone 活动联系电话
     * @param targetAudience 目标受众
     * @param targetBuildings 目标楼栋JSON数组
     * @param targetOwnerTypes 目标业主类型
     * @param publishTime 发布时间
     * @param startTime 生效时间
     * @param endTime 失效时间
     * @param isUrgent 是否紧急
     * @param isTop 是否置顶
     * @param topEndTime 置顶结束时间
     * @param attachments 附件信息JSON格式
     * @param status 状态
     * @param approvalStatus 审核状态
     * @param approvedBy 审核人ID
     * @param approvedAt 审核时间
     * @param remark 备注
     * @param noticeImages 公告图片文件（可选）
     * @param files 公告图片文件（可选，与noticeImages等价）
     * @return 操作结果
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改社区公告信息", description = "修改社区公告信息（支持文件上传）")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(
            @Parameter(description = "公告ID") @PathVariable Long id,
            @Parameter(description = "所属社区ID") @RequestParam(required = false) Long communityId,
            @Parameter(description = "发布人ID") @RequestParam(required = false) Long createdBy,
            @Parameter(description = "公告标题") @RequestParam(required = false) String title,
            @Parameter(description = "公告内容") @RequestParam(required = false) String content,
            @Parameter(description = "公告类型") @RequestParam(required = false) String noticeType,
            @Parameter(description = "活动日期") @RequestParam(required = false) String activityDate,
            @Parameter(description = "活动时间") @RequestParam(required = false) String activityTime,
            @Parameter(description = "活动地点") @RequestParam(required = false) String activityLocation,
            @Parameter(description = "活动组织者") @RequestParam(required = false) String activityOrganizer,
            @Parameter(description = "活动联系人") @RequestParam(required = false) String activityContact,
            @Parameter(description = "活动联系电话") @RequestParam(required = false) String activityContactPhone,
            @Parameter(description = "目标受众") @RequestParam(required = false) String targetAudience,
            @Parameter(description = "目标楼栋JSON数组") @RequestParam(required = false) String targetBuildings,
            @Parameter(description = "目标业主类型") @RequestParam(required = false) String targetOwnerTypes,
            @Parameter(description = "发布时间") @RequestParam(required = false) String publishTime,
            @Parameter(description = "生效时间") @RequestParam(required = false) String startTime,
            @Parameter(description = "失效时间") @RequestParam(required = false) String endTime,
            @Parameter(description = "是否紧急") @RequestParam(required = false) Integer isUrgent,
            @Parameter(description = "是否置顶") @RequestParam(required = false) Integer isTop,
            @Parameter(description = "置顶结束时间") @RequestParam(required = false) String topEndTime,
            @Parameter(description = "附件信息JSON格式") @RequestParam(required = false) String attachments,
            @Parameter(description = "状态") @RequestParam(required = false) String status,
            @Parameter(description = "审核状态") @RequestParam(required = false) String approvalStatus,
            @Parameter(description = "审核人ID") @RequestParam(required = false) Long approvedBy,
            @Parameter(description = "审核时间") @RequestParam(required = false) String approvedAt,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "公告图片文件（可选）") @RequestParam(value = "noticeImages", required = false) MultipartFile[] noticeImages,
            @Parameter(description = "公告图片文件（可选，与noticeImages等价）") @RequestParam(value = "files", required = false) MultipartFile[] files) {
        
        // 先从数据库获取原始公告信息
        CommunityNotice existingNotice = communityNoticeService.getById(id);
        if (existingNotice == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("msg", "公告信息不存在");
            return response;
        }
        
        // 更新公告信息对象
        existingNotice.setCommunityId(communityId);
        existingNotice.setCreatedBy(createdBy);
        existingNotice.setTitle(title);
        existingNotice.setContent(content);
        existingNotice.setNoticeType(noticeType);
        
        // 解析日期和时间字段
        java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (activityDate != null && !activityDate.isEmpty()) {
            try {
                existingNotice.setActivityDate(java.time.LocalDate.parse(activityDate, dateFormatter));
            } catch (Exception e) {
                // 忽略日期解析错误
            }
        }
        
        existingNotice.setActivityTime(activityTime);
        existingNotice.setActivityLocation(activityLocation);
        existingNotice.setActivityOrganizer(activityOrganizer);
        existingNotice.setActivityContact(activityContact);
        existingNotice.setActivityContactPhone(activityContactPhone);
        existingNotice.setTargetAudience(targetAudience);
        existingNotice.setTargetBuildings(targetBuildings);
        existingNotice.setTargetOwnerTypes(targetOwnerTypes);
        
        if (publishTime != null && !publishTime.isEmpty()) {
            try {
                existingNotice.setPublishTime(java.time.LocalDateTime.parse(publishTime, dateTimeFormatter));
            } catch (Exception e) {
                // 忽略时间解析错误
            }
        }
        
        existingNotice.setStartTime(startTime != null ? java.time.LocalDateTime.parse(startTime, dateTimeFormatter) : null);
        existingNotice.setEndTime(endTime != null ? java.time.LocalDateTime.parse(endTime, dateTimeFormatter) : null);
        existingNotice.setIsUrgent(isUrgent);
        existingNotice.setIsTop(isTop);
        
        if (topEndTime != null && !topEndTime.isEmpty()) {
            try {
                existingNotice.setTopEndTime(java.time.LocalDateTime.parse(topEndTime, dateTimeFormatter));
            } catch (Exception e) {
                // 忽略时间解析错误
            }
        }
        
        existingNotice.setAttachments(attachments);
        existingNotice.setStatus(status);
        existingNotice.setApprovalStatus(approvalStatus);
        existingNotice.setApprovedBy(approvedBy);
        
        if (approvedAt != null && !approvedAt.isEmpty()) {
            try {
                existingNotice.setApprovedAt(java.time.LocalDateTime.parse(approvedAt, dateTimeFormatter));
            } catch (Exception e) {
                // 忽略时间解析错误
            }
        }
        
        existingNotice.setRemark(remark);
        
        // 保存公告信息
        boolean result = communityNoticeService.updateById(existingNotice);
        
        // 如果有图片文件，则处理图片上传（支持noticeImages和files两种参数名）
        MultipartFile[] imagesToUpload = null;
        if (noticeImages != null && noticeImages.length > 0) {
            imagesToUpload = noticeImages;
        } else if (files != null && files.length > 0) {
            imagesToUpload = files;
        }
        
        if (result && imagesToUpload != null) {
            try {
                communityNoticeService.updateNoticeImages(id, imagesToUpload);
            } catch (Exception e) {
                // 图片上传失败不应该影响公告信息的更新
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
     * 删除社区公告信息
     *
     * @param id 社区公告ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除社区公告信息", description = "根据ID删除社区公告信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "社区公告ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = communityNoticeService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 上传公告图片
     *
     * @param id 公告ID
     * @param files 公告图片文件
     * @return 操作结果
     */
    @PostMapping("/{id}/images")
    @Operation(summary = "上传公告图片", description = "为指定公告上传图片")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result uploadNoticeImages(
            @Parameter(description = "公告ID") @PathVariable Long id,
            @Parameter(description = "公告图片文件") @RequestParam("files") MultipartFile[] files) {
        try {
            boolean result = communityNoticeService.updateNoticeImages(id, files);
            if (result) {
                return Result.ok("公告图片上传成功");
            } else {
                return Result.error("公告图片上传失败");
            }
        } catch (Exception e) {
            return Result.error("公告图片上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取公告图片
     *
     * @param id 公告ID
     * @return 公告图片URL列表
     */
    @GetMapping("/{id}/images")
    @Operation(summary = "获取公告图片", description = "获取指定公告的图片")
    @ApiOperationSupport(order = 7, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getNoticeImages(@Parameter(description = "公告ID") @PathVariable Long id) {
        try {
            String photos = communityNoticeService.getNoticeImages(id);
            return Result.ok().put("data", photos);
        } catch (Exception e) {
            return Result.error("获取公告图片失败: " + e.getMessage());
        }
    }
}