package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.HouseOwner;
import com.community.domain.vo.HouseOwnerVO;
import com.community.service.HouseOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/houseOwner")
@Tag(name = "房屋业主关联管理", description = "房屋业主关联相关接口")
@ApiSupport(order = 14, author = "社区管理系统开发团队")
public class HouseOwnerController {

    @Autowired
    private HouseOwnerService houseOwnerService;

    /**
     * 分页查询房屋业主关联详细信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param houseOwner 查询条件
     * @return 房屋业主关联详细信息分页数据
     */
    @GetMapping("/pageDetail")
    @Operation(summary = "分页查询房屋业主关联详细信息", description = "根据条件分页查询房屋业主关联详细信息列表，包含房屋和业主信息")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result pageDetail(@Parameter(description = "当前页码") @RequestParam(defaultValue = "1") Integer pageNum,
                             @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
                             HouseOwner houseOwner) {
        IPage<HouseOwnerVO> page = new Page<>(pageNum, pageSize);
        IPage<HouseOwnerVO> result = houseOwnerService.selectHouseOwnerVOPage(page, houseOwner);
        return Result.ok().put("data", result.getRecords())
                .put("total", result.getTotal())
                .put("pageNum", result.getCurrent())
                .put("pageSize", result.getSize());
    }
    
    /**
     * 根据ID查询房屋业主关联详细信息
     *
     * @param id 房屋业主关联ID
     * @return 房屋业主关联详细信息
     */
    @GetMapping("/detail/{id}")
    @Operation(summary = "根据ID查询房屋业主关联详细信息", description = "根据ID查询指定房屋业主关联详细信息，包含房屋和业主信息")
    @ApiOperationSupport(order = 2, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getDetailById(@Parameter(name = "id", description = "房屋业主关联ID", required = true, example = "1") @PathVariable Long id) {
        HouseOwnerVO houseOwnerVO = houseOwnerService.selectHouseOwnerVOById(id);
        return Result.ok().put("data", houseOwnerVO);
    }

    /**
     * 新增房屋业主关联信息
     *
     * @param houseOwner 房屋业主关联信息
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增房屋业主关联信息", description = "新增房屋业主关联信息")
    @ApiOperationSupport(order = 3, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result add(@Parameter(description = "房屋业主关联信息") @RequestBody HouseOwner houseOwner) {
        boolean result = houseOwnerService.save(houseOwner);
        if (result) {
            return Result.ok("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改房屋业主关联信息
     *
     * @param houseOwner 房屋业主关联信息
     * @return 操作结果
     */
    @PutMapping
    @Operation(summary = "修改房屋业主关联信息", description = "修改房屋业主关联信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result update(@Parameter(description = "房屋业主关联信息") @RequestBody HouseOwner houseOwner) {
        boolean result = houseOwnerService.updateById(houseOwner);
        if (result) {
            return Result.ok("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除房屋业主关联信息
     *
     * @param id 房屋业主关联ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除房屋业主关联信息", description = "根据ID删除房屋业主关联信息")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result delete(@Parameter(name = "id", description = "房屋业主关联ID", required = true, example = "1") @PathVariable Long id) {
        boolean result = houseOwnerService.removeById(id);
        if (result) {
            return Result.ok("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}