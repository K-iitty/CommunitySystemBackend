package com.community.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.community.common.Result;
import com.community.domain.entity.CommunityInfo;
import com.community.domain.vo.CommunityStatisticsVO;
import com.community.service.CommunityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
     * 获取社区总数
     *
     * @return 社区总数
     */
    @GetMapping("/count")
    @Operation(summary = "获取社区总数", description = "获取社区总数")
    @ApiOperationSupport(order = 1, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result count() {
        long count = communityInfoService.countAll();
        return Result.ok().put("data", count);
    }

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
    @ApiOperationSupport(order = 2, author = "张三")
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
    @Operation(
        summary = "根据社区名称或详细地址分页查询社区信息", 
        description = "根据社区名称或详细地址分页查询社区信息",
        parameters = {
            @Parameter(name = "pageNum", description = "当前页码", example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", example = "10"),
            @Parameter(name = "communityName", description = "社区名称"),
            @Parameter(name = "detailAddress", description = "详细地址")
        }
    )
    @ApiOperationSupport(order = 3, author = "开发团队")
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
    @ApiOperationSupport(order = 4, author = "张三")
    @SecurityRequirement(name = "Authorization")
    public Result getById(@Parameter(description = "社区ID") @PathVariable Long id) {
        CommunityInfo communityInfo = communityInfoService.getById(id);
        return Result.ok().put("data", communityInfo);
    }

    /**
     * 获取各社区统计信息
     *
     * @return 各社区的房屋、业主、车位统计数据
     */
    @GetMapping("/statistics")
    @Operation(summary = "获取各社区统计信息", description = "获取各社区的房屋、业主、车位统计数据")
    @ApiOperationSupport(order = 5, author = "开发团队")
    @SecurityRequirement(name = "Authorization")
    public Result getCommunityStatistics() {
        List<CommunityStatisticsVO> statistics = communityInfoService.getCommunityStatistics();
        return Result.ok().put("data", statistics);
    }

    /**
     * 新增社区信息（支持同时上传图片）
     *
     * @param communityName 社区名称
     * @param communityCode 社区编码
     * @param province 省份
     * @param city 城市
     * @param district 区县
     * @param areaCode 地区编码
     * @param detailAddress 详细地址
     * @param totalBuildings 楼座数
     * @param totalHouseholds 总户数
     * @param totalArea 占地面积
     * @param buildingArea 建筑面积
     * @param parkingArea 车位面积
     * @param greenArea 绿化面积
     * @param publicArea 公共场所面积
     * @param managerStaffId 负责人ID
     * @param managerName 负责人姓名
     * @param managerPhone 负责人电话
     * @param managerRemark 负责人备注
     * @param propertyCompany 物业公司
     * @param contactPhone 物业联系电话
     * @param builtYear 建成年份
     * @param occupancyRate 入住率
     * @param status 状态
     * @param remark 备注
     * @param files 社区图片文件（可选）
     * @return 操作结果
     */
    @PostMapping
    @Operation(summary = "新增社区信息", description = "新增社区信息（可同时上传图片）")
    @ApiOperationSupport(order = 6, author = "李四")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> add(
            @Parameter(description = "社区名称") @RequestParam(required = false) String communityName,
            @Parameter(description = "社区编码") @RequestParam(required = false) String communityCode,
            @Parameter(description = "省份") @RequestParam(required = false) String province,
            @Parameter(description = "城市") @RequestParam(required = false) String city,
            @Parameter(description = "区县") @RequestParam(required = false) String district,
            @Parameter(description = "地区编码") @RequestParam(required = false) String areaCode,
            @Parameter(description = "详细地址") @RequestParam(required = false) String detailAddress,
            @Parameter(description = "楼座数") @RequestParam(required = false) Integer totalBuildings,
            @Parameter(description = "总户数") @RequestParam(required = false) Integer totalHouseholds,
            @Parameter(description = "占地面积(㎡)") @RequestParam(required = false) BigDecimal totalArea,
            @Parameter(description = "建筑面积(㎡)") @RequestParam(required = false) BigDecimal buildingArea,
            @Parameter(description = "车位面积(㎡)") @RequestParam(required = false) BigDecimal parkingArea,
            @Parameter(description = "绿化面积(㎡)") @RequestParam(required = false) BigDecimal greenArea,
            @Parameter(description = "公共场所面积(㎡)") @RequestParam(required = false) BigDecimal publicArea,
            @Parameter(description = "负责人ID(关联物业员工)") @RequestParam(required = false) Long managerStaffId,
            @Parameter(description = "负责人姓名") @RequestParam(required = false) String managerName,
            @Parameter(description = "负责人电话") @RequestParam(required = false) String managerPhone,
            @Parameter(description = "负责人备注") @RequestParam(required = false) String managerRemark,
            @Parameter(description = "物业公司") @RequestParam(required = false) String propertyCompany,
            @Parameter(description = "物业联系电话") @RequestParam(required = false) String contactPhone,
            @Parameter(description = "建成年份") @RequestParam(required = false) Integer builtYear,
            @Parameter(description = "入住率(%)") @RequestParam(required = false) BigDecimal occupancyRate,
            @Parameter(description = "状态:正常/暂停/关闭") @RequestParam(required = false) String status,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "社区图片文件（可选）") @RequestParam(value = "files", required = false) MultipartFile[] files) {
        
        // 构建社区信息对象
        CommunityInfo communityInfo = new CommunityInfo();
        communityInfo.setCommunityName(communityName);
        communityInfo.setCommunityCode(communityCode);
        communityInfo.setProvince(province);
        communityInfo.setCity(city);
        communityInfo.setDistrict(district);
        communityInfo.setAreaCode(areaCode);
        communityInfo.setDetailAddress(detailAddress);
        communityInfo.setTotalBuildings(totalBuildings);
        communityInfo.setTotalHouseholds(totalHouseholds);
        communityInfo.setTotalArea(totalArea);
        communityInfo.setBuildingArea(buildingArea);
        communityInfo.setParkingArea(parkingArea);
        communityInfo.setGreenArea(greenArea);
        communityInfo.setPublicArea(publicArea);
        communityInfo.setManagerStaffId(managerStaffId);
        communityInfo.setManagerName(managerName);
        communityInfo.setManagerPhone(managerPhone);
        communityInfo.setManagerRemark(managerRemark);
        communityInfo.setPropertyCompany(propertyCompany);
        communityInfo.setContactPhone(contactPhone);
        communityInfo.setBuiltYear(builtYear);
        communityInfo.setOccupancyRate(occupancyRate);
        communityInfo.setStatus(status);
        communityInfo.setRemark(remark);
        
        // 保存社区信息
        boolean result = communityInfoService.save(communityInfo);
        
        // 如果有图片文件，则同时处理图片上传
        if (result && files != null && files.length > 0) {
            try {
                communityInfoService.updateCommunityImages(communityInfo.getId(), files);
            } catch (Exception e) {
                // 图片上传失败不应该影响社区信息的保存
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
     * 修改社区信息（支持同时上传图片）
     *
     * @param id 社区ID
     * @param communityName 社区名称
     * @param communityCode 社区编码
     * @param province 省份
     * @param city 城市
     * @param district 区县
     * @param areaCode 地区编码
     * @param detailAddress 详细地址
     * @param totalBuildings 楼座数
     * @param totalHouseholds 总户数
     * @param totalArea 占地面积
     * @param buildingArea 建筑面积
     * @param parkingArea 车位面积
     * @param greenArea 绿化面积
     * @param publicArea 公共场所面积
     * @param managerStaffId 负责人ID
     * @param managerName 负责人姓名
     * @param managerPhone 负责人电话
     * @param managerRemark 负责人备注
     * @param propertyCompany 物业公司
     * @param contactPhone 物业联系电话
     * @param builtYear 建成年份
     * @param occupancyRate 入住率
     * @param status 状态
     * @param remark 备注
     * @param files 社区图片文件（可选）
     * @return 操作结果
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改社区信息", description = "修改社区信息（可同时上传图片）")
    @ApiOperationSupport(order = 7, author = "李四")
    @SecurityRequirement(name = "Authorization")
    public Map<String, Object> update(
            @Parameter(description = "社区ID") @PathVariable Long id,
            @Parameter(description = "社区名称") @RequestParam(required = false) String communityName,
            @Parameter(description = "社区编码") @RequestParam(required = false) String communityCode,
            @Parameter(description = "省份") @RequestParam(required = false) String province,
            @Parameter(description = "城市") @RequestParam(required = false) String city,
            @Parameter(description = "区县") @RequestParam(required = false) String district,
            @Parameter(description = "地区编码") @RequestParam(required = false) String areaCode,
            @Parameter(description = "详细地址") @RequestParam(required = false) String detailAddress,
            @Parameter(description = "楼座数") @RequestParam(required = false) Integer totalBuildings,
            @Parameter(description = "总户数") @RequestParam(required = false) Integer totalHouseholds,
            @Parameter(description = "占地面积(㎡)") @RequestParam(required = false) BigDecimal totalArea,
            @Parameter(description = "建筑面积(㎡)") @RequestParam(required = false) BigDecimal buildingArea,
            @Parameter(description = "车位面积(㎡)") @RequestParam(required = false) BigDecimal parkingArea,
            @Parameter(description = "绿化面积(㎡)") @RequestParam(required = false) BigDecimal greenArea,
            @Parameter(description = "公共场所面积(㎡)") @RequestParam(required = false) BigDecimal publicArea,
            @Parameter(description = "负责人ID(关联物业员工)") @RequestParam(required = false) Long managerStaffId,
            @Parameter(description = "负责人姓名") @RequestParam(required = false) String managerName,
            @Parameter(description = "负责人电话") @RequestParam(required = false) String managerPhone,
            @Parameter(description = "负责人备注") @RequestParam(required = false) String managerRemark,
            @Parameter(description = "物业公司") @RequestParam(required = false) String propertyCompany,
            @Parameter(description = "物业联系电话") @RequestParam(required = false) String contactPhone,
            @Parameter(description = "建成年份") @RequestParam(required = false) Integer builtYear,
            @Parameter(description = "入住率(%)") @RequestParam(required = false) BigDecimal occupancyRate,
            @Parameter(description = "状态:正常/暂停/关闭") @RequestParam(required = false) String status,
            @Parameter(description = "备注") @RequestParam(required = false) String remark,
            @Parameter(description = "社区图片文件（可选）") @RequestParam(value = "files", required = false) MultipartFile[] files) {
        
        // 先从数据库获取原始社区信息
        CommunityInfo communityInfo = communityInfoService.getById(id);
        if (communityInfo == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("msg", "社区信息不存在");
            return response;
        }
        
        // 更新社区信息对象
        communityInfo.setCommunityName(communityName);
        communityInfo.setCommunityCode(communityCode);
        communityInfo.setProvince(province);
        communityInfo.setCity(city);
        communityInfo.setDistrict(district);
        communityInfo.setAreaCode(areaCode);
        communityInfo.setDetailAddress(detailAddress);
        communityInfo.setTotalBuildings(totalBuildings);
        communityInfo.setTotalHouseholds(totalHouseholds);
        communityInfo.setTotalArea(totalArea);
        communityInfo.setBuildingArea(buildingArea);
        communityInfo.setParkingArea(parkingArea);
        communityInfo.setGreenArea(greenArea);
        communityInfo.setPublicArea(publicArea);
        communityInfo.setManagerStaffId(managerStaffId);
        communityInfo.setManagerName(managerName);
        communityInfo.setManagerPhone(managerPhone);
        communityInfo.setManagerRemark(managerRemark);
        communityInfo.setPropertyCompany(propertyCompany);
        communityInfo.setContactPhone(contactPhone);
        communityInfo.setBuiltYear(builtYear);
        communityInfo.setOccupancyRate(occupancyRate);
        communityInfo.setStatus(status);
        communityInfo.setRemark(remark);
        
        // 保存社区信息
        boolean result = communityInfoService.updateById(communityInfo);
        
        // 如果有图片文件，则同时处理图片上传
        if (result && files != null && files.length > 0) {
            try {
                communityInfoService.updateCommunityImages(communityInfo.getId(), files);
            } catch (Exception e) {
                // 图片上传失败不应该影响社区信息的保存
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
     * 删除社区信息
     *
     * @param id 社区ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除社区信息", description = "根据ID删除社区信息")
    @ApiOperationSupport(order = 8, author = "王五")
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