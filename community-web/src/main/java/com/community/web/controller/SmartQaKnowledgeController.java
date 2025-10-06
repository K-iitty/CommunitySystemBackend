package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.SmartQaKnowledge;
import com.community.service.SmartQaKnowledgeService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/smartQaKnowledge")
@Tag(name = "智能问答知识库管理", description = "智能问答知识库相关接口")
public class SmartQaKnowledgeController {

    @Autowired
    private SmartQaKnowledgeService smartQaKnowledgeService;

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
        boolean result = smartQaKnowledgeService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}