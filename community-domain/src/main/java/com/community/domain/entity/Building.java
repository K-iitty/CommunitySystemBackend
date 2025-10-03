package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 楼栋表实体类
 */
@Data
@TableName("building")
public class Building {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 社区ID
     */
    @TableField("community_id")
    private Long communityId;
    
    /**
     * 楼宇编号
     */
    @TableField("building_no")
    private String buildingNo;
    
    /**
     * 楼宇名称
     */
    @TableField("building_name")
    private String buildingName;
    
    /**
     * 楼宇别名
     */
    @TableField("building_alias")
    private String buildingAlias;
    
    /**
     * 单元号
     */
    @TableField("unit_no")
    private String unitNo;
    
    /**
     * 单元名称
     */
    @TableField("unit_name")
    private String unitName;
    
    /**
     * 楼宇类型:住宅/商业/办公/综合
     */
    @TableField("building_type")
    private String buildingType;
    
    /**
     * 建筑结构
     */
    @TableField("building_structure")
    private String buildingStructure;
    
    /**
     * 楼宇用途
     */
    @TableField("building_purpose")
    private String buildingPurpose;
    
    /**
     * 单元数
     */
    @TableField("total_units")
    private Integer totalUnits;
    
    /**
     * 层数(地上)
     */
    @TableField("total_floors")
    private Integer totalFloors;
    
    /**
     * 地下层数
     */
    @TableField("underground_floors")
    private Integer undergroundFloors;
    
    /**
     * 总房屋数量
     */
    @TableField("total_households")
    private Integer totalHouseholds;
    
    /**
     * 每层户数
     */
    @TableField("households_per_floor")
    private Integer householdsPerFloor;
    
    /**
     * 建筑面积(㎡)
     */
    @TableField("building_area")
    private BigDecimal buildingArea;
    
    /**
     * 使用面积(㎡)
     */
    @TableField("usable_area")
    private BigDecimal usableArea;
    
    /**
     * 公摊面积(㎡)
     */
    @TableField("public_area")
    private BigDecimal publicArea;
    
    /**
     * 建成日期
     */
    @TableField("built_date")
    private LocalDate builtDate;
    
    /**
     * 验收日期
     */
    @TableField("acceptance_date")
    private LocalDate acceptanceDate;
    
    /**
     * 交付使用日期
     */
    @TableField("delivery_date")
    private LocalDate deliveryDate;
    
    /**
     * 经度
     */
    @TableField("longitude")
    private BigDecimal longitude;
    
    /**
     * 纬度
     */
    @TableField("latitude")
    private BigDecimal latitude;
    
    /**
     * 楼宇详细地址
     */
    @TableField("building_address")
    private String buildingAddress;
    
    /**
     * 楼宇朝向
     */
    @TableField("orientation")
    private String orientation;
    
    /**
     * 是否有电梯
     */
    @TableField("has_elevator")
    private Integer hasElevator;
    
    /**
     * 电梯数量
     */
    @TableField("elevator_count")
    private Integer elevatorCount;
    
    /**
     * 楼梯数量
     */
    @TableField("stair_count")
    private Integer stairCount;
    
    /**
     * 是否有地下停车场
     */
    @TableField("has_underground_parking")
    private Integer hasUndergroundParking;
    
    /**
     * 是否有天台
     */
    @TableField("has_rooftop")
    private Integer hasRooftop;
    
    /**
     * 状态:正常/维修中/停用/拆除
     */
    @TableField("status")
    private String status;
    
    /**
     * 楼宇等级
     */
    @TableField("building_grade")
    private String buildingGrade;
    
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