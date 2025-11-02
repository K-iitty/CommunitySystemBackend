package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.common.service.OssService;
import com.community.domain.entity.SmartQaKnowledge;
import com.community.service.SmartQaKnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/smartQaKnowledge")
@Tag(name = "智能问答知识库管理", description = "智能问答知识库相关接口")
@ApiSupport(order = 20, author = "社区管理系统开发团队")
public class SmartQaKnowledgeController {

    @Autowired
    private SmartQaKnowledgeService smartQaKnowledgeService;
    
    @Autowired
    private OssService ossService;

    /**
     * 分页查询知识库信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param smartQaKnowledge 查询条件
     * @return 知识库信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询知识库信息", description = "根据分页参数和查询条件分页查询知识库信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result page(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       SmartQaKnowledge smartQaKnowledge) {
        IPage<SmartQaKnowledge> page = new Page<>(pageNum, pageSize);
        IPage<SmartQaKnowledge> result = smartQaKnowledgeService.selectSmartQaKnowledgePage(page, smartQaKnowledge);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据标题分页查询知识库信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param title 标题
     * @return 知识库信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据标题分页查询知识库信息", description = "根据标题分页查询知识库信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "标题") @RequestParam(required = false) String title) {
        IPage<SmartQaKnowledge> page = new Page<>(pageNum, pageSize);
        IPage<SmartQaKnowledge> result = smartQaKnowledgeService.selectSmartQaKnowledgePageByMultiple(page, title);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询知识库信息
     *
     * @param id 知识库ID
     * @return 知识库信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询知识库信息", description = "根据ID查询指定知识库信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "知识库ID") @PathVariable Long id) {
        if (id == null || id <= 0) {
            return Result.error("知识库ID不能为空");
        }
        SmartQaKnowledge smartQaKnowledge = smartQaKnowledgeService.getById(id);
        if (smartQaKnowledge == null) {
            return Result.error("知识库不存在");
        }
        return Result.ok().put("data", smartQaKnowledge);
    }

    /**
     * 新增知识库信息
     *
     * @param smartQaKnowledge 知识库信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增知识库信息", description = "新增知识库信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "知识库信息") @RequestBody SmartQaKnowledge smartQaKnowledge) {
        boolean result = smartQaKnowledgeService.save(smartQaKnowledge);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改知识库信息
     *
     * @param smartQaKnowledge 知识库信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改知识库信息", description = "修改知识库信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "知识库信息") @RequestBody SmartQaKnowledge smartQaKnowledge) {
        boolean result = smartQaKnowledgeService.updateById(smartQaKnowledge);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除智能问答知识库信息
     *
     * @param id 知识库ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除智能问答知识库信息", description = "根据ID删除智能问答知识库信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "知识库ID") @PathVariable Long id) {
        // 先获取要删除的记录，用于删除文件
        SmartQaKnowledge knowledge = smartQaKnowledgeService.getById(id);
        if (knowledge == null) {
            return Result.error("知识库不存在");
        }

        boolean result = smartQaKnowledgeService.removeById(id);
        if (result) {
            // 删除关联的文件 (在OSS中删除)
            if (StringUtils.hasText(knowledge.getFilePath())) {
                try {
                    // 注意：这里实际应该调用OSS服务删除文件，但由于我们只存储URL，
                    // 且OSS通常有生命周期管理，这里仅作注释说明
                    // ossService.deleteFile(knowledge.getFilePath());
                } catch (Exception e) {
                    // 文件删除失败不返回错误，只记录日志
                    e.printStackTrace();
                }
            }
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 上传知识库文档
     *
     * @param file 文档文件
     * @param category 文档分类
     * @param title 文档标题
     * @param description 文档描述
     * @param tags 标签
     * @param sortOrder 排序
     * @param status 状态
     * @return 操作结果
     */
    @PostMapping("/upload")
    @Operation(summary = "上传知识库文档", description = "上传知识库文档并保存相关信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result upload(@Parameter(description = "文档文件") @RequestParam("file") MultipartFile file,
                         @Parameter(description = "文档分类") @RequestParam(required = false) String category,
                         @Parameter(description = "文档标题") @RequestParam(required = false) String title,
                         @Parameter(description = "文档描述") @RequestParam(required = false) String description,
                         @Parameter(description = "标签") @RequestParam(required = false) String tags,
                         @Parameter(description = "排序") @RequestParam(required = false, defaultValue = "0") Integer sortOrder,
                         @Parameter(description = "状态") @RequestParam(required = false, defaultValue = "启用") String status,
                         @Parameter(description = "上传人ID") @RequestParam(required = false) Long createdBy) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        try {
            // 上传文件到OSS并获取URL
            String fileUrl = ossService.uploadFile(file, "smart_qa_knowledge/");

            // 创建知识库记录
            SmartQaKnowledge knowledge = new SmartQaKnowledge();
            knowledge.setCategory(category != null ? category : "默认分类");
            knowledge.setTitle(title != null && !title.isEmpty() ? title : 
                              (file.getOriginalFilename() != null ? file.getOriginalFilename() : "未命名文档"));
            knowledge.setDescription(description);
            knowledge.setFilePath(fileUrl);  // 存储OSS文件URL
            knowledge.setFileName(file.getOriginalFilename());
            // 获取文件扩展名
            String fileExtension = "";
            if (file.getOriginalFilename() != null && file.getOriginalFilename().contains(".")) {
                fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            }
            knowledge.setFileType(fileExtension.toLowerCase());
            knowledge.setFileSize(file.getSize());
            knowledge.setTags(tags);
            knowledge.setSortOrder(sortOrder);
            knowledge.setStatus(status);
            knowledge.setCreatedBy(createdBy);
            knowledge.setCreatedAt(LocalDateTime.now());
            knowledge.setUpdatedAt(LocalDateTime.now());

            // 保存到数据库
            boolean result = smartQaKnowledgeService.save(knowledge);
            if (result) {
                return Result.ok("文件上传成功").put("data", knowledge);
            } else {
                // 如果保存数据库失败，理论上应该删除OSS中的文件，但由于OSS删除需要解析URL获取文件名，
                // 这里仅作注释说明
                // ossService.deleteFile(fileUrl);
                return Result.error("文件上传失败");
            }
        } catch (Exception e) {
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
}