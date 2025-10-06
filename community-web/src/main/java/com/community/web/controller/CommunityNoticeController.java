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
     * @param communityNotice 社区公告信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增社区公告信息", description = "新增社区公告信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "社区公告信息") @RequestBody CommunityNotice communityNotice) {
        boolean result = communityNoticeService.save(communityNotice);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改社区公告信息
     *
     * @param communityNotice 社区公告信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改社区公告信息", description = "修改社区公告信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "社区公告信息") @RequestBody CommunityNotice communityNotice) {
        boolean result = communityNoticeService.updateById(communityNotice);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
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
}