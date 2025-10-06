# 数据库-建表语句

### 1. 社区信息表

```sql
CREATE TABLE community_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    community_name VARCHAR(100) NOT NULL COMMENT '社区名称',
    community_code VARCHAR(50) UNIQUE COMMENT '社区编码',
    
    -- 地区信息
    province VARCHAR(50) COMMENT '省份',
    city VARCHAR(50) COMMENT '城市',
    district VARCHAR(50) COMMENT '区县',
    area_code VARCHAR(20) COMMENT '地区编码',
    detail_address VARCHAR(200) NOT NULL COMMENT '详细地址',
    
    -- 规模信息
    total_buildings INT DEFAULT 0 COMMENT '楼座数',
    total_households INT DEFAULT 0 COMMENT '总户数',
    
    -- 面积信息
    total_area DECIMAL(12,2) COMMENT '占地面积(㎡)',
    building_area DECIMAL(12,2) COMMENT '建筑面积(㎡)',
    parking_area DECIMAL(12,2) COMMENT '车位面积(㎡)',
    green_area DECIMAL(12,2) COMMENT '绿化面积(㎡)',
    public_area DECIMAL(12,2) COMMENT '公共场所面积(㎡)',
    
    -- 负责人信息
    manager_staff_id BIGINT COMMENT '负责人ID(关联物业员工)',
    manager_name VARCHAR(50) COMMENT '负责人姓名',
    manager_phone VARCHAR(20) COMMENT '负责人电话',
    manager_remark VARCHAR(500) COMMENT '负责人备注信息',
    
    -- 基础信息
    property_company VARCHAR(100) COMMENT '物业公司',
    contact_phone VARCHAR(20) COMMENT '物业联系电话',
    built_year YEAR COMMENT '建成年份',
    occupancy_rate DECIMAL(5,2) COMMENT '入住率(%)',
    
    -- 状态和时间
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态:正常/暂停/关闭',
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_community_area (province, city, district),
    INDEX idx_community_manager (manager_staff_id),
    INDEX idx_community_code (community_code)
);
```



### 2. 部门表

```sql
CREATE TABLE department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL COMMENT '部门名称',
    parent_id BIGINT DEFAULT 0 COMMENT '父部门ID',
    department_code VARCHAR(50) UNIQUE COMMENT '部门编码',
    department_level INT DEFAULT 1 COMMENT '部门层级',
    sort_order INT DEFAULT 0 COMMENT '排序',
    status VARCHAR(20) DEFAULT '启用' COMMENT '状态:启用/禁用',
    description TEXT COMMENT '描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_parent_id (parent_id),
    INDEX idx_status (status)
);
```

### 3. 角色表

```sql
CREATE TABLE role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(100) NOT NULL COMMENT '角色名称',
    role_code VARCHAR(50) UNIQUE COMMENT '角色编码',
    role_type VARCHAR(50) COMMENT '角色类型',
    description TEXT COMMENT '描述',
    member_count INT DEFAULT 0 COMMENT '成员数量',
    status VARCHAR(20) DEFAULT '启用' COMMENT '状态:启用/禁用',
    permissions TEXT COMMENT '权限配置(JSON)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_role_type (role_type),
    INDEX idx_status (status)
);
```


### 4. 员工/物业表（整合版）

```sql
CREATE TABLE staff (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 账户信息
    username VARCHAR(50) UNIQUE COMMENT '登录账号',
    password VARCHAR(100) COMMENT '登录密码',
    
    -- 基本信息
    name VARCHAR(50) NOT NULL COMMENT '员工姓名',
    phone VARCHAR(20) NOT NULL COMMENT '手机号',
    id_card VARCHAR(18) COMMENT '身份证号',
    work_no VARCHAR(20) UNIQUE COMMENT '工号',
    gender VARCHAR(10) DEFAULT '保密' COMMENT '性别:男/女/保密',
    birth_date DATE COMMENT '员工生日',
    avatar VARCHAR(500) COMMENT '头像',
    
    -- 联系信息
    email VARCHAR(100) COMMENT '邮箱',
    wechat VARCHAR(100) COMMENT '微信账号',
    telephone_area_code VARCHAR(10) COMMENT '电话区号',
    telephone_number VARCHAR(20) COMMENT '电话号码',
    telephone_extension VARCHAR(20) COMMENT '分机号码',
    emergency_contact VARCHAR(50) COMMENT '紧急联系人',
    emergency_phone VARCHAR(20) COMMENT '紧急联系电话',
    
    -- 教育信息
    graduate_school VARCHAR(200) COMMENT '毕业院校',
    graduation_date DATE COMMENT '毕业时间',
    education_level VARCHAR(50) COMMENT '学历',
    major VARCHAR(100) COMMENT '所学专业',
    
    -- 工作信息
    department_id BIGINT NOT NULL COMMENT '所属部门ID',
    role_id BIGINT COMMENT '角色ID',
    position VARCHAR(50) COMMENT '职位',
    job_title VARCHAR(50) COMMENT '职称',
    hire_date DATE COMMENT '入职日期',
    work_status VARCHAR(20) DEFAULT '在职' COMMENT '工作状态:在职/休假/离职',
    is_manager TINYINT DEFAULT 0 COMMENT '是否可担任负责人',
    
    -- 籍贯信息
    native_place VARCHAR(200) COMMENT '籍贯',
    
    -- 状态信息
    account_status VARCHAR(20) DEFAULT '正常' COMMENT '账号状态:正常/禁用/锁定',
    online_status TINYINT DEFAULT 0 COMMENT '在线状态:0-离线,1-在线',
    
    -- 登录信息
    last_login_time TIMESTAMP NULL COMMENT '最后登录时间',
    last_login_ip VARCHAR(50) COMMENT '最后登录IP',
    login_count INT DEFAULT 0 COMMENT '登录次数',
    
    -- 系统信息
    remark TEXT COMMENT '备注',
    created_by BIGINT COMMENT '创建人',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (department_id) REFERENCES department(id),
    FOREIGN KEY (role_id) REFERENCES role(id),
    FOREIGN KEY (created_by) REFERENCES staff(id),
    
    INDEX idx_department (department_id),
    INDEX idx_role (role_id),
    INDEX idx_account_status (account_status),
    INDEX idx_work_status (work_status),
    INDEX idx_phone (phone),
    INDEX idx_work_no (work_no),
    INDEX idx_is_manager (is_manager)
);
```



### 5. 楼栋表

```sql
CREATE TABLE building (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    community_id BIGINT NOT NULL COMMENT '社区ID',
    
    -- 基础信息
    building_no VARCHAR(20) NOT NULL COMMENT '楼宇编号',
    building_name VARCHAR(100) NOT NULL COMMENT '楼宇名称',
    building_alias VARCHAR(100) COMMENT '楼宇别名',
    unit_no VARCHAR(10) COMMENT '单元号',
    unit_name VARCHAR(50) COMMENT '单元名称',
    
    -- 建筑信息
    building_type VARCHAR(50) DEFAULT '住宅' COMMENT '楼宇类型:住宅/商业/办公/综合',
    building_structure VARCHAR(50) COMMENT '建筑结构',
    building_purpose VARCHAR(100) COMMENT '楼宇用途',
    
    -- 规模信息
    total_units INT DEFAULT 1 COMMENT '单元数',
    total_floors INT NOT NULL COMMENT '层数(地上)',
    underground_floors INT DEFAULT 0 COMMENT '地下层数',
    total_households INT DEFAULT 0 COMMENT '总房屋数量',
    households_per_floor INT COMMENT '每层户数',
    
    -- 面积信息
    building_area DECIMAL(12,2) COMMENT '建筑面积(㎡)',
    usable_area DECIMAL(12,2) COMMENT '使用面积(㎡)',
    public_area DECIMAL(12,2) COMMENT '公摊面积(㎡)',
    
    -- 时间信息
    built_date DATE COMMENT '建成日期',
    acceptance_date DATE COMMENT '验收日期',
    delivery_date DATE COMMENT '交付使用日期',
    
    -- 位置信息
    longitude DECIMAL(10,6) COMMENT '经度',
    latitude DECIMAL(10,6) COMMENT '纬度',
    building_address VARCHAR(200) COMMENT '楼宇详细地址',
    orientation VARCHAR(50) COMMENT '楼宇朝向',
    
    -- 设施信息
    has_elevator TINYINT DEFAULT 0 COMMENT '是否有电梯',
    elevator_count INT DEFAULT 0 COMMENT '电梯数量',
    stair_count INT DEFAULT 1 COMMENT '楼梯数量',
    has_underground_parking TINYINT DEFAULT 0 COMMENT '是否有地下停车场',
    has_rooftop TINYINT DEFAULT 0 COMMENT '是否有天台',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态:正常/维修中/停用/拆除',
    building_grade VARCHAR(20) COMMENT '楼宇等级',
    
    -- 备注和其他
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    INDEX idx_community_building (community_id, building_no),
    INDEX idx_building_type (building_type),
    INDEX idx_building_status (status)
);
```



### 6. 房屋表

```sql
CREATE TABLE house (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 关联信息
    community_id BIGINT NOT NULL COMMENT '所属社区ID',
    building_id BIGINT NOT NULL COMMENT '所属楼宇ID',
    
    -- 房屋标识
    room_no VARCHAR(20) NOT NULL COMMENT '房间号',
    full_room_no VARCHAR(50) COMMENT '完整房间号',
    house_code VARCHAR(50) UNIQUE COMMENT '房屋唯一编码',
    
    -- 面积信息
    building_area DECIMAL(10,2) NOT NULL COMMENT '建筑面积(㎡)',
    usable_area DECIMAL(10,2) COMMENT '使用面积(㎡)',
    shared_area DECIMAL(10,2) COMMENT '公摊面积(㎡)',
    
    -- 房屋属性
    house_type VARCHAR(50) NOT NULL COMMENT '房屋类型:住宅/商铺/办公/车库',
    house_layout VARCHAR(50) COMMENT '房屋户型',
    house_orientation VARCHAR(20) COMMENT '房屋朝向',
    
    -- 车位信息（如果是车位类型房屋）
    parking_space_no VARCHAR(50) COMMENT '车位号',
    parking_type VARCHAR(20) COMMENT '车位类型',
    
    -- 状态信息
    house_status VARCHAR(20) DEFAULT '空置' COMMENT '房屋状态:空置/已售/已租/装修中',
    decoration_status VARCHAR(20) COMMENT '装修状态',
    
    -- 其他信息
    floor_level INT COMMENT '所在楼层',
    has_balcony TINYINT DEFAULT 0 COMMENT '是否有阳台',
    has_garden TINYINT DEFAULT 0 COMMENT '是否有花园',
    remark TEXT COMMENT '备注信息',
    
    -- 系统字段
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    FOREIGN KEY (building_id) REFERENCES building(id),
    
    INDEX idx_community_house (community_id, room_no),
    INDEX idx_building_house (building_id, room_no),
    INDEX idx_house_status (house_status),
    INDEX idx_house_type (house_type)
);
```



### 7. 业主表

```sql
CREATE TABLE owner (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    -- 登陆、注册
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    
    -- 基本信息
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    phone VARCHAR(20) NOT NULL COMMENT '手机号',
    id_card VARCHAR(18) NOT NULL COMMENT '身份证号',
    gender VARCHAR(10) DEFAULT '未知' COMMENT '性别',
    birth_date DATE COMMENT '出生日期',
    
    -- 身份信息
    owner_type VARCHAR(20) DEFAULT '业主' COMMENT '业主类型:业主/家属/租客',
    political_status VARCHAR(20) COMMENT '政治面貌',
    marital_status VARCHAR(20) COMMENT '婚姻状况',
    nationality VARCHAR(50) DEFAULT '汉族' COMMENT '民族',
    
    -- 户籍信息
    household_type VARCHAR(20) COMMENT '户口类型',
    census_register VARCHAR(200) COMMENT '户籍所在地',
    temporary_resident_no VARCHAR(50) COMMENT '暂住证号',
    
    -- 联系信息
    current_address VARCHAR(200) COMMENT '现住地址',
    emergency_contact_name VARCHAR(50) COMMENT '紧急联系人姓名',
    emergency_contact_relation VARCHAR(20) COMMENT '紧急联系人关系',
    emergency_contact_phone VARCHAR(20) COMMENT '紧急联系人手机号码',
    
    -- 居住信息
    residence_type VARCHAR(20) COMMENT '居住类型',
    move_in_date DATE COMMENT '入住日期',
    move_out_date DATE COMMENT '迁出日期',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态:正常/冻结/迁出',
    verify_status VARCHAR(20) DEFAULT '未认证' COMMENT '认证状态',
    
    -- 系统信息
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE KEY uk_id_card (id_card),
    INDEX idx_phone (phone),
    INDEX idx_name (name),
    INDEX idx_owner_type (owner_type),
    INDEX idx_status (status)
);
```



### 8. 业主房屋关联表

```sql
CREATE TABLE house_owner (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    house_id BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    
    -- 关联属性
    relationship VARCHAR(20) NOT NULL COMMENT '关系类型:业主/家属/租客/其他',
    is_primary TINYINT DEFAULT 0 COMMENT '是否为主要联系人',
    is_verified TINYINT DEFAULT 0 COMMENT '是否已验证',
    
    -- 时间信息
    start_date DATE NOT NULL COMMENT '关系开始日期',
    end_date DATE COMMENT '关系结束日期',
    
    -- 租赁信息（如果是租客）
    rent_amount DECIMAL(10,2) COMMENT '租金金额',
    rent_pay_cycle VARCHAR(20) COMMENT '租金支付周期',
    deposit_amount DECIMAL(10,2) COMMENT '押金金额',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态:正常/到期/终止',
    
    -- 验证信息
    verified_by BIGINT COMMENT '验证人ID',
    verified_at TIMESTAMP NULL COMMENT '验证时间',
    verify_remark VARCHAR(200) COMMENT '验证备注',
    
    -- 系统字段
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (house_id) REFERENCES house(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (verified_by) REFERENCES staff(id),
    
    UNIQUE KEY uk_house_owner (house_id, owner_id, relationship),
    INDEX idx_relationship (relationship),
    INDEX idx_status (status),
    INDEX idx_dates (start_date, end_date)
);
```


### 10. 停车场表（整合版）

```sql
CREATE TABLE parking_lot (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    community_id BIGINT NOT NULL COMMENT '所属小区ID',
    
    -- 基础信息
    lot_name VARCHAR(100) NOT NULL COMMENT '停车场名称',
    lot_code VARCHAR(50) UNIQUE COMMENT '停车场编码',
    lot_category VARCHAR(50) DEFAULT '普通' COMMENT '车库类别',
    zone_name VARCHAR(100) COMMENT '车库区域名称',
    zone_code VARCHAR(50) COMMENT '区域编码',
    
    -- 联系信息
    contact_person VARCHAR(50) COMMENT '联系人',
    contact_phone VARCHAR(20) COMMENT '联系人电话',
    
    -- 地址信息
    address VARCHAR(200) COMMENT '地址',
    detail_address VARCHAR(200) COMMENT '详细地址',
    longitude DECIMAL(10,6) COMMENT '经度坐标',
    latitude DECIMAL(10,6) COMMENT '纬度坐标',
    
    -- 规模信息
    total_spaces INT NOT NULL COMMENT '车位数量',
    fixed_spaces INT DEFAULT 0 COMMENT '固定车位数',
    temp_spaces INT DEFAULT 0 COMMENT '临时车位数',
    start_no INT COMMENT '起始编号',
    end_no INT COMMENT '结束编号',
    floor_level INT COMMENT '所在楼层',
    
    -- 营业信息
    business_hours VARCHAR(100) COMMENT '营业时间',
    is_allow_foreign TINYINT DEFAULT 1 COMMENT '是否允许外来车辆进入',
    
    -- 计费配置
    charge_method VARCHAR(50) COMMENT '计费方式',
    charge_standard VARCHAR(100) COMMENT '收费标准',
    first_duration INT COMMENT '首段时长(分钟)',
    first_fee DECIMAL(8,2) COMMENT '首段费用',
    unit_duration INT COMMENT '单位时长(分钟)',
    unit_fee DECIMAL(8,2) COMMENT '单位费用',
    daily_max_fee DECIMAL(8,2) COMMENT '每日封顶费用',
    monthly_fee DECIMAL(10,2) COMMENT '月租费用',
    free_duration INT DEFAULT 15 COMMENT '免费时长(分钟)',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '启用' COMMENT '状态:启用/停用/维修',
    
    -- 系统信息
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    INDEX idx_community_lot (community_id, lot_name),
    INDEX idx_lot_status (status)
);
```



### 9. 车辆表

```sql
CREATE TABLE vehicle (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT NOT NULL COMMENT '车主ID',
    
    -- 车辆信息
    plate_number VARCHAR(20) NOT NULL COMMENT '车牌号',
    vehicle_type VARCHAR(20) DEFAULT '小型车' COMMENT '车辆类型',
    brand VARCHAR(50) COMMENT '品牌',
    model VARCHAR(50) COMMENT '型号',
    color VARCHAR(20) COMMENT '颜色',
    
    -- 关联信息
    fixed_space_id BIGINT COMMENT '固定车位ID',
    
    -- 证件信息
    vehicle_license_no VARCHAR(50) COMMENT '行驶证号',
    engine_no VARCHAR(50) COMMENT '发动机号',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态:正常/冻结/黑名单',
    register_date DATE COMMENT '登记日期',
    
    -- 系统信息
    remark VARCHAR(200) COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (fixed_space_id) REFERENCES parking_space(id),
    
    UNIQUE KEY uk_plate_number (plate_number),
    INDEX idx_owner_vehicle (owner_id),
    INDEX idx_vehicle_status (status)
);
```


### 11. 车位表

```sql
CREATE TABLE parking_space (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    parking_lot_id BIGINT NOT NULL COMMENT '停车场ID',
    
    -- 车位标识
    space_no VARCHAR(50) NOT NULL COMMENT '车位编号',
    full_space_no VARCHAR(100) COMMENT '完整车位编号',
    
    -- 车位属性
    space_type VARCHAR(20) DEFAULT '固定' COMMENT '车位类型:固定/临时/VIP',
    space_area DECIMAL(8,2) COMMENT '车位面积(㎡)',
    space_status VARCHAR(20) DEFAULT '空闲' COMMENT '车位状态:空闲/已租/占用/维修',
    
    -- 关联信息
    owner_id BIGINT COMMENT '关联业主ID',
    vehicle_id BIGINT COMMENT '关联车辆ID',
    
    -- 收费信息
    monthly_fee DECIMAL(10,2) COMMENT '月租费用',
    
    -- 系统信息
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    
    UNIQUE KEY uk_space_no (parking_lot_id, space_no),
    INDEX idx_space_status (space_status),
    INDEX idx_space_type (space_type),
    INDEX idx_owner_space (owner_id)
);
```



### 12. 停车记录表

```sql
CREATE TABLE parking_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 车辆信息
    vehicle_id BIGINT NOT NULL COMMENT '车辆ID',
    plate_number VARCHAR(20) NOT NULL COMMENT '车牌号',
    vehicle_type VARCHAR(20) DEFAULT '小型车' COMMENT '车辆类型',
    
    -- 车主信息
    owner_id BIGINT COMMENT '车主ID',
    owner_name VARCHAR(50) COMMENT '车主姓名',
    owner_phone VARCHAR(20) COMMENT '车主手机号',
    
    -- 停车信息
    parking_lot_id BIGINT NOT NULL COMMENT '停车场ID',
    parking_space_id BIGINT COMMENT '车位ID',
    gate_no VARCHAR(50) COMMENT '出入闸机号',
    
    -- 时间信息
    entry_time TIMESTAMP NOT NULL COMMENT '入场时间',
    exit_time TIMESTAMP NULL COMMENT '出场时间',
    pay_time TIMESTAMP NULL COMMENT '支付时间',
    
    -- 费用信息
    duration_minutes INT COMMENT '停车时长(分钟)',
    parking_fee DECIMAL(10,2) DEFAULT 0.00 COMMENT '停车费用',
    actual_fee DECIMAL(10,2) DEFAULT 0.00 COMMENT '实收费用',
    
    -- 支付信息
    payment_method VARCHAR(20) COMMENT '支付方式',
    payment_status VARCHAR(20) DEFAULT '未支付' COMMENT '支付状态',
    transaction_no VARCHAR(100) COMMENT '交易流水号',
    
    -- 系统信息
    remark VARCHAR(200) COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id),
    FOREIGN KEY (parking_space_id) REFERENCES parking_space(id),
    
    INDEX idx_plate_time (plate_number, entry_time),
    INDEX idx_entry_time (entry_time),
    INDEX idx_exit_time (exit_time),
    INDEX idx_payment_status (payment_status)
);
```



### 13. 门禁设备表

```sql
CREATE TABLE access_control_device (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    community_id BIGINT NOT NULL COMMENT '所属小区ID',
    building_id BIGINT COMMENT '所属楼栋ID',
    
    -- 设备信息
    device_name VARCHAR(100) NOT NULL COMMENT '门禁名称',
    device_type VARCHAR(50) NOT NULL COMMENT '设备类型',
    device_code VARCHAR(100) UNIQUE COMMENT '配置码/设备编码',
    device_sn VARCHAR(100) COMMENT '设备序列号',
    
    -- 位置信息
    install_location VARCHAR(200) COMMENT '安装位置',
    install_date DATE COMMENT '安装日期',
    
    -- 状态信息
    device_status VARCHAR(20) DEFAULT '启用' COMMENT '设备状态:启用/禁用/故障',
    online_status TINYINT DEFAULT 0 COMMENT '在线状态:0-离线,1-在线',
    
    -- 功能支持
    support_face TINYINT DEFAULT 0 COMMENT '支持人脸识别',
    support_fingerprint TINYINT DEFAULT 0 COMMENT '支持指纹识别',
    support_card TINYINT DEFAULT 0 COMMENT '支持刷卡',
    support_qrcode TINYINT DEFAULT 0 COMMENT '支持二维码',
    
    -- 配置信息
    arrears_ban_enabled TINYINT DEFAULT 0 COMMENT '欠费禁入',
    visitor_approval_enabled TINYINT DEFAULT 0 COMMENT '访客审核',
    open_duration INT DEFAULT 10 COMMENT '开门持续时间(秒)',
    
    -- 系统信息
    last_heartbeat TIMESTAMP NULL COMMENT '最后心跳时间',
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    FOREIGN KEY (building_id) REFERENCES building(id),
    
    INDEX idx_community_device (community_id, device_status),
    INDEX idx_device_type (device_type),
    INDEX idx_device_code (device_code)
);
```



### 14. 门禁记录表（整合版）

```sql
CREATE TABLE access_control_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 人员信息
    person_id BIGINT COMMENT '人员ID(业主/员工/访客)',
    person_type VARCHAR(20) NOT NULL COMMENT '人员类型:owner/staff/visitor',
    person_name VARCHAR(50) NOT NULL COMMENT '姓名',
    person_phone VARCHAR(20) COMMENT '手机号',
    id_card VARCHAR(18) COMMENT '身份证号',
    
    -- 出入信息
    device_id BIGINT NOT NULL COMMENT '门禁设备ID',
    community_id BIGINT NOT NULL COMMENT '所属小区ID',
    access_type VARCHAR(20) NOT NULL COMMENT '出入类型:entry/exit',
    access_method VARCHAR(50) NOT NULL COMMENT '出入方式',
    
    -- 权限信息
    permission_type VARCHAR(50) COMMENT '权限类型',
    valid_start_time TIMESTAMP NULL COMMENT '权限开始时间',
    valid_end_time TIMESTAMP NULL COMMENT '权限结束时间',
    
    -- 时间信息
    access_time TIMESTAMP NOT NULL COMMENT '出入时间',
    
    -- 位置信息
    gate_name VARCHAR(100) COMMENT '出入闸机名称',
    location_info VARCHAR(200) COMMENT '位置信息',
    
    -- 验证信息
    verify_result VARCHAR(20) DEFAULT '成功' COMMENT '验证结果',
    fail_reason VARCHAR(200) COMMENT '失败原因',
    
    -- 图片信息
    capture_image VARCHAR(500) COMMENT '抓拍图片',
    
    -- 审批信息
    approved_by BIGINT COMMENT '审批人ID',
    
    -- 系统信息
    remark VARCHAR(200) COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (device_id) REFERENCES access_control_device(id),
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    FOREIGN KEY (person_id) REFERENCES owner(id) ON DELETE SET NULL,
    FOREIGN KEY (approved_by) REFERENCES staff(id),
    
    INDEX idx_person_access (person_type, person_id, access_time),
    INDEX idx_device_time (device_id, access_time),
    INDEX idx_phone_time (person_phone, access_time),
    INDEX idx_access_time (access_time)
);
```



### 15. 仪表配置表

```sql
CREATE TABLE meter_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 分类信息
    category_name VARCHAR(50) NOT NULL COMMENT '分类名称(电表/水表/燃气表)',
    meter_type VARCHAR(50) NOT NULL COMMENT '仪表种类',
    product_id VARCHAR(100) COMMENT '产品ID',
    config_params TEXT COMMENT '配置参数(JSON格式)',
    
    -- 计量配置
    unit VARCHAR(20) NOT NULL COMMENT '计量单位',
    decimal_places INT DEFAULT 2 COMMENT '小数位数',
    max_value DECIMAL(12,2) COMMENT '最大值',
    min_value DECIMAL(12,2) DEFAULT 0 COMMENT '最小值',
    
    -- 收费配置
    charge_standard VARCHAR(100) COMMENT '收费标准',
    unit_price DECIMAL(10,4) COMMENT '单价',
    calculation_method VARCHAR(50) COMMENT '计算方式',
    
    -- 通信配置
    comm_protocol VARCHAR(50) COMMENT '通信协议',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '启用' COMMENT '状态:启用/禁用',
    is_default TINYINT DEFAULT 0 COMMENT '是否默认配置',
    
    -- 系统信息
    remark TEXT COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE KEY uk_category_type (category_name, meter_type),
    INDEX idx_category (category_name),
    INDEX idx_status (status)
);
```



### 16. 仪表信息表

```sql
CREATE TABLE meter_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 关联信息
    community_id BIGINT NOT NULL COMMENT '所属小区ID',
    house_id BIGINT COMMENT '房间ID',
    building_id BIGINT COMMENT '楼栋ID',
    config_id BIGINT COMMENT '仪表配置ID',
    
    -- 仪表基本信息
    meter_name VARCHAR(100) NOT NULL COMMENT '仪表名称',
    category_name VARCHAR(50) NOT NULL COMMENT '分类名称',
    meter_type VARCHAR(50) NOT NULL COMMENT '仪表种类',
    meter_code VARCHAR(100) UNIQUE COMMENT '仪表编码',
    meter_sn VARCHAR(100) COMMENT '仪表序列号',
    
    -- 位置信息
    location_type VARCHAR(20) DEFAULT '房间表' COMMENT '仪表位置',
    install_location VARCHAR(200) COMMENT '安装位置描述',
    install_date DATE COMMENT '安装日期',
    
    -- 读数信息
    initial_reading DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '起始读数',
    current_reading DECIMAL(12,2) DEFAULT 0 COMMENT '当前读数',
    max_reading DECIMAL(12,2) COMMENT '最大读数',
    total_usage DECIMAL(12,2) DEFAULT 0 COMMENT '总用量',
    
    -- 计量单位
    unit VARCHAR(20) COMMENT '计量单位',
    
    -- 收费信息
    charge_standard VARCHAR(100) COMMENT '收费标准',
    
    -- 状态信息
    online_status TINYINT DEFAULT 0 COMMENT '在线状态',
    power_status TINYINT DEFAULT 0 COMMENT '通电状态',
    meter_status VARCHAR(20) DEFAULT '正常' COMMENT '仪表状态',
    
    -- 通信信息
    comm_address VARCHAR(100) COMMENT '通信地址',
    last_comm_time TIMESTAMP NULL COMMENT '最后通信时间',
    
    -- 系统信息
    remark TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    FOREIGN KEY (house_id) REFERENCES house(id),
    FOREIGN KEY (building_id) REFERENCES building(id),
    FOREIGN KEY (config_id) REFERENCES meter_config(id),
    
    INDEX idx_community_meter (community_id, category_name),
    INDEX idx_category_status (category_name, meter_status),
    INDEX idx_online_status (online_status)
);
```

### 17. 抄表记录表

```sql
CREATE TABLE meter_reading (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    meter_id BIGINT NOT NULL COMMENT '仪表ID',
    
    -- 读数信息
    previous_reading DECIMAL(12,2) NOT NULL COMMENT '上次读数',
    current_reading DECIMAL(12,2) NOT NULL COMMENT '本次读数',
    usage_amount DECIMAL(12,2) NOT NULL COMMENT '用量',
    unit VARCHAR(20) COMMENT '计量单位',
    
    -- 时间信息
    reading_date DATE NOT NULL COMMENT '抄表日期',
    reading_time TIMESTAMP NOT NULL COMMENT '抄表时间',
    
    -- 抄表人信息
    reader_id BIGINT NOT NULL COMMENT '抄表人ID',
    reader_name VARCHAR(50) COMMENT '抄表人姓名',
    
    -- 分类信息
    category_name VARCHAR(50) COMMENT '分类名称',
    
    -- 状态信息
    reading_type VARCHAR(20) DEFAULT '手动' COMMENT '抄表类型',
    reading_status VARCHAR(20) DEFAULT '正常' COMMENT '抄表状态',
    
    -- 异常信息
    abnormal_reason VARCHAR(200) COMMENT '异常原因',
    processed TINYINT DEFAULT 0 COMMENT '是否已处理',
    
    -- 图片信息
    reading_image VARCHAR(500) COMMENT '抄表图片',
    
    -- 系统信息
    remark VARCHAR(200) COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (meter_id) REFERENCES meter_info(id),
    FOREIGN KEY (reader_id) REFERENCES staff(id),
    
    INDEX idx_meter_reading (meter_id, reading_date),
    INDEX idx_reading_date (reading_date),
    INDEX idx_reader (reader_id)
);
```

### 18. 员工扩展信息表

```sql
CREATE TABLE staff_extension (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    staff_id BIGINT NOT NULL COMMENT '员工ID',
    extension_key VARCHAR(100) NOT NULL COMMENT '扩展字段键',
    extension_value TEXT COMMENT '扩展字段值',
    value_type VARCHAR(20) DEFAULT 'string' COMMENT '值类型',
    sort_order INT DEFAULT 0 COMMENT '排序',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE,
    UNIQUE KEY uk_staff_key (staff_id, extension_key),
    INDEX idx_extension_key (extension_key)
);
```

### 19.智能问答知识库表 (smart_qa_knowledge)
```sql
CREATE TABLE smart_qa_knowledge (
id BIGINT PRIMARY KEY AUTO_INCREMENT,

    -- 分类信息
    category VARCHAR(100) NOT NULL COMMENT '文档分类',
    
    -- 文档信息
    title VARCHAR(200) NOT NULL COMMENT '文档标题',
    description TEXT COMMENT '文档描述/摘要',
    file_path VARCHAR(500) NOT NULL COMMENT '文档存储路径',
    file_name VARCHAR(255) NOT NULL COMMENT '文档文件名',
    file_type VARCHAR(50) COMMENT '文档类型(pdf/doc/txt等)',
    file_size BIGINT COMMENT '文档大小(字节)',
    
    -- 关键词和标签
    tags VARCHAR(300) COMMENT '标签，逗号分隔',
    
    -- 排序和状态
    sort_order INT DEFAULT 0 COMMENT '排序',
    status VARCHAR(20) DEFAULT '启用' COMMENT '状态:启用/禁用',
    
    -- 统计信息
    view_count INT DEFAULT 0 COMMENT '查看次数',
    download_count INT DEFAULT 0 COMMENT '下载次数',
    
    -- 系统信息
    created_by BIGINT COMMENT '上传人ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (created_by) REFERENCES staff(id),
    
    -- 索引
    INDEX idx_category (category, status),
    INDEX idx_title (title),
    INDEX idx_tags (tags(100)),
    INDEX idx_file_type (file_type),
    INDEX idx_status (status)
);
```

### 20.系统管理员表 (system_admin)
```sql
CREATE TABLE system_admin (
id BIGINT PRIMARY KEY AUTO_INCREMENT,

    -- 登录信息
    username VARCHAR(50) UNIQUE NOT NULL COMMENT '登录用户名',
    password VARCHAR(100) NOT NULL COMMENT '登录密码',
    email VARCHAR(100) UNIQUE NOT NULL COMMENT '邮箱',
    phone VARCHAR(20) UNIQUE COMMENT '手机号',
    
    -- 个人信息
    real_name VARCHAR(50) NOT NULL COMMENT '真实姓名',
    nickname VARCHAR(50) COMMENT '昵称',
    avatar VARCHAR(500) COMMENT '头像URL',
    gender VARCHAR(10) DEFAULT '保密' COMMENT '性别:男/女/保密',
    birth_date DATE COMMENT '出生日期',
    id_card VARCHAR(18) COMMENT '身份证号',
    
    -- 角色权限
    role_type VARCHAR(20) NOT NULL COMMENT '角色类型:super_admin/admin/operator',
    permissions TEXT COMMENT '权限列表(JSON格式)',
    department VARCHAR(100) COMMENT '所属部门',
    position VARCHAR(100) COMMENT '职位',
    
    -- 联系信息
    wechat VARCHAR(100) COMMENT '微信号',
    qq VARCHAR(20) COMMENT 'QQ号',
    address VARCHAR(200) COMMENT '联系地址',
    emergency_contact VARCHAR(50) COMMENT '紧急联系人',
    emergency_phone VARCHAR(20) COMMENT '紧急联系电话',
    
    -- 登录安全
    last_login_time TIMESTAMP NULL COMMENT '最后登录时间',
    last_login_ip VARCHAR(50) COMMENT '最后登录IP',
    login_count INT DEFAULT 0 COMMENT '登录次数',
    failed_login_count INT DEFAULT 0 COMMENT '连续登录失败次数',
    account_locked TINYINT DEFAULT 0 COMMENT '账号是否锁定',
    lock_until_time TIMESTAMP NULL COMMENT '锁定截止时间',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '正常' COMMENT '状态:正常/禁用/锁定',
    email_verified TINYINT DEFAULT 0 COMMENT '邮箱是否验证',
    phone_verified TINYINT DEFAULT 0 COMMENT '手机是否验证',
    
    -- 系统信息
    created_by BIGINT COMMENT '创建人ID(自关联)',
    remark TEXT COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- 索引
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_phone (phone),
    INDEX idx_role_type (role_type),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at)
);
```

### 21.管理员操作日志表 (admin_operation_log)
```sql
CREATE TABLE admin_operation_log (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
admin_id BIGINT NOT NULL COMMENT '管理员ID',
operation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
operation_module VARCHAR(100) NOT NULL COMMENT '操作模块',
operation_type VARCHAR(50) NOT NULL COMMENT '操作类型',
operation_description VARCHAR(500) NOT NULL COMMENT '操作描述',
request_url VARCHAR(500) COMMENT '请求URL',
request_method VARCHAR(10) COMMENT '请求方法',
request_ip VARCHAR(50) COMMENT '请求IP',
request_params TEXT COMMENT '请求参数',
response_code INT COMMENT '响应代码',
operation_status VARCHAR(20) DEFAULT '成功' COMMENT '操作状态',

    INDEX idx_admin_operation (admin_id, operation_time),
    INDEX idx_operation_time (operation_time),
    INDEX idx_operation_module (operation_module),
    INDEX idx_operation_type (operation_type)
);
```

### 22. 社区公告表 (community_notice)
```sql

CREATE TABLE community_notice (
id BIGINT PRIMARY KEY AUTO_INCREMENT,

    -- 关联信息
    community_id BIGINT NOT NULL COMMENT '所属社区ID',
    created_by BIGINT NOT NULL COMMENT '发布人ID(关联system_admin)',
    
    -- 公告基本信息
    title VARCHAR(200) NOT NULL COMMENT '公告标题',
    content TEXT NOT NULL COMMENT '公告内容',
    notice_type VARCHAR(50) NOT NULL COMMENT '公告类型:社区公告/活动公告/紧急通知/温馨提示',
    
    -- 活动信息（如果是活动公告）
    activity_date DATE COMMENT '活动日期',
    activity_time VARCHAR(50) COMMENT '活动时间',
    activity_location VARCHAR(200) COMMENT '活动地点',
    activity_organizer VARCHAR(100) COMMENT '活动组织者',
    activity_contact VARCHAR(50) COMMENT '活动联系人',
    activity_contact_phone VARCHAR(20) COMMENT '活动联系电话',
    
    -- 目标受众
    target_audience VARCHAR(100) DEFAULT '全体业主' COMMENT '目标受众',
    target_buildings VARCHAR(500) COMMENT '目标楼栋(JSON数组)',
    target_owner_types VARCHAR(100) COMMENT '目标业主类型',
    
    -- 时间控制
    publish_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    start_time TIMESTAMP NOT NULL COMMENT '生效时间',
    end_time TIMESTAMP NOT NULL COMMENT '失效时间',
    is_urgent TINYINT DEFAULT 0 COMMENT '是否紧急',
    
    -- 展示设置
    is_top TINYINT DEFAULT 0 COMMENT '是否置顶',
    top_end_time TIMESTAMP COMMENT '置顶结束时间',
    read_count INT DEFAULT 0 COMMENT '阅读次数',
    
    -- 附件信息
    attachments TEXT COMMENT '附件信息(JSON格式)',
    
    -- 状态信息
    status VARCHAR(20) DEFAULT '已发布' COMMENT '状态:草稿/已发布/已撤回/已过期',
    approval_status VARCHAR(20) DEFAULT '已审核' COMMENT '审核状态',
    approved_by BIGINT COMMENT '审核人ID',
    approved_at TIMESTAMP NULL COMMENT '审核时间',
    
    -- 系统信息
    remark VARCHAR(500) COMMENT '备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    FOREIGN KEY (created_by) REFERENCES system_admin(id),
    FOREIGN KEY (approved_by) REFERENCES system_admin(id),
    
    INDEX idx_community_notice (community_id, status, publish_time),
    INDEX idx_notice_type (notice_type),
    INDEX idx_publish_time (publish_time),
    INDEX idx_time_range (start_time, end_time),
    INDEX idx_is_top (is_top)
);
```

### 23.业主问题表 (owner_issue)
```sql
CREATE TABLE owner_issue (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- 关联信息
    community_id BIGINT NOT NULL COMMENT '所属社区ID',
    owner_id BIGINT NOT NULL COMMENT '业主ID',
    house_id BIGINT COMMENT '关联房屋ID',
    
    -- 问题基本信息
    issue_title VARCHAR(200) NOT NULL COMMENT '问题标题',
    issue_content TEXT NOT NULL COMMENT '问题详情描述',
    issue_type VARCHAR(50) NOT NULL COMMENT '问题类型:报修服务/投诉建议/咨询服务/其他',
    sub_type VARCHAR(100) COMMENT '问题子类型',
    
    -- 位置信息
    location_type VARCHAR(50) COMMENT '位置类型:室内/公共区域/停车场/其他',
    specific_location VARCHAR(200) COMMENT '具体位置',
    
    -- 联系信息
    contact_name VARCHAR(50) COMMENT '联系人姓名',
    contact_phone VARCHAR(20) NOT NULL COMMENT '联系电话',
    best_contact_time VARCHAR(100) COMMENT '最佳联系时间',
    
    -- 紧急程度
    urgency_level VARCHAR(20) DEFAULT '一般' COMMENT '紧急程度:紧急/高/一般/低',
    expected_resolve_time TIMESTAMP COMMENT '期望解决时间',
    
    -- 分配信息
    assigned_staff_id BIGINT COMMENT '指派处理人员ID',
    assigned_department_id BIGINT COMMENT '指派部门ID',
    assigned_time TIMESTAMP NULL COMMENT '指派时间',
    assigned_remark VARCHAR(500) COMMENT '指派备注',
    
    -- 处理信息
    processor_staff_id BIGINT COMMENT '实际处理人员ID',
    process_plan TEXT COMMENT '处理方案',
    process_result TEXT COMMENT '处理结果',
    process_start_time TIMESTAMP NULL COMMENT '处理开始时间',
    process_end_time TIMESTAMP NULL COMMENT '处理完成时间',
    actual_hours DECIMAL(6,2) COMMENT '实际耗时(小时)',
    
    -- 费用信息
    has_cost TINYINT DEFAULT 0 COMMENT '是否产生费用',
    material_cost DECIMAL(10,2) DEFAULT 0 COMMENT '材料费用',
    labor_cost DECIMAL(10,2) DEFAULT 0 COMMENT '人工费用',
    total_cost DECIMAL(10,2) DEFAULT 0 COMMENT '总费用',
    cost_payment_status VARCHAR(20) DEFAULT '未支付' COMMENT '费用支付状态',
    
    -- 状态跟踪
    issue_status VARCHAR(20) DEFAULT '待处理' COMMENT '问题状态',
    work_status VARCHAR(20) DEFAULT '未分配' COMMENT '工单状态',
    satisfaction_level INT COMMENT '满意度评分(1-5分)',
    satisfaction_feedback TEXT COMMENT '满意度反馈',
    
    -- 图片信息
    issue_images TEXT COMMENT '问题图片(JSON数组)',
    process_images TEXT COMMENT '处理过程图片(JSON数组)',
    result_images TEXT COMMENT '处理结果图片(JSON数组)',
    
    -- 时间信息
    reported_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '上报时间',
    response_time TIMESTAMP NULL COMMENT '首次响应时间',
    estimated_complete_time TIMESTAMP NULL COMMENT '预计完成时间',
    actual_complete_time TIMESTAMP NULL COMMENT '实际完成时间',
    
    -- 关闭信息
    closed_by BIGINT COMMENT '关闭人ID',
    closed_time TIMESTAMP NULL COMMENT '关闭时间',
    close_reason VARCHAR(200) COMMENT '关闭原因',
    
    -- 评价信息
    is_evaluated TINYINT DEFAULT 0 COMMENT '是否已评价',
    evaluation_time TIMESTAMP NULL COMMENT '评价时间',
    evaluation_content TEXT COMMENT '评价内容',
    
    -- 系统信息
    internal_remark TEXT COMMENT '内部备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (community_id) REFERENCES community_info(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (house_id) REFERENCES house(id),
    FOREIGN KEY (assigned_staff_id) REFERENCES staff(id),
    FOREIGN KEY (assigned_department_id) REFERENCES department(id),
    FOREIGN KEY (processor_staff_id) REFERENCES staff(id),
    FOREIGN KEY (closed_by) REFERENCES staff(id),
    
    INDEX idx_owner_issue (owner_id, reported_time),
    INDEX idx_community_issue (community_id, issue_status),
    INDEX idx_issue_type (issue_type),
    INDEX idx_issue_status (issue_status),
    INDEX idx_work_status (work_status),
    INDEX idx_assigned_staff (assigned_staff_id),
    INDEX idx_reported_time (reported_time)
);
```

### 24.问题跟进记录表 (issue_follow_up)
```sql
CREATE TABLE issue_follow_up (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    issue_id BIGINT NOT NULL COMMENT '问题ID',
    
    -- 跟进信息
    follow_up_type VARCHAR(50) NOT NULL COMMENT '跟进类型:业主补充/处理进展/状态变更/费用确认/满意度评价',
    follow_up_content TEXT NOT NULL COMMENT '跟进内容',
    
    -- 操作人信息
    operator_type VARCHAR(20) NOT NULL COMMENT '操作人类型:owner/staff/system',
    operator_id BIGINT COMMENT '操作人ID',
    operator_name VARCHAR(50) NOT NULL COMMENT '操作人姓名',
    
    -- 附件信息
    attachments TEXT COMMENT '附件信息(JSON格式)',
    
    -- 系统信息
    internal_note TEXT COMMENT '内部备注',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (issue_id) REFERENCES owner_issue(id) ON DELETE CASCADE,
    
    INDEX idx_issue_follow (issue_id, created_at),
    INDEX idx_operator (operator_type, operator_id)
);
```

## 插入测试数据

### 1. 插入小区信息
```sql
INSERT INTO community_info (community_name, community_code, province, city, district, area_code, detail_address, total_buildings, total_households, total_area, building_area, parking_area, green_area, public_area, property_company, contact_phone, built_year, occupancy_rate, status) VALUES
('阳光花园小区', 'YG-001', '陕西省', '西安市', '雁塔区', '610113', '长安南路123号', 8, 1200, 120000.00, 80000.00, 15000.00, 20000.00, 10000.00, '西安阳光物业服务有限公司', '029-88888888', 2015, 95.50, '正常'),
('绿城百合小区', 'LC-002', '陕西省', '西安市', '莲湖区', '610104', '大庆路456号', 6, 800, 90000.00, 65000.00, 12000.00, 15000.00, 8000.00, '绿城物业服务有限公司', '029-66666666', 2018, 92.30, '正常');
```

### 2. 插入部门信息
```sql
INSERT INTO department (department_name, parent_id, department_code, department_level, sort_order, status, description) VALUES
('物业管理部', 0, 'D001', 1, 1, '启用', '负责小区日常物业管理'),
('工程维修部', 0, 'D002', 1, 2, '启用', '负责小区设施设备维护'),
('安全管理部', 0, 'D003', 1, 3, '启用', '负责小区安全保卫工作'),
('客户服务部', 0, 'D004', 1, 4, '启用', '负责业主客户服务'),
('财务部', 0, 'D005', 1, 5, '启用', '负责小区财务管理');
```

### 3. 插入角色信息
```sql
INSERT INTO role (role_name, role_code, role_type, description, member_count, status, permissions) VALUES
('物业经理', 'R001', 'manager', '负责整个小区的管理工作', 1, '启用', '{"community_manage":true,"staff_manage":true,"finance_manage":true}'),
('客服主管', 'R002', 'supervisor', '负责客户服务管理工作', 1, '启用', '{"customer_service":true,"complaint_handle":true}'),
('维修工程师', 'R003', 'staff', '负责小区设施设备维修', 5, '启用', '{"repair_manage":true,"equipment_inspect":true}'),
('安保人员', 'R004', 'staff', '负责小区安全保卫工作', 8, '启用', '{"security_patrol":true,"access_control":true}'),
('客服专员', 'R005', 'staff', '负责业主日常服务', 4, '启用', '{"customer_service":true,"fee_collection":true}');
```

### 4. 插入员工信息
```sql
INSERT INTO staff (username, password, name, phone, id_card, work_no, gender, department_id, role_id, position, job_title, hire_date, work_status, is_manager, account_status, remark) VALUES
('manager001', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '张三', '13800000001', '610113198501011234', 'EMP001', '男', 1, 1, '物业经理', '中级工程师', '2015-03-15', '在职', 1, '正常', '小区物业经理'),
('service001', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '李四', '13800000002', '610113198802022345', 'EMP002', '女', 4, 2, '客服主管', '助理工程师', '2018-06-20', '在职', 0, '正常', '客服部主管'),
('repair001', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '王五', '13800000003', '610113199003033456', 'EMP003', '男', 2, 3, '维修工程师', '技术员', '2020-04-10', '在职', 0, '正常', '水电维修工程师'),
('security001', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '赵六', '13800000004', '610113198704044567', 'EMP004', '男', 3, 4, '安保队长', '保安员', '2019-08-25', '在职', 0, '正常', '安保部门负责人');
```

### 5. 插入楼栋信息
```sql
INSERT INTO building (community_id, building_no, building_name, building_type, building_structure, building_purpose, total_floors, underground_floors, total_households, building_area, usable_area, public_area, built_date, has_elevator, elevator_count, status) VALUES
(1, 'A1', 'A1栋', '住宅', '钢筋混凝土', '住宅', 18, 2, 120, 8000.00, 6500.00, 1500.00, '2015-10-01', 1, 2, '正常'),
(1, 'B2', 'B2栋', '住宅', '钢筋混凝土', '住宅', 24, 2, 144, 12000.00, 9600.00, 2400.00, '2015-12-01', 1, 3, '正常'),
(2, 'C1', 'C1栋', '住宅', '钢筋混凝土', '住宅', 20, 1, 100, 7500.00, 6000.00, 1500.00, '2018-05-01', 1, 2, '正常');
```

### 6. 插入房屋信息
```sql
INSERT INTO house (community_id, building_id, room_no, full_room_no, house_code, building_area, usable_area, shared_area, house_type, house_layout, house_orientation, house_status) VALUES
(1, 1, '101', 'A1-1-101', 'HOUSE-A1-101', 85.50, 68.40, 17.10, '住宅', '三室一厅', '南', '已售'),
(1, 1, '102', 'A1-1-102', 'HOUSE-A1-102', 85.50, 68.40, 17.10, '住宅', '三室一厅', '南', '已售'),
(1, 1, '201', 'A1-2-201', 'HOUSE-A1-201', 110.00, 88.00, 22.00, '住宅', '三室两厅', '北', '已售'),
(1, 2, '1001', 'B2-1-1001', 'HOUSE-B2-1001', 140.00, 112.00, 28.00, '住宅', '四室两厅', '南北通透', '已售'),
(2, 3, '501', 'C1-5-501', 'HOUSE-C1-501', 95.00, 76.00, 19.00, '住宅', '三室一厅', '东', '已售');
```

### 7. 插入业主信息
```sql
INSERT INTO owner (username, password, name, phone, id_card, gender, owner_type, current_address, status) VALUES
('owner001', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '刘先生', '13900000001', '610113198001015678', '男', '业主', '陕西省西安市长安南路123号阳光花园A1-1-101', '正常'),
('owner002', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '陈女士', '13900000002', '610113198202026789', '女', '业主', '陕西省西安市长安南路123号阳光花园A1-1-102', '正常'),
('owner003', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '杨先生', '13900000003', '610113197803037890', '男', '业主', '陕西省西安市长安南路123号阳光花园A1-2-201', '正常');
```

### 8. 插入业主房屋关联信息
```sql
INSERT INTO house_owner (house_id, owner_id, relationship, is_primary, start_date, status) VALUES
(1, 1, '业主', 1, '2016-01-15', '正常'),
(2, 2, '业主', 1, '2016-02-20', '正常'),
(3, 3, '业主', 1, '2016-03-10', '正常');
```

### 9. 插入停车场信息
```sql
INSERT INTO parking_lot (community_id, lot_name, lot_code, total_spaces, fixed_spaces, temp_spaces, status) VALUES
(1, '地下停车场', 'PARK-001', 200, 120, 80, '启用'),
(1, '地面停车场', 'PARK-002', 50, 30, 20, '启用'),
(2, '地下停车场', 'PARK-003', 150, 100, 50, '启用');
```

### 10. 插入车辆信息
```sql
INSERT INTO vehicle (owner_id, plate_number, vehicle_type, brand, model, color, status) VALUES
(1, '陕A12345', '小型车', '丰田', '卡罗拉', '白色', '正常'),
(2, '陕A67890', '小型车', '本田', '雅阁', '黑色', '正常'),
(3, '陕B11111', '小型车', '大众', '帕萨特', '银色', '正常');
```

### 11. 插入车位信息
```sql
INSERT INTO parking_space (parking_lot_id, space_no, full_space_no, space_type, space_status, owner_id, vehicle_id) VALUES
(1, 'B001', '地下-B001', '固定', '已租', 1, 1),
(1, 'B002', '地下-B002', '固定', '已租', 2, 2),
(1, 'B003', '地下-B003', '固定', '已租', 3, 3);
```

### 12. 插入仪表配置信息
```sql
INSERT INTO meter_config (category_name, meter_type, unit, charge_standard, unit_price, status) VALUES
('电表', '单相电表', 'kWh', '按月收费', 0.55, '启用'),
('水表', '机械水表', '吨', '按月收费', 3.5, '启用'),
('燃气表', '智能燃气表', '立方米', '按月收费', 2.8, '启用');
```

### 13. 插入仪表信息
```sql
INSERT INTO meter_info (community_id, house_id, config_id, meter_name, category_name, meter_type, meter_code, initial_reading, current_reading, meter_status) VALUES
(1, 1, 1, '电表001', '电表', '单相电表', 'ELE-001', 0.00, 1200.50, '正常'),
(1, 1, 2, '水表001', '水表', '机械水表', 'WAT-001', 0.00, 240.80, '正常'),
(1, 2, 1, '电表002', '电表', '单相电表', 'ELE-002', 0.00, 980.30, '正常');
```

### 14. 插入系统管理员信息
```sql
INSERT INTO system_admin (username, password, email, phone, real_name, role_type, status) VALUES
('admin', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', 'admin@community.com', '13888888888', '超级管理员', 'super_admin', '正常'),
('operator', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', 'operator@community.com', '13999999999', '操作员', 'operator', '正常');
```

### 15. 插入社区公告信息
```sql
INSERT INTO community_notice (community_id, created_by, title, content, notice_type, start_time, end_time, status) VALUES
(1, 1, '停水通知', '因供水管道检修，本小区将于2023年10月15日8:00-17:00停水，请各位业主提前做好储水准备。', '紧急通知', NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), '已发布'),
(1, 1, '业主大会通知', '兹定于2023年10月20日14:00在小区会所召开业主大会，请各位业主准时参加。', '社区公告', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), '已发布');
```

### 16. 插入门禁设备信息
```sql
INSERT INTO access_control_device (community_id, building_id, device_name, device_type, device_code, install_location, device_status) VALUES
(1, 1, 'A1栋入口门禁', '人脸识别', 'AC-A1-001', 'A1栋一楼入口', '启用'),
(1, 2, 'B2栋入口门禁', '刷卡识别', 'AC-B2-001', 'B2栋一楼入口', '启用'),
(1, NULL, '小区大门门禁', '人脸识别+刷卡', 'AC-MAIN-001', '小区正门', '启用');
```

### 17. 插入门禁记录信息
```sql
INSERT INTO access_control_record (person_id, person_type, person_name, person_phone, device_id, community_id, access_type, access_method, access_time, verify_result) VALUES
(1, 'owner', '刘先生', '13900000001', 1, 1, 'entry', '人脸识别', DATE_ADD(NOW(), INTERVAL -1 HOUR), '成功'),
(2, 'owner', '陈女士', '13900000002', 1, 1, 'entry', '人脸识别', DATE_ADD(NOW(), INTERVAL -30 MINUTE), '成功'),
(3, 'owner', '杨先生', '13900000003', 3, 1, 'entry', '刷卡识别', DATE_ADD(NOW(), INTERVAL -15 MINUTE), '成功');
```

### 18. 插入业主问题信息
```sql
INSERT INTO owner_issue (community_id, owner_id, house_id, issue_title, issue_content, issue_type, contact_name, contact_phone, issue_status, work_status) VALUES
(1, 1, 1, '水管漏水', '厨房水龙头下方水管漏水，需要紧急维修', '报修服务', '刘先生', '13900000001', '待处理', '未分配'),
(1, 2, 2, '电梯故障', 'B2栋2号电梯无法正常使用', '报修服务', '陈女士', '13900000002', '待处理', '未分配');
```

### 19. 插入问题跟进记录信息
```sql
INSERT INTO issue_follow_up (issue_id, follow_up_type, follow_up_content, operator_type, operator_id, operator_name) VALUES
(1, '处理进展', '已安排维修人员上门检查', 'staff', 3, '王五'),
(2, '状态变更', '已指派给电梯维保单位', 'staff', 3, '王五');
```

### 20. 插入停车记录信息
```sql
INSERT INTO parking_record (vehicle_id, plate_number, vehicle_type, owner_id, owner_name, owner_phone, parking_lot_id, entry_time, payment_status) VALUES
(1, '陕A12345', '小型车', 1, '刘先生', '13900000001', 1, DATE_ADD(NOW(), INTERVAL -2 HOUR), '未支付'),
(2, '陕A67890', '小型车', 2, '陈女士', '13900000002', 1, DATE_ADD(NOW(), INTERVAL -1 HOUR), '未支付');
```

### 21. 插入员工扩展信息
```sql
INSERT INTO staff_extension (staff_id, extension_key, extension_value, value_type) VALUES
(1, '学历', '本科', 'string'),
(1, '紧急联系人', '李太太', 'string'),
(2, '学历', '大专', 'string'),
(3, '技能证书', '电工证', 'string');
```

### 22. 插入抄表记录信息
```sql
INSERT INTO meter_reading (meter_id, previous_reading, current_reading, usage_amount, unit, reading_date, reading_time, reader_id, reader_name, category_name) VALUES
(1, 1100.00, 1200.50, 100.50, 'kWh', CURDATE(), NOW(), 3, '王五', '电表'),
(2, 220.00, 240.80, 20.80, '吨', CURDATE(), NOW(), 3, '王五', '水表');
```

### 23. 插入智能问答知识库信息
```sql
INSERT INTO smart_qa_knowledge (category, title, description, file_path, file_name, file_type, status, created_by) VALUES
('物业服务', '物业服务标准', '小区物业服务标准说明', '/files/property_service_standard.pdf', '物业服务标准.pdf', 'pdf', '启用', 1),
('安全管理', '消防安全指南', '小区消防安全相关指南', '/files/fire_safety_guide.pdf', '消防安全指南.pdf', 'pdf', '启用', 1);
```

### 24. 插入管理员操作日志信息
```sql
INSERT INTO admin_operation_log (admin_id, operation_module, operation_type, operation_description, request_url, request_method, request_ip, response_code, operation_status) VALUES
(1, '业主管理', '新增', '新增业主信息', '/api/owner', 'POST', '127.0.0.1', 200, '成功'),
(1, '公告管理', '发布', '发布停水通知', '/api/notice', 'POST', '127.0.0.1', 200, '成功');
```

