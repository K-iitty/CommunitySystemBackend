package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.CommunityInfo;
import com.community.service.CommunityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// Knife4j增强注解
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

@RestController
@RequestMapping("/api/community")
@Tag(name = "社区信息管理", description = "社区信息相关接口")
@ApiSupport(order = 1, author = "社区管理系统开发团队")
public class CommunityInfoController {

    @Autowired
    private CommunityInfoService communityInfoService;

    /**
     * 分页查询社区信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param communityInfo 查询条件
     * @return 社区信息分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询社区信息", description = "根据条件分页查询社区信息列表")
    @ApiOperationSupport(order = 1, author = "张三")
    @SecurityRequirement(name = "Authorization")
    public Result page(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                       @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                       CommunityInfo communityInfo) {
        IPage<CommunityInfo> page = new Page<>(pageNum, pageSize);
        IPage<CommunityInfo> result = communityInfoService.selectCommunityInfoPage(page, communityInfo);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据社区名称或详细地址分页查询社区信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param communityName 社区名称
     * @param detailAddress 详细地址
     * @return 社区信息分页数据
     */
    @GetMapping("/search")
    @Operation(summary = "根据社区名称或详细地址分页查询社区信息", description = "根据社区名称或详细地址分页查询社区信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result searchByMultiple(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                   @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                                   @Parameter(description = "社区名称") @RequestParam(required = false) String communityName,
                                   @Parameter(description = "详细地址") @RequestParam(required = false) String detailAddress) {
        IPage<CommunityInfo> page = new Page<>(pageNum, pageSize);
        IPage<CommunityInfo> result = communityInfoService.selectCommunityInfoPageByMultiple(page, communityName, detailAddress);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }

    /**
     * 根据ID查询社区信息
     *
     * @param id 社区ID
     * @return 社区信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询社区信息", description = "根据ID查询指定社区信息")
    @ApiOperationSupport(order = 2, author = "张三")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "社区ID") @PathVariable Long id) {
        CommunityInfo communityInfo = communityInfoService.getById(id);
        return Result.ok().put("data", communityInfo);
    }

    /**
     * 新增社区信息
     *
     * @param communityInfo 社区信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增社区信息", description = "新增社区信息")
    @ApiOperationSupport(order = 3, author = "李四")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(@Parameter(description = "社区信息") @RequestBody CommunityInfo communityInfo) {
        boolean result = communityInfoService.save(communityInfo);

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
     * 修改社区信息
     *
     * @param communityInfo 社区信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改社区信息", description = "修改社区信息")
    @ApiOperationSupport(order = 4, author = "李四")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(@Parameter(description = "社区信息") @RequestBody CommunityInfo communityInfo) {
        boolean result = communityInfoService.updateById(communityInfo);

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
     * 删除社区信息
     *
     * @param id 社区ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除社区信息", description = "根据ID删除社区信息")
    @ApiOperationSupport(order = 5, author = "王五")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(description = "社区ID") @PathVariable Long id) {
        boolean result = communityInfoService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}