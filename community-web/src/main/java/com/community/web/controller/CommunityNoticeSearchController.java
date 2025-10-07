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
public class CommunityNoticeSearchController {

    @Autowired
    private CommunityNoticeService communityNoticeService;

    /**
     * 根据公告标题或公告类型或状态分页查询社区公告信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param title 公告标题
     * @param noticeType 公告类型
     * @param status 状态
     * @return 社区公告信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据公告标题或公告类型或状态分页查询社区公告信息", description = "根据公告标题或公告类型或状态分页查询社区公告信息")
    @ApiOperationSupport(order = 6, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result search(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                         @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                         @Parameter(description = "公告标题") @RequestParam(required = false) String title,
                         @Parameter(description = "公告类型") @RequestParam(required = false) String noticeType,
                         @Parameter(description = "状态") @RequestParam(required = false) String status) {
        CommunityNotice communityNotice = new CommunityNotice();
        communityNotice.setTitle(title);
        communityNotice.setNoticeType(noticeType);
        communityNotice.setStatus(status);
        
        IPage<CommunityNotice> page = new Page<>(pageNum, pageSize);
        IPage<CommunityNotice> result = communityNoticeService.selectCommunityNoticePage(page, communityNotice);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
}