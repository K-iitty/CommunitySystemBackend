# 社区服务系统 (Community System) 后端服务

一个基于Spring Boot 3.3.4的现代化、功能完整的社区信息管理系统后端服务。系统采用模块化架构设计，为社区管理提供了包括住户管理、房屋管理、车辆管理、门禁管理、停车位管理、水电气表管理、物业通知、员工管理等完整解决方案。

## 📋 目录

- [项目概述](#项目概述)
- [技术栈](#技术栈)
- [项目架构](#项目架构)
- [快速开始](#快速开始)
- [环境要求](#环境要求)
- [配置说明](#配置说明)
- [功能模块](#功能模块)
- [API文档](#api文档)
- [数据库设计](#数据库设计)
- [项目特点](#项目特点)
- [开发规范](#开发规范)
- [部署说明](#部署说明)
- [常见问题](#常见问题)
- [贡献指南](#贡献指南)

## 项目概述

社区服务系统是一个针对智慧社区、物业公司运营管理的完整后端服务，集成了现代化的技术方案和最佳实践。系统支持多角色权限管理、集中数据管理、实时数据处理等功能，为社区管理部门提供高效的工作支撑。

### 核心功能

- ✅ **住户管理**：住户信息维护、业主认证、权限管理
- ✅ **房屋管理**：房屋信息管理、户型档案、房屋状态跟踪
- ✅ **车辆管理**：车辆信息管理、驾照认证、违规记录
- ✅ **停车管理**：停车位管理、停车位预订、停车记录查询
- ✅ **门禁管理**：门禁设备管理、门禁记录统计、访问控制
- ✅ **表计管理**：水电气表配置、表计读数管理、缴费记录
- ✅ **员工管理**：员工信息、部门管理、员工权限配置
- ✅ **通知公告**：社区公告发布、通知管理、历史查询
- ✅ **智能问答**：知识库管理、AI辅助答疑（基于LangChain4j）
- ✅ **业主报事**：问题报告管理、跟进处理、完成统计
- ✅ **操作审计**：管理员操作日志、行为审计、数据追踪

## 技术栈

### 核心框架

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.3.4 | 核心应用框架 |
| JDK | 17 | Java开发工具包 |
| Maven | 3.6+ | 项目构建管理 |

### 数据库与缓存

| 技术 | 版本 | 说明 |
|------|------|------|
| MySQL | 8.0 | 关系型数据库 |
| Redis | Latest | 缓存中间件 |
| MyBatis Plus | 3.5.6 | ORM框架 |
| HikariCP | Latest | 高性能数据库连接池 |

### 安全与认证

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Security | 3.3.4 | 安全框架 |
| JWT (jjwt) | 0.11.5 | Token认证 |

### API与文档

| 技术 | 版本 | 说明 |
|------|------|------|
| SpringDoc OpenAPI | Latest | OpenAPI 3.0规范支持 |
| Knife4j | 4.5.0 | API文档增强工具 |

### 其他工具

| 技术 | 版本 | 说明 |
|------|------|------|
| Lombok | 1.18.30 | 代码简化工具 |
| Apache Commons Lang | 3.12.0 | 工具类库 |
| Aliyun OSS SDK | 3.17.4 | 对象存储服务 |
| LangChain4j | 1.4.0 | AI集成框架 |
| Spring WebFlux | 3.3.4 | 响应式编程支持 |
| Spring AOP | 3.3.4 | 面向切面编程 |

## 项目架构

### 目录结构

```
CommunitySystem-Backend/
├── docs/                              # 文档目录
│   ├── QUICK_FIX_GUIDE.md            # 快速修复指南
│   ├── sql.md                        # 数据库SQL脚本
│   ├── 完整API接口文档.md             # API详细文档
│   ├── 车辆接口修复说明.md            # 车辆功能说明
│   └── 多表查询接口.md               # 复杂查询指南
├── src/main/java/com/community/      # Java源代码
│   ├── common/                       # 公共模块
│   │   ├── config/                   # 配置类
│   │   │   ├── CustomUserDetailsService.java    # 用户详情服务
│   │   │   ├── JwtAuthenticationFilter.java     # JWT认证过滤器
│   │   │   ├── OssConfig.java                   # OSS配置
│   │   │   ├── WebMvcConfig.java                # Web配置
│   │   │   ├── Knife4jConfig.java               # API文档配置
│   │   │   ├── MybatisPlusConfig.java           # ORM配置
│   │   │   ├── SecurityConfig.java              # 安全配置
│   │   │   └── GlobalExceptionHandler.java      # 全局异常处理
│   │   ├── util/                     # 工具类
│   │   │   ├── JwtUtil.java          # JWT工具
│   │   │   └── OssUtil.java          # OSS工具
│   │   ├── service/                  # 服务类
│   │   │   └── OssService.java       # OSS业务服务
│   │   ├── exception/                # 异常类
│   │   │   └── BusinessException.java # 业务异常
│   │   ├── validator/                # 验证工具
│   │   │   └── ValidatorUtil.java    # 参数验证
│   │   └── Result.java               # 统一响应格式
│   ├── domain/                       # 领域模型
│   │   ├── entity/                   # 数据库实体
│   │   │   ├── Owner.java            # 业主实体
│   │   │   ├── Building.java         # 楼栋实体
│   │   │   ├── House.java            # 房屋实体
│   │   │   ├── Vehicle.java          # 车辆实体
│   │   │   ├── Staff.java            # 员工实体
│   │   │   ├── Department.java       # 部门实体
│   │   │   ├── ParkingSpace.java     # 停车位实体
│   │   │   ├── ParkingRecord.java    # 停车记录实体
│   │   │   ├── AccessControlDevice.java    # 门禁设备实体
│   │   │   ├── AccessControlRecord.java    # 门禁记录实体
│   │   │   ├── MeterInfo.java        # 表计信息实体
│   │   │   ├── MeterReading.java     # 表计读数实体
│   │   │   ├── CommunityNotice.java  # 社区公告实体
│   │   │   ├── OwnerIssue.java       # 业主报事实体
│   │   │   ├── IssueFollowUp.java    # 报事跟进实体
│   │   │   ├── SmartQaKnowledge.java # 知识库实体
│   │   │   ├── SystemAdmin.java      # 管理员实体
│   │   │   ├── CommunityInfo.java    # 社区信息实体
│   │   │   └── ... 更多实体
│   │   └── vo/                       # 数据传输对象(VO)
│   │       └── 对应entity的VO类
│   ├── dao/                          # 数据访问层
│   │   ├── OwnerDao.java
│   │   ├── BuildingDao.java
│   │   ├── HouseDao.java
│   │   ├── VehicleDao.java
│   │   ├── StaffDao.java
│   │   ├── ParkingSpaceDao.java
│   │   ├── ParkingRecordDao.java
│   │   ├── AccessControlDeviceDao.java
│   │   ├── AccessControlRecordDao.java
│   │   ├── MeterInfoDao.java
│   │   ├── MeterReadingDao.java
│   │   ├── CommunityNoticeDao.java
│   │   ├── OwnerIssueDao.java
│   │   ├── IssueFollowUpDao.java
│   │   ├── SmartQaKnowledgeDao.java
│   │   ├── SystemAdminDao.java
│   │   └── ... 更多数据访问对象
│   ├── service/                      # 业务逻辑层
│   │   ├── OwnerService.java         # 业主服务接口
│   │   ├── impl/                     # 服务实现
│   │   │   ├── OwnerServiceImpl.java
│   │   │   ├── BuildingServiceImpl.java
│   │   │   ├── HouseServiceImpl.java
│   │   │   ├── VehicleServiceImpl.java
│   │   │   ├── StaffServiceImpl.java
│   │   │   ├── ParkingServiceImpl.java
│   │   │   └── ... 更多实现类
│   │   ├── ComplexBusinessService.java # 复杂业务服务
│   │   └── ... 其他服务
│   └── web/                          # Web层
│       ├── App.java                  # 应用启动类
│       ├── config/                   # Web配置
│       │   ├── Knife4jConfig.java
│       │   ├── SecurityConfig.java
│       │   ├── MybatisPlusConfig.java
│       │   ├── GlobalExceptionHandler.java
│       │   └── AdminOperationLogAspect.java
│       └── controller/               # 控制层
│           ├── OwnerController.java        # 业主管理接口
│           ├── BuildingController.java     # 楼栋管理接口
│           ├── HouseController.java        # 房屋管理接口
│           ├── VehicleController.java      # 车辆管理接口
│           ├── StaffController.java        # 员工管理接口
│           ├── DepartmentController.java   # 部门管理接口
│           ├── ParkingLotController.java   # 停车场管理接口
│           ├── ParkingSpaceController.java # 停车位管理接口
│           ├── ParkingRecordController.java # 停车记录接口
│           ├── AccessControlDeviceController.java  # 门禁设备接口
│           ├── AccessControlRecordController.java  # 门禁记录接口
│           ├── MeterConfigController.java  # 表计配置接口
│           ├── MeterInfoController.java    # 表计信息接口
│           ├── MeterReadingController.java # 表计读数接口
│           ├── CommunityNoticeController.java     # 公告接口
│           ├── OwnerIssueController.java   # 报事接口
│           ├── IssueFollowUpController.java # 跟进接口
│           ├── SmartQaKnowledgeController.java    # 知识库接口
│           ├── SystemAdminController.java  # 管理员接口
│           ├── SystemAdminLoginController.java # 登录接口
│           ├── RoleController.java         # 角色管理接口
│           ├── CommunityInfoController.java # 社区信息接口
│           ├── HouseOwnerController.java   # 房屋业主接口
│           ├── ComplexBusinessController.java # 复杂业务接口
│           ├── AdminOperationLogController.java # 操作日志接口
│           └── *SearchController.java      # 搜索接口组
├── src/main/resources/
│   └── application.yml              # 应用配置文件
├── pom.xml                          # Maven依赖配置
└── README.md                        # 项目说明文档
```

### 模块划分

系统采用分层架构，各层职责清晰：

```
┌─────────────────────────────────────────┐
│   Web Layer (Controller)                 │
│   - HTTP请求处理                        │
│   - 参数验证                            │
│   - 响应格式化                          │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│   Service Layer (Business Logic)        │
│   - 业务逻辑处理                        │
│   - 事务管理                            │
│   - 数据验证                            │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│   DAO Layer (Data Access)               │
│   - 数据库操作                          │
│   - 查询条件构建                        │
│   - 数据映射                            │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│   Domain Layer (Data Model)             │
│   - 实体类定义                          │
│   - VO对象定义                          │
└─────────────────────────────────────────┘
```

## 快速开始

### 环境要求

- **JDK**: 17或更高版本
- **MySQL**: 8.0或更高版本
- **Redis**: 最新稳定版本
- **Maven**: 3.6.0或更高版本

### 安装步骤

#### 1. 克隆项目

```bash
git clone <your-repository-url>
cd CommunitySystem-Backend
```

#### 2. 创建数据库

```bash
# 使用MySQL客户端连接到MySQL服务器
mysql -u root -p

# 创建数据库
CREATE DATABASE community CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 3. 初始化数据库表

查看 `docs/sql.md` 文件，执行其中的SQL脚本初始化所有数据库表：

```bash
mysql -u root -p community < docs/sql.md
```

#### 4. 配置环境变量

修改 `src/main/resources/application.yml` 中的数据库和服务配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/community?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
    
  redis:
    host: localhost
    port: 6379
    password: your_redis_password
    
jwt:
  secret: your_jwt_secret_key
  expiration: 86400000  # 24小时
```

#### 5. 配置阿里云OSS（可选）

如果需要使用文件上传功能，需要配置阿里云OSS：

```yaml
aliyun:
  oss:
    endpoint: oss-cn-beijing.aliyuncs.com
    bucket-name: your-bucket-name

# 设置环境变量
export OSS_ACCESS_KEY_ID=your_access_key_id
export OSS_ACCESS_KEY_SECRET=your_access_key_secret
```

#### 6. 构建项目

```bash
# 清理并构建
mvn clean install

# 或者跳过测试快速构建
mvn clean install -DskipTests
```

#### 7. 运行项目

方式一：使用Maven插件运行
```bash
mvn spring-boot:run
```

方式二：运行启动类
```bash
# 直接在IDE中运行 com.community.web.App 类
```

方式三：打包后运行
```bash
mvn package -DskipTests
java -jar target/CommunitySystem-1.0.0.jar
```

#### 8. 验证服务

启动成功后，访问以下地址验证：

```
# API文档
http://localhost:8080/doc.html (Knife4j增强文档)
http://localhost:8080/swagger-ui.html (Swagger UI)

# 健康检查
http://localhost:8080/actuator/health
```

## 配置说明

### 数据库配置

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/community?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: sheep14
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      pool-name: HikariCP
      minimum-idle: 5              # 最小空闲连接数
      maximum-pool-size: 20        # 最大连接数
      connection-timeout: 30000    # 连接超时时间(毫秒)
      idle-timeout: 600000         # 空闲超时时间(毫秒)
      max-lifetime: 1800000        # 连接最大存活时间(毫秒)
```

### Redis配置

```yaml
spring:
  data:
  redis:
    host: localhost
    port: 6379
    password: sheep14
      database: 0                  # 数据库编号
      # timeout: 2000ms           # 连接超时时间
      # lettuce连接池配置（可选）
```

### JWT配置

```yaml
jwt:
  secret: communitySystemSecretKeyForJWTTokenGenerationAndValidation
  expiration: 86400000  # Token过期时间（毫秒），默认24小时
```

### 阿里云OSS配置

```yaml
aliyun:
  oss:
    endpoint: oss-cn-beijing.aliyuncs.com  # 根据Bucket所在地域修改
    bucket-name: smart-community-system     # Bucket名称

# 环境变量配置（Linux/Mac）
export OSS_ACCESS_KEY_ID=xxx
export OSS_ACCESS_KEY_SECRET=xxx

# Windows命令行配置
set OSS_ACCESS_KEY_ID=xxx
set OSS_ACCESS_KEY_SECRET=xxx
```

### 日志配置

```yaml
logging:
  level:
    com.community: debug                               # 应用日志级别
    com.community.common.config.JwtAuthenticationFilter: debug  # JWT过滤器日志
    org.springframework.security: debug                # Spring Security日志
    com.baomidou.mybatisplus: debug                   # MyBatis Plus日志
```

### API文档配置

```yaml
springdoc:
  swagger-ui:
    path: /swagger-ui.html
    tags-sorter: alpha              # 按字母排序标签
    operations-sorter: alpha        # 按字母排序操作
    
  api-docs:
    path: /v3/api-docs
    
  group-configs:
    - group: community-system
      paths-to-match: /**
      packages-to-scan: com.community.web.controller

knife4j:
  enable: true                  # 启用Knife4j
  production: false             # 生产环境设为true
  basic:
    enable: false               # 关闭BasicAuth
  setting:
    language: zh_cn             # 设置语言为中文
```

## 功能模块

### 1. 系统管理 (System Management)

**相关接口**: `SystemAdminController`, `SystemAdminLoginController`, `RoleController`, `DepartmentController`

- 管理员账户管理
- 角色权限管理
- 部门管理
- 操作日志审计

### 2. 社区管理 (Community Management)

**相关接口**: `CommunityInfoController`

- 社区基本信息维护
- 社区配置管理

### 3. 楼栋与房屋管理 (Building & House Management)

**相关接口**: `BuildingController`, `HouseController`, `HouseOwnerController`

- 楼栋信息管理
- 房屋信息管理
- 房屋-业主关系管理
- 房屋户型档案

### 4. 业主管理 (Owner Management)

**相关接口**: `OwnerController`

- 业主信息管理
- 业主认证（身份证验证）
- 业主权限配置
- 业主统计查询

**主要接口**:
```
GET    /api/owner/count              # 获取业主总数
GET    /api/owner/list               # 分页查询业主
POST   /api/owner/create             # 创建业主
PUT    /api/owner/update             # 修改业主信息
GET    /api/owner/{id}               # 获取业主详情
DELETE /api/owner/{id}               # 删除业主
```

### 5. 员工管理 (Staff Management)

**相关接口**: `StaffController`, `StaffExtensionController`

- 员工信息管理
- 员工扩展信息（证件照、身份证等）
- 员工权限配置

### 6. 车辆管理 (Vehicle Management)

**相关接口**: `VehicleController`

- 车辆信息管理
- 驾照认证
- 车型管理
- 车辆违规记录

### 7. 停车场管理 (Parking Management)

**相关接口**: `ParkingLotController`, `ParkingSpaceController`, `ParkingRecordController`

- 停车场信息管理
- 停车位管理
- 停车位预订
- 停车记录查询
- 停车费用统计

### 8. 门禁管理 (Access Control Management)

**相关接口**: `AccessControlDeviceController`, `AccessControlRecordController`

- 门禁设备管理
- 门禁权限配置
- 门禁记录查询
- 出入统计分析

### 9. 表计管理 (Meter Management)

**相关接口**: `MeterConfigController`, `MeterInfoController`, `MeterReadingController`

- 表计配置管理
- 表计信息维护
- 表计读数管理
- 缴费记录查询
- 用量统计

### 10. 社区公告管理 (Community Notice Management)

**相关接口**: `CommunityNoticeController`

- 公告发布
- 公告编辑与删除
- 公告分类管理
- 公告历史查询

### 11. 业主报事管理 (Owner Issue Management)

**相关接口**: `OwnerIssueController`, `IssueFollowUpController`

- 业主报事录入
- 报事跟进处理
- 完成情况统计
- 报事分类与优先级

### 12. 智能问答 (Smart QA Management)

**相关接口**: `SmartQaKnowledgeController`

- 知识库管理
- AI知识库训练
- 问答推荐
- 知识库文件上传

**特性**:
- 集成LangChain4j框架
- 支持OpenAI API调用
- 知识库向量化存储
- 智能问答推荐

### 13. 复杂业务 (Complex Business)

**相关接口**: `ComplexBusinessController`

- 多表关联查询
- 复杂统计分析
- 数据导出
- 跨模块业务处理

### 14. 搜索功能 (Search Features)

**相关接口**:
- `ParkingRecordSearchController` - 停车记录搜索
- `AdminOperationLogSearchController` - 操作日志搜索
- `SystemAdminSearchController` - 管理员搜索
- `OwnerIssueSearchController` - 报事搜索
- `IssueFollowUpSearchController` - 跟进搜索
- `CommunityNoticeSearchController` - 公告搜索

## API文档

### 文档访问

项目集成了SpringDoc OpenAPI和Knife4j，启动项目后可通过以下地址访问：

#### Knife4j增强文档（推荐）
```
http://localhost:8080/doc.html
```
- 提供更友好的UI界面
- 支持更多的文档功能
- 支持代码生成
- 支持导出离线文档

#### Swagger UI
```
http://localhost:8080/swagger-ui.html
```
- 标准Swagger UI界面
- 基础API测试功能

#### OpenAPI JSON规范
```
http://localhost:8080/v3/api-docs
```
- JSON格式的OpenAPI规范
- 可用于第三方工具解析

### API测试

在API文档界面可以直接测试接口：

1. 点击要测试的接口
2. 点击"Try it out"按钮
3. 输入参数值
4. 点击"Execute"按钮
5. 查看响应结果

### JWT认证测试

对于需要认证的接口：

1. 先调用登录接口获取Token
   ```
   POST /api/systemAdmin/login
   Body: { "username": "admin", "password": "password" }
   ```

2. 复制返回的Token值

3. 在Knife4j或Swagger UI的右上角点击"Authorize"按钮

4. 输入Token值（格式: `Bearer <token>`）

5. 后续所有请求都会自动携带认证信息

### 文档导出

在Knife4j界面可以导出离线HTML文档：

1. 点击左侧"文档管理"
2. 选择"离线文档"
3. 选择导出格式（HTML/Markdown/Word）
4. 点击下载

## 数据库设计

### 核心表结构

```sql
-- 管理员用户表
CREATE TABLE system_admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    real_name VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100),
    enabled BOOLEAN DEFAULT TRUE,
    role_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 社区基本信息
CREATE TABLE community_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    intro TEXT,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 楼栋信息
CREATE TABLE building (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    number VARCHAR(50),
    floors INT,
    houses INT,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 房屋信息
CREATE TABLE house (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    building_id BIGINT NOT NULL,
    unit_number VARCHAR(50),
    floor INT,
    number VARCHAR(50),
    area DECIMAL(10,2),
    layout VARCHAR(50),
    image_url VARCHAR(255),
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (building_id) REFERENCES building(id)
);

-- 业主信息
CREATE TABLE owner (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    id_card VARCHAR(20),
    id_card_url VARCHAR(255),
    gender VARCHAR(10),
    birth_date DATE,
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 房屋-业主关系
CREATE TABLE house_owner (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    house_id BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    is_owner BOOLEAN,
    move_in_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (house_id) REFERENCES house(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id)
);

-- 车辆信息
CREATE TABLE vehicle (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT NOT NULL,
    license_plate VARCHAR(20) UNIQUE,
    vehicle_type VARCHAR(50),
    brand VARCHAR(50),
    model VARCHAR(50),
    color VARCHAR(50),
    vin VARCHAR(100),
    driver_license VARCHAR(50),
    driver_license_url VARCHAR(255),
    vehicle_image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES owner(id)
);

-- 停车位信息
CREATE TABLE parking_space (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    parking_lot_id BIGINT NOT NULL,
    space_number VARCHAR(50) UNIQUE,
    location VARCHAR(100),
    status VARCHAR(20),
    owner_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 停车记录
CREATE TABLE parking_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id BIGINT NOT NULL,
    parking_space_id BIGINT,
    entry_time TIMESTAMP,
    exit_time TIMESTAMP,
    fee DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);

-- 门禁设备
CREATE TABLE access_control_device (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    location VARCHAR(100),
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 门禁记录
CREATE TABLE access_control_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    device_id BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    access_time TIMESTAMP,
    direction VARCHAR(20),
    success BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (device_id) REFERENCES access_control_device(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id)
);

-- 表计信息
CREATE TABLE meter_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    house_id BIGINT NOT NULL,
    meter_type VARCHAR(50),
    meter_number VARCHAR(50) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (house_id) REFERENCES house(id)
);

-- 表计读数
CREATE TABLE meter_reading (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    meter_info_id BIGINT NOT NULL,
    reading_value DECIMAL(10,2),
    reading_date DATE,
    amount DECIMAL(10,2),
    paid BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (meter_info_id) REFERENCES meter_info(id)
);

-- 社区公告
CREATE TABLE community_notice (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    image_url VARCHAR(255),
    category VARCHAR(50),
    admin_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES system_admin(id)
);

-- 业主报事
CREATE TABLE owner_issue (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT NOT NULL,
    title VARCHAR(100),
    description TEXT,
    image_url VARCHAR(255),
    category VARCHAR(50),
    priority VARCHAR(20),
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES owner(id)
);

-- 报事跟进
CREATE TABLE issue_follow_up (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    issue_id BIGINT NOT NULL,
    admin_id BIGINT,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (issue_id) REFERENCES owner_issue(id),
    FOREIGN KEY (admin_id) REFERENCES system_admin(id)
);

-- 管理员操作日志
CREATE TABLE admin_operation_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    admin_id BIGINT NOT NULL,
    operation_type VARCHAR(50),
    resource_type VARCHAR(50),
    resource_id BIGINT,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES system_admin(id)
);
```

更详细的SQL脚本请参考 `docs/sql.md` 文件。

## 项目特点

### 🏗️ 架构优势

- **模块化设计**: 清晰的模块划分，易于维护和扩展
- **分层架构**: Controller → Service → DAO → Domain的标准分层
- **MyBatis Plus**: 强大的ORM框架，简化数据库操作
- **HikariCP**: 高性能数据库连接池

### 🔐 安全特性

- **JWT认证**: 基于JSON Web Token的无状态认证
- **Spring Security**: 企业级安全框架
- **权限管理**: 基于角色的访问控制（RBAC）
- **操作日志**: 完整的管理员操作审计追踪

### 📡 接口特性

- **RESTful API**: 标准的REST接口设计
- **Knife4j增强**: 友好的API文档界面
- **OpenAPI 3.0**: 标准的API规范支持
- **在线测试**: 可直接在文档中测试API

### 💾 数据特性

- **Redis缓存**: 提高系统性能
- **事务管理**: 确保数据一致性
- **数据验证**: 参数合法性验证
- **审计日志**: 记录所有重要操作

### 📁 文件存储

- **阿里云OSS**: 云端文件存储
- **安全访问**: 使用访问密钥认证
- **URL管理**: 数据库存储文件URL
- **多种文件类型**: 支持各类业务文件

### 🤖 智能功能

- **LangChain4j**: AI集成框架
- **OpenAI API**: 接入大模型能力
- **知识库管理**: 可扩展的知识管理系统
- **智能问答**: AI辅助的问答推荐

### 📊 业务功能

- **完整覆盖**: 覆盖社区管理的主要业务
- **数据分析**: 支持复杂的数据查询和分析
- **跨模块**: 支持多个模块的联合操作
- **统计报表**: 丰富的数据统计功能

## 开发规范

### 命名规范

- **包名**: 使用小写英文，如 `com.community.web.controller`
- **类名**: 使用大驼峰，如 `OwnerController`
- **方法名**: 使用小驼峰，如 `getOwnerById`
- **常量**: 使用大写下划线，如 `MAX_PAGE_SIZE`
- **变量**: 使用小驼峰，如 `ownerName`

### 代码风格

- **缩进**: 使用4个空格（IDE自动配置）
- **行长**: 尽量不超过120个字符
- **注释**: 使用清晰的英文或中文注释
- **文档**: 类和公开方法添加JavaDoc

### Controller层规范

```java
@RestController
@RequestMapping("/api/owner")
@Tag(name = "业主管理", description = "业主信息相关接口")
public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;
    
    @GetMapping("/{id}")
    @Operation(summary = "获取业主详情")
    public Result<Owner> getById(@PathVariable Long id) {
        return Result.ok(ownerService.getById(id));
    }
}
```

### Service层规范

```java
public interface OwnerService {
    /**
     * 获取业主详情
     */
    Owner getById(Long id);
    
    /**
     * 创建业主
     */
    Owner create(OwnerCreateRequest request);
}

@Service
public class OwnerServiceImpl implements OwnerService {
    
    @Autowired
    private OwnerDao ownerDao;
    
    @Override
    public Owner getById(Long id) {
        return ownerDao.selectById(id);
    }
}
```

### DAO层规范

```java
@Repository
public interface OwnerDao extends BaseMapper<Owner> {
    /**
     * 按身份证号查询业主
     */
    Owner selectByIdCard(String idCard);
}
```

### 响应格式规范

所有接口统一返回Result格式：

```java
// 成功响应
Result.ok(data)
Result.ok().put("count", total)

// 失败响应
Result.error("错误信息")
Result.error(ErrorCode.BUSINESS_ERROR, "具体错误描述")
```

### 异常处理规范

```java
// 业务异常
throw new BusinessException("业主不存在");

// 在GlobalExceptionHandler中统一处理
@ExceptionHandler(BusinessException.class)
public Result<Void> handleBusinessException(BusinessException e) {
    return Result.error(e.getMessage());
}
```

### API文档规范

使用Knife4j和SpringDoc注解编写文档：

```java
@GetMapping("/{id}")
@Operation(summary = "获取业主详情", description = "根据业主ID获取其详细信息")
@ApiOperationSupport(author = "开发团队")
@SecurityRequirement(name = "Authorization")
public Result<Owner> getById(
    @PathVariable @Parameter(description = "业主ID") Long id
) {
    return Result.ok(ownerService.getById(id));
}
```

## 部署说明

### 本地开发环境

1. **启动依赖服务**
   ```bash
   # 启动MySQL
   mysql.server start
   
   # 启动Redis
   redis-server
   ```

2. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

3. **访问应用**
   ```
   http://localhost:8080/doc.html
   ```

### Docker部署

1. **构建镜像**
   ```bash
   mvn clean package -DskipTests
   docker build -t community-system:1.0.0 .
   ```

2. **运行容器**
   ```bash
   docker run -d \
     --name community-system \
     -p 8080:8080 \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/community \
     -e SPRING_DATASOURCE_USERNAME=root \
     -e SPRING_DATASOURCE_PASSWORD=password \
     -e SPRING_REDIS_HOST=redis \
     community-system:1.0.0
   ```

### 生产环境部署

1. **修改配置**
   ```yaml
   # application-prod.yml
   spring:
     profiles: prod
   knife4j:
     production: true
   logging:
     level: warn
   ```

2. **打包应用**
```bash
   mvn clean package -DskipTests -P prod
```

3. **运行应用**
```bash
   java -jar community-system-1.0.0.jar --spring.profiles.active=prod
   ```

4. **配置Nginx反向代理**
   ```nginx
   server {
       listen 80;
       server_name your-domain.com;
       
       location / {
           proxy_pass http://localhost:8080;
           proxy_set_header Host $host;
           proxy_set_header X-Real-IP $remote_addr;
       }
   }
   ```

## 常见问题

### Q: 启动报错"Connection refused"
**A**: 检查MySQL和Redis是否已启动，以及配置中的主机名和端口是否正确。

### Q: JWT Token验证失败
**A**: 确保：
1. Token格式正确（Bearer + Token）
2. Token未过期
3. 密钥配置一致
4. 在Authorization请求头中传递

### Q: 文件上传失败
**A**: 检查：
1. 阿里云OSS配置是否正确
2. 环境变量是否设置（OSS_ACCESS_KEY_ID等）
3. Bucket是否存在且权限正确
4. 网络连接是否正常

### Q: 数据库连接池耗尽
**A**: 调整HikariCP配置，增加maximum-pool-size值，或排查是否有未关闭的连接。

### Q: 接口超时
**A**: 检查：
1. 数据库查询是否已添加索引
2. 是否存在N+1问题
3. Redis缓存是否配置正确
4. 网络延迟是否过高

## 贡献指南

1. **Fork项目**
   ```bash
   git clone <your-fork-url>
   ```

2. **创建功能分支**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **提交更改**
   ```bash
   git add .
   git commit -m "feat: add your feature description"
   git push origin feature/your-feature-name
   ```

4. **发起Pull Request**
   - 描述你的修改内容
   - 说明你的改动的意义
   - 关联相关Issue

5. **代码审查**
   - 等待项目维护者进行代码审查
   - 根据反馈进行调整

## 联系方式

- **项目维护**: 社区管理系统开发团队
- **问题反馈**: 提交Issue或联系开发团队
- **技术支持**: 查看文档或提交讨论

## 版本历史

| 版本 | 发布日期 | 主要更新 |
|------|---------|--------|
| 1.0.0 | 2024-11 | 初始版本发布，包含所有核心功能 |

## 许可证

本项目采用 MIT 许可证。详见 LICENSE 文件。

---

**更新时间**: 2024-11-02
**项目版本**: 1.0.0
**维护团队**: 社区管理系统开发团队