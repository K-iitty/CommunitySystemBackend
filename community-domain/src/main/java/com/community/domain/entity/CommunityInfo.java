package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 社区信息表实体类
 */
@Data
@TableName("community_info")
public class CommunityInfo {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 社区名称
     */
    @TableField("community_name")
    private String communityName;
    
    /**
     * 社区编码
     */
    @TableField("community_code")
    private String communityCode;
    
    /**
     * 省份
     */
    @TableField("province")
    private String province;
    
    /**
     * 城市
     */
    @TableField("city")
    private String city;
    
    /**
     * 区县
     */
    @TableField("district")
    private String district;
    
    /**
     * 地区编码
     */
    @TableField("area_code")
    private String areaCode;
    
    /**
     * 详细地址
     */
    @TableField("detail_address")
    private String detailAddress;
    
    /**
     * 楼座数
     */
    @TableField("total_buildings")
    private Integer totalBuildings;
    
    /**
     * 总户数
     */
    @TableField("total_households")
    private Integer totalHouseholds;
    
    /**
     * 占地面积(㎡)
     */
    @TableField("total_area")
    private BigDecimal totalArea;
    
    /**
     * 建筑面积(㎡)
     */
    @TableField("building_area")
    private BigDecimal buildingArea;
    
    /**
     * 车位面积(㎡)
     */
    @TableField("parking_area")
    private BigDecimal parkingArea;
    
    /**
     * 绿化面积(㎡)
     */
    @TableField("green_area")
    private BigDecimal greenArea;
    
    /**
     * 公共场所面积(㎡)
     */
    @TableField("public_area")
    private BigDecimal publicArea;
    
    /**
     * 负责人ID(关联物业员工)
     */
    @TableField("manager_staff_id")
    private Long managerStaffId;
    
    /**
     * 负责人姓名
     */
    @TableField("manager_name")
    private String managerName;
    
    /**
     * 负责人电话
     */
    @TableField("manager_phone")
    private String managerPhone;
    
    /**
     * 负责人备注信息
     */
    @TableField("manager_remark")
    private String managerRemark;
    
    /**
     * 物业公司
     */
    @TableField("property_company")
    private String propertyCompany;
    
    /**
     * 物业联系电话
     */
    @TableField("contact_phone")
    private String contactPhone;
    
    /**
     * 建成年份
     */
    @TableField("built_year")
    private Integer builtYear;
    
    /**
     * 入住率(%)
     */
    @TableField("occupancy_rate")
    private BigDecimal occupancyRate;
    
    /**
     * 状态:正常/暂停/关闭
     */
    @TableField("status")
    private String status;
    
    /**
     * 备注信息
     */
    @TableField("remark")
    private String remark;
    
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}