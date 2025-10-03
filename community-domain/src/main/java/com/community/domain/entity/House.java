package com.community.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 房屋表实体类
 */
@Data
@TableName("house")
public class House {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 所属社区ID
     */
    @TableField("community_id")
    private Long communityId;
    
    /**
     * 所属楼宇ID
     */
    @TableField("building_id")
    private Long buildingId;
    
    /**
     * 房间号
     */
    @TableField("room_no")
    private String roomNo;
    
    /**
     * 完整房间号
     */
    @TableField("full_room_no")
    private String fullRoomNo;
    
    /**
     * 房屋唯一编码
     */
    @TableField("house_code")
    private String houseCode;
    
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
    @TableField("shared_area")
    private BigDecimal sharedArea;
    
    /**
     * 房屋类型:住宅/商铺/办公/车库
     */
    @TableField("house_type")
    private String houseType;
    
    /**
     * 房屋户型
     */
    @TableField("house_layout")
    private String houseLayout;
    
    /**
     * 房屋朝向
     */
    @TableField("house_orientation")
    private String houseOrientation;
    
    /**
     * 车位号
     */
    @TableField("parking_space_no")
    private String parkingSpaceNo;
    
    /**
     * 车位类型
     */
    @TableField("parking_type")
    private String parkingType;
    
    /**
     * 房屋状态:空置/已售/已租/装修中
     */
    @TableField("house_status")
    private String houseStatus;
    
    /**
     * 装修状态
     */
    @TableField("decoration_status")
    private String decorationStatus;
    
    /**
     * 所在楼层
     */
    @TableField("floor_level")
    private Integer floorLevel;
    
    /**
     * 是否有阳台
     */
    @TableField("has_balcony")
    private Integer hasBalcony;
    
    /**
     * 是否有花园
     */
    @TableField("has_garden")
    private Integer hasGarden;
    
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