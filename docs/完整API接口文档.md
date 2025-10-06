# 社区管理系统完整API接口文档

## 1. 简介

本文档详细描述了社区管理系统的后端API接口，供前端开发人员参考和使用。

### 1.1 公共说明

#### 1.1.1 响应格式
所有接口均返回统一的JSON格式：
```json
{
  "code": 200,       // 状态码，200表示成功，其他表示失败
  "msg": "操作成功",  // 提示信息
  "data": {}         // 返回数据，根据接口不同而不同
}
```

#### 1.1.2 认证方式
除登录、注册等少数接口外，其他接口都需要在请求头中携带JWT Token：
```
Authorization: Bearer <token>
```

#### 1.1.3 分页参数
分页查询接口统一使用以下参数：
- `pageNum`: 当前页码，默认为1
- `pageSize`: 每页条数，默认为10

#### 1.1.4 状态码说明
- 200: 请求成功
- 500: 服务器内部错误

## 2. 系统管理员接口

### 2.1 管理员登录
**请求地址**: `POST /api/systemAdmin/login`  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| username | string | 是 | 用户名 |
| password | string | 是 | 密码 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "登录成功",
  "data": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "realName": "管理员",
    "roleType": "super_admin",
    "status": "正常"
  },
  "token": "eyJhbGciOiJIUzUxMiJ9.xxxxx",
  "tokenType": "Bearer"
}
```

### 2.2 获取当前登录管理员信息
**请求地址**: `GET /api/systemAdmin/info`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "realName": "管理员",
    "roleType": "super_admin",
    "status": "正常"
  }
}
```

### 2.3 管理员退出登录
**请求地址**: `POST /api/systemAdmin/logout`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "退出成功"
}
```

### 2.4 管理员注册
**请求地址**: `POST /api/systemAdmin/register`  
**请求参数**:
```json
{
  "username": "newadmin",
  "password": "123456",
  "email": "newadmin@example.com",
  "realName": "新管理员"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "注册成功"
}
```

### 2.5 分页查询管理员
**请求地址**: `GET /api/systemAdmin/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| username | string | 否 | 用户名（模糊查询） |
| realName | string | 否 | 真实姓名（模糊查询） |
| status | string | 否 | 状态 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "username": "admin",
      "email": "admin@example.com",
      "realName": "管理员",
      "roleType": "super_admin",
      "status": "正常",
      "createdAt": "2023-01-01 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 2.6 根据ID查询管理员
**请求地址**: `GET /api/systemAdmin/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "realName": "管理员",
    "roleType": "super_admin",
    "status": "正常",
    "createdAt": "2023-01-01 12:00:00"
  }
}
```

### 2.7 新增管理员
**请求地址**: `POST /api/systemAdmin`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "username": "newadmin",
  "password": "123456",
  "email": "newadmin@example.com",
  "realName": "新管理员",
  "roleType": "admin",
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 2.8 修改管理员
**请求地址**: `PUT /api/systemAdmin`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "email": "updated@example.com",
  "realName": "更新的管理员",
  "roleType": "super_admin",
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 2.9 删除管理员
**请求地址**: `DELETE /api/systemAdmin/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 3. 智能问答知识库接口

### 3.1 分页查询知识库
**请求地址**: `GET /api/smartQaKnowledge/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| category | string | 否 | 文档分类（模糊查询） |
| title | string | 否 | 文档标题（模糊查询） |
| tags | string | 否 | 标签（模糊查询） |
| status | string | 否 | 状态 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "category": "使用手册",
      "title": "系统使用手册.pdf",
      "description": "系统使用说明文档",
      "fileName": "系统使用手册.pdf",
      "fileType": "pdf",
      "fileSize": 102400,
      "tags": "使用手册,操作指南",
      "sortOrder": 0,
      "status": "启用",
      "viewCount": 10,
      "downloadCount": 5,
      "createdBy": 1,
      "createdAt": "2023-01-01 12:00:00",
      "updatedAt": "2023-01-01 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 3.2 根据ID查询知识库
**请求地址**: `GET /api/smartQaKnowledge/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "category": "使用手册",
    "title": "系统使用手册.pdf",
    "description": "系统使用说明文档",
    "fileName": "系统使用手册.pdf",
    "fileType": "pdf",
    "fileSize": 102400,
    "tags": "使用手册,操作指南",
    "sortOrder": 0,
    "status": "启用",
    "viewCount": 10,
    "downloadCount": 5,
    "createdBy": 1,
    "createdAt": "2023-01-01 12:00:00",
    "updatedAt": "2023-01-01 12:00:00"
  }
}
```

### 3.3 新增知识库
**请求地址**: `POST /api/smartQaKnowledge`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "category": "使用手册",
  "title": "系统使用手册.pdf",
  "description": "系统使用说明文档",
  "filePath": "/uploads/knowledge/abc123.pdf",
  "fileName": "系统使用手册.pdf",
  "fileType": "pdf",
  "fileSize": 102400,
  "tags": "使用手册,操作指南",
  "sortOrder": 0,
  "status": "启用",
  "createdBy": 1
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 3.4 修改知识库
**请求地址**: `PUT /api/smartQaKnowledge`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "category": "使用手册",
  "title": "系统使用手册V2.pdf",
  "description": "系统使用说明文档第二版",
  "tags": "使用手册,操作指南,V2",
  "sortOrder": 1,
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 3.5 删除知识库
**请求地址**: `DELETE /api/smartQaKnowledge/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

### 3.6 上传知识库文档
**请求地址**: `POST /api/smartQaKnowledge/upload`  
**请求头**: 需要携带JWT Token  
**请求方式**: multipart/form-data  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| file | file | 是 | 要上传的文件 |
| category | string | 否 | 文档分类 |
| title | string | 否 | 文档标题 |
| description | string | 否 | 文档描述 |
| tags | string | 否 | 标签 |
| sortOrder | int | 否 | 排序 |
| status | string | 否 | 状态，默认为"启用" |
| createdBy | long | 否 | 上传人ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "文件上传成功",
  "data": {
    "id": 1,
    "category": "默认分类",
    "title": "系统使用手册.pdf",
    "description": null,
    "filePath": "uploads/knowledge/abc123.pdf",
    "fileName": "系统使用手册.pdf",
    "fileType": "pdf",
    "fileSize": 102400,
    "tags": null,
    "sortOrder": 0,
    "status": "启用",
    "viewCount": 0,
    "downloadCount": 0,
    "createdBy": null,
    "createdAt": "2023-01-01 12:00:00",
    "updatedAt": "2023-01-01 12:00:00"
  }
}
```

## 4. 门禁设备管理接口

### 4.1 分页查询门禁设备
**请求地址**: `GET /api/accessControlDevice/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| deviceName | string | 否 | 设备名称（模糊查询） |
| deviceCode | string | 否 | 设备编码（模糊查询） |
| status | string | 否 | 状态 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "deviceName": "东门门禁",
      "deviceCode": "ACD001",
      "deviceIp": "192.168.1.100",
      "devicePort": 8080,
      "location": "东门",
      "status": "启用",
      "createdAt": "2023-01-01 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 4.2 根据ID查询门禁设备
**请求地址**: `GET /api/accessControlDevice/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "deviceName": "东门门禁",
    "deviceCode": "ACD001",
    "deviceIp": "192.168.1.100",
    "devicePort": 8080,
    "location": "东门",
    "status": "启用",
    "createdAt": "2023-01-01 12:00:00"
  }
}
```

### 4.3 新增门禁设备
**请求地址**: `POST /api/accessControlDevice`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "deviceName": "西门门禁",
  "deviceCode": "ACD002",
  "deviceIp": "192.168.1.101",
  "devicePort": 8080,
  "location": "西门",
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 4.4 修改门禁设备
**请求地址**: `PUT /api/accessControlDevice`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "deviceName": "东门门禁",
  "deviceCode": "ACD001",
  "deviceIp": "192.168.1.100",
  "devicePort": 8080,
  "location": "东门",
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 4.5 删除门禁设备
**请求地址**: `DELETE /api/accessControlDevice/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 5. 门禁记录管理接口

### 5.1 分页查询门禁记录
**请求地址**: `GET /api/accessControlRecord/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| deviceId | long | 否 | 设备ID |
| personName | string | 否 | 人员姓名（模糊查询） |
| cardNo | string | 否 | 卡号 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "deviceId": 1,
      "deviceName": "东门门禁",
      "personId": 1001,
      "personName": "张三",
      "cardNo": "C001",
      "accessTime": "2023-01-01 12:00:00",
      "accessType": "进门",
      "accessResult": "成功"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 5.2 根据ID查询门禁记录
**请求地址**: `GET /api/accessControlRecord/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "deviceId": 1,
    "deviceName": "东门门禁",
    "personId": 1001,
    "personName": "张三",
    "cardNo": "C001",
    "accessTime": "2023-01-01 12:00:00",
    "accessType": "进门",
    "accessResult": "成功"
  }
}
```

### 5.3 新增门禁记录
**请求地址**: `POST /api/accessControlRecord`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "deviceId": 1,
  "deviceName": "东门门禁",
  "personId": 1001,
  "personName": "张三",
  "cardNo": "C001",
  "accessTime": "2023-01-01 12:00:00",
  "accessType": "进门",
  "accessResult": "成功"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 5.4 修改门禁记录
**请求地址**: `PUT /api/accessControlRecord`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "deviceId": 1,
  "deviceName": "东门门禁",
  "personId": 1001,
  "personName": "张三",
  "cardNo": "C001",
  "accessTime": "2023-01-01 12:00:00",
  "accessType": "进门",
  "accessResult": "成功"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 5.5 删除门禁记录
**请求地址**: `DELETE /api/accessControlRecord/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 6. 管理员操作日志接口

### 6.1 分页查询操作日志
**请求地址**: `GET /api/adminOperationLog/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| adminId | long | 否 | 管理员ID |
| operationType | string | 否 | 操作类型 |
| operationDesc | string | 否 | 操作描述（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "adminId": 1,
      "adminName": "管理员",
      "operationType": "新增",
      "operationDesc": "新增用户信息",
      "operationTime": "2023-01-01 12:00:00",
      "ipAddress": "192.168.1.100"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 6.2 根据ID查询操作日志
**请求地址**: `GET /api/adminOperationLog/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "adminId": 1,
    "adminName": "管理员",
    "operationType": "新增",
    "operationDesc": "新增用户信息",
    "operationTime": "2023-01-01 12:00:00",
    "ipAddress": "192.168.1.100"
  }
}
```

### 6.3 新增操作日志
**请求地址**: `POST /api/adminOperationLog`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "adminId": 1,
  "adminName": "管理员",
  "operationType": "新增",
  "operationDesc": "新增用户信息",
  "operationTime": "2023-01-01 12:00:00",
  "ipAddress": "192.168.1.100"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 6.4 修改操作日志
**请求地址**: `PUT /api/adminOperationLog`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "adminId": 1,
  "adminName": "管理员",
  "operationType": "新增",
  "operationDesc": "新增用户信息",
  "operationTime": "2023-01-01 12:00:00",
  "ipAddress": "192.168.1.100"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 6.5 删除操作日志
**请求地址**: `DELETE /api/adminOperationLog/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 7. 楼栋信息管理接口

### 7.1 分页查询楼栋信息
**请求地址**: `GET /api/building/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| buildingName | string | 否 | 楼栋名称（模糊查询） |
| buildingCode | string | 否 | 楼栋编码（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "buildingName": "1号楼",
      "buildingCode": "B001",
      "floorCount": 10,
      "houseCount": 100,
      "manager": "李四",
      "managerPhone": "13800138000",
      "createdAt": "2023-01-01 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 7.2 根据ID查询楼栋信息
**请求地址**: `GET /api/building/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "buildingName": "1号楼",
    "buildingCode": "B001",
    "floorCount": 10,
    "houseCount": 100,
    "manager": "李四",
    "managerPhone": "13800138000",
    "createdAt": "2023-01-01 12:00:00"
  }
}
```

### 7.3 新增楼栋信息
**请求地址**: `POST /api/building`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "buildingName": "2号楼",
  "buildingCode": "B002",
  "floorCount": 12,
  "houseCount": 120,
  "manager": "王五",
  "managerPhone": "13800138001"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 7.4 修改楼栋信息
**请求地址**: `PUT /api/building`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "buildingName": "1号楼",
  "buildingCode": "B001",
  "floorCount": 10,
  "houseCount": 100,
  "manager": "李四",
  "managerPhone": "13800138000"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 7.5 删除楼栋信息
**请求地址**: `DELETE /api/building/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 8. 社区信息管理接口

### 8.1 分页查询社区信息
**请求地址**: `GET /api/community/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| communityName | string | 否 | 社区名称（模糊查询） |
| address | string | 否 | 地址（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "communityName": "阳光小区",
      "address": "北京市朝阳区xxx街道",
      "propertyCompany": "阳光物业公司",
      "contactPerson": "张经理",
      "contactPhone": "010-12345678",
      "houseCount": 500,
      "residentCount": 1500,
      "createdAt": "2023-01-01 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 8.2 根据ID查询社区信息
**请求地址**: `GET /api/community/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "communityName": "阳光小区",
    "address": "北京市朝阳区xxx街道",
    "propertyCompany": "阳光物业公司",
    "contactPerson": "张经理",
    "contactPhone": "010-12345678",
    "houseCount": 500,
    "residentCount": 1500,
    "createdAt": "2023-01-01 12:00:00"
  }
}
```

### 8.3 新增社区信息
**请求地址**: `POST /api/community`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "communityName": "绿洲小区",
  "address": "北京市海淀区xxx街道",
  "propertyCompany": "绿洲物业公司",
  "contactPerson": "李经理",
  "contactPhone": "010-87654321",
  "houseCount": 300,
  "residentCount": 900
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 8.4 修改社区信息
**请求地址**: `PUT /api/community`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "communityName": "阳光小区",
  "address": "北京市朝阳区xxx街道",
  "propertyCompany": "阳光物业公司",
  "contactPerson": "张经理",
  "contactPhone": "010-12345678",
  "houseCount": 500,
  "residentCount": 1500
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 8.5 删除社区信息
**请求地址**: `DELETE /api/community/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 9. 社区公告管理接口

### 9.1 分页查询社区公告
**请求地址**: `GET /api/communityNotice/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| title | string | 否 | 公告标题（模糊查询） |
| noticeType | string | 否 | 公告类型 |
| status | string | 否 | 状态 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "title": "停水通知",
      "noticeType": "通知",
      "content": "因管道维修，本周日停水一天",
      "status": "发布",
      "publishTime": "2023-01-01 12:00:00",
      "expireTime": "2023-01-02 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 9.2 根据ID查询社区公告
**请求地址**: `GET /api/communityNotice/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "title": "停水通知",
    "noticeType": "通知",
    "content": "因管道维修，本周日停水一天",
    "status": "发布",
    "publishTime": "2023-01-01 12:00:00",
    "expireTime": "2023-01-02 12:00:00"
  }
}
```

### 9.3 新增社区公告
**请求地址**: `POST /api/communityNotice`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "title": "停电通知",
  "noticeType": "通知",
  "content": "因电路检修，本周六停电半天",
  "status": "草稿",
  "publishTime": "2023-01-01 12:00:00",
  "expireTime": "2023-01-02 12:00:00"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 9.4 修改社区公告
**请求地址**: `PUT /api/communityNotice`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "title": "停水通知",
  "noticeType": "通知",
  "content": "因管道维修，本周日停水一天",
  "status": "发布",
  "publishTime": "2023-01-01 12:00:00",
  "expireTime": "2023-01-02 12:00:00"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 9.5 删除社区公告
**请求地址**: `DELETE /api/communityNotice/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 10. 部门信息管理接口

### 10.1 分页查询部门信息
**请求地址**: `GET /api/department/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| deptName | string | 否 | 部门名称（模糊查询） |
| deptCode | string | 否 | 部门编码（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "deptName": "物业管理部",
      "deptCode": "D001",
      "parentId": 0,
      "manager": "张经理",
      "managerPhone": "13800138000",
      "status": "启用",
      "sortOrder": 1
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 10.2 根据ID查询部门信息
**请求地址**: `GET /api/department/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "deptName": "物业管理部",
    "deptCode": "D001",
    "parentId": 0,
    "manager": "张经理",
    "managerPhone": "13800138000",
    "status": "启用",
    "sortOrder": 1
  }
}
```

### 10.3 新增部门信息
**请求地址**: `POST /api/department`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "deptName": "安保部",
  "deptCode": "D002",
  "parentId": 0,
  "manager": "李队长",
  "managerPhone": "13800138001",
  "status": "启用",
  "sortOrder": 2
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 10.4 修改部门信息
**请求地址**: `PUT /api/department`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "deptName": "物业管理部",
  "deptCode": "D001",
  "parentId": 0,
  "manager": "张经理",
  "managerPhone": "13800138000",
  "status": "启用",
  "sortOrder": 1
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 10.5 删除部门信息
**请求地址**: `DELETE /api/department/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 11. 房屋信息管理接口

### 11.1 分页查询房屋信息
**请求地址**: `GET /api/house/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| houseCode | string | 否 | 房屋编码（模糊查询） |
| buildingId | long | 否 | 楼栋ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "houseCode": "H001",
      "buildingId": 1,
      "buildingName": "1号楼",
      "unit": "1",
      "floor": "3",
      "room": "301",
      "houseType": "三室一厅",
      "area": 120.5,
      "status": "已入住"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 11.2 根据ID查询房屋信息
**请求地址**: `GET /api/house/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "houseCode": "H001",
    "buildingId": 1,
    "buildingName": "1号楼",
    "unit": "1",
    "floor": "3",
    "room": "301",
    "houseType": "三室一厅",
    "area": 120.5,
    "status": "已入住"
  }
}
```

### 11.3 新增房屋信息
**请求地址**: `POST /api/house`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "houseCode": "H002",
  "buildingId": 1,
  "buildingName": "1号楼",
  "unit": "1",
  "floor": "3",
  "room": "302",
  "houseType": "三室一厅",
  "area": 120.5,
  "status": "空置"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 11.4 修改房屋信息
**请求地址**: `PUT /api/house`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "houseCode": "H001",
  "buildingId": 1,
  "buildingName": "1号楼",
  "unit": "1",
  "floor": "3",
  "room": "301",
  "houseType": "三室一厅",
  "area": 120.5,
  "status": "已入住"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 11.5 删除房屋信息
**请求地址**: `DELETE /api/house/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 12. 房屋业主关联管理接口

### 12.1 分页查询房屋业主关联
**请求地址**: `GET /api/houseOwner/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| houseId | long | 否 | 房屋ID |
| ownerId | long | 否 | 业主ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "houseId": 1,
      "ownerId": 1001,
      "relationType": "产权人",
      "startDate": "2023-01-01",
      "endDate": "2099-12-31",
      "status": "有效"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 12.2 根据ID查询房屋业主关联
**请求地址**: `GET /api/houseOwner/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "houseId": 1,
    "ownerId": 1001,
    "relationType": "产权人",
    "startDate": "2023-01-01",
    "endDate": "2099-12-31",
    "status": "有效"
  }
}
```

### 12.3 新增房屋业主关联
**请求地址**: `POST /api/houseOwner`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "houseId": 1,
  "ownerId": 1001,
  "relationType": "产权人",
  "startDate": "2023-01-01",
  "endDate": "2099-12-31",
  "status": "有效"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 12.4 修改房屋业主关联
**请求地址**: `PUT /api/houseOwner`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "houseId": 1,
  "ownerId": 1001,
  "relationType": "产权人",
  "startDate": "2023-01-01",
  "endDate": "2099-12-31",
  "status": "有效"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 12.5 删除房屋业主关联
**请求地址**: `DELETE /api/houseOwner/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 13. 问题跟进管理接口

### 13.1 分页查询问题跟进
**请求地址**: `GET /api/issueFollowUp/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| issueId | long | 否 | 问题ID |
| followUpPerson | string | 否 | 跟进人（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "issueId": 1001,
      "followUpPerson": "张三",
      "followUpTime": "2023-01-01 12:00:00",
      "followUpContent": "已联系业主，安排维修师傅",
      "nextFollowUpTime": "2023-01-02 12:00:00",
      "status": "处理中"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 13.2 根据ID查询问题跟进
**请求地址**: `GET /api/issueFollowUp/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "issueId": 1001,
    "followUpPerson": "张三",
    "followUpTime": "2023-01-01 12:00:00",
    "followUpContent": "已联系业主，安排维修师傅",
    "nextFollowUpTime": "2023-01-02 12:00:00",
    "status": "处理中"
  }
}
```

### 13.3 新增问题跟进
**请求地址**: `POST /api/issueFollowUp`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "issueId": 1001,
  "followUpPerson": "张三",
  "followUpTime": "2023-01-01 12:00:00",
  "followUpContent": "已联系业主，安排维修师傅",
  "nextFollowUpTime": "2023-01-02 12:00:00",
  "status": "处理中"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 13.4 修改问题跟进
**请求地址**: `PUT /api/issueFollowUp`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "issueId": 1001,
  "followUpPerson": "张三",
  "followUpTime": "2023-01-01 12:00:00",
  "followUpContent": "已联系业主，安排维修师傅",
  "nextFollowUpTime": "2023-01-02 12:00:00",
  "status": "处理中"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 13.5 删除问题跟进
**请求地址**: `DELETE /api/issueFollowUp/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 14. 仪表配置管理接口

### 14.1 分页查询仪表配置
**请求地址**: `GET /api/meterConfig/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| meterType | string | 否 | 仪表类型 |
| meterName | string | 否 | 仪表名称（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "meterType": "水表",
      "meterName": "家用水表",
      "unit": "吨",
      "precision": 2,
      "status": "启用"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 14.2 根据ID查询仪表配置
**请求地址**: `GET /api/meterConfig/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "meterType": "水表",
    "meterName": "家用水表",
    "unit": "吨",
    "precision": 2,
    "status": "启用"
  }
}
```

### 14.3 新增仪表配置
**请求地址**: `POST /api/meterConfig`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "meterType": "电表",
  "meterName": "家用电表",
  "unit": "度",
  "precision": 2,
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 14.4 修改仪表配置
**请求地址**: `PUT /api/meterConfig`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "meterType": "水表",
  "meterName": "家用水表",
  "unit": "吨",
  "precision": 2,
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 14.5 删除仪表配置
**请求地址**: `DELETE /api/meterConfig/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 15. 仪表信息管理接口

### 15.1 分页查询仪表信息
**请求地址**: `GET /api/meterInfo/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| meterCode | string | 否 | 仪表编码（模糊查询） |
| houseId | long | 否 | 房屋ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "meterCode": "M001",
      "meterType": "水表",
      "houseId": 1001,
      "installPosition": "厨房",
      "installDate": "2023-01-01",
      "initialReading": 0.00,
      "status": "正常"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 15.2 根据ID查询仪表信息
**请求地址**: `GET /api/meterInfo/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "meterCode": "M001",
    "meterType": "水表",
    "houseId": 1001,
    "installPosition": "厨房",
    "installDate": "2023-01-01",
    "initialReading": 0.00,
    "status": "正常"
  }
}
```

### 15.3 新增仪表信息
**请求地址**: `POST /api/meterInfo`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "meterCode": "M002",
  "meterType": "电表",
  "houseId": 1001,
  "installPosition": "配电箱",
  "installDate": "2023-01-01",
  "initialReading": 0.00,
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 15.4 修改仪表信息
**请求地址**: `PUT /api/meterInfo`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "meterCode": "M001",
  "meterType": "水表",
  "houseId": 1001,
  "installPosition": "厨房",
  "installDate": "2023-01-01",
  "initialReading": 0.00,
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 15.5 删除仪表信息
**请求地址**: `DELETE /api/meterInfo/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 16. 抄表记录管理接口

### 16.1 分页查询抄表记录
**请求地址**: `GET /api/meterReading/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| meterId | long | 否 | 仪表ID |
| reader | string | 否 | 抄表人（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "meterId": 1001,
      "reading": 100.50,
      "readingDate": "2023-01-01",
      "reader": "张三",
      "remark": "正常"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 16.2 根据ID查询抄表记录
**请求地址**: `GET /api/meterReading/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "meterId": 1001,
    "reading": 100.50,
    "readingDate": "2023-01-01",
    "reader": "张三",
    "remark": "正常"
  }
}
```

### 16.3 新增抄表记录
**请求地址**: `POST /api/meterReading`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "meterId": 1001,
  "reading": 100.50,
  "readingDate": "2023-01-01",
  "reader": "张三",
  "remark": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 16.4 修改抄表记录
**请求地址**: `PUT /api/meterReading`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "meterId": 1001,
  "reading": 100.50,
  "readingDate": "2023-01-01",
  "reader": "张三",
  "remark": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 16.5 删除抄表记录
**请求地址**: `DELETE /api/meterReading/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 17. 业主信息管理接口

### 17.1 分页查询业主信息
**请求地址**: `GET /api/owner/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| ownerName | string | 否 | 业主姓名（模糊查询） |
| phone | string | 否 | 手机号 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "ownerName": "张三",
      "gender": "男",
      "phone": "13800138000",
      "idCard": "110101199001011234",
      "email": "zhangsan@example.com",
      "address": "北京市朝阳区xxx街道1号楼301",
      "status": "正常"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 17.2 根据ID查询业主信息
**请求地址**: `GET /api/owner/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "ownerName": "张三",
    "gender": "男",
    "phone": "13800138000",
    "idCard": "110101199001011234",
    "email": "zhangsan@example.com",
    "address": "北京市朝阳区xxx街道1号楼301",
    "status": "正常"
  }
}
```

### 17.3 新增业主信息
**请求地址**: `POST /api/owner`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "ownerName": "李四",
  "gender": "女",
  "phone": "13800138001",
  "idCard": "110101199001011235",
  "email": "lisi@example.com",
  "address": "北京市朝阳区xxx街道1号楼302",
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 17.4 修改业主信息
**请求地址**: `PUT /api/owner`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "ownerName": "张三",
  "gender": "男",
  "phone": "13800138000",
  "idCard": "110101199001011234",
  "email": "zhangsan@example.com",
  "address": "北京市朝阳区xxx街道1号楼301",
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 17.5 删除业主信息
**请求地址**: `DELETE /api/owner/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 18. 业主问题管理接口

### 18.1 分页查询业主问题
**请求地址**: `GET /api/ownerIssue/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| ownerId | long | 否 | 业主ID |
| issueType | string | 否 | 问题类型 |
| status | string | 否 | 状态 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "ownerId": 1001,
      "ownerName": "张三",
      "issueType": "设施维修",
      "title": "水管漏水",
      "content": "厨房水管漏水，需要维修",
      "priority": "紧急",
      "status": "处理中",
      "submitTime": "2023-01-01 12:00:00"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 18.2 根据ID查询业主问题
**请求地址**: `GET /api/ownerIssue/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "ownerId": 1001,
    "ownerName": "张三",
    "issueType": "设施维修",
    "title": "水管漏水",
    "content": "厨房水管漏水，需要维修",
    "priority": "紧急",
    "status": "处理中",
    "submitTime": "2023-01-01 12:00:00"
  }
}
```

### 18.3 新增业主问题
**请求地址**: `POST /api/ownerIssue`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "ownerId": 1001,
  "ownerName": "张三",
  "issueType": "设施维修",
  "title": "水管漏水",
  "content": "厨房水管漏水，需要维修",
  "priority": "紧急",
  "status": "待处理",
  "submitTime": "2023-01-01 12:00:00"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 18.4 修改业主问题
**请求地址**: `PUT /api/ownerIssue`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "ownerId": 1001,
  "ownerName": "张三",
  "issueType": "设施维修",
  "title": "水管漏水",
  "content": "厨房水管漏水，需要维修",
  "priority": "紧急",
  "status": "处理中",
  "submitTime": "2023-01-01 12:00:00"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 18.5 删除业主问题
**请求地址**: `DELETE /api/ownerIssue/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 19. 停车场管理接口

### 19.1 分页查询停车场
**请求地址**: `GET /api/parkingLot/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| lotName | string | 否 | 停车场名称（模糊查询） |
| lotCode | string | 否 | 停车场编码（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "lotName": "一号停车场",
      "lotCode": "PL001",
      "totalSpaces": 100,
      "availableSpaces": 80,
      "address": "小区东侧",
      "status": "启用"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 19.2 根据ID查询停车场
**请求地址**: `GET /api/parkingLot/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "lotName": "一号停车场",
    "lotCode": "PL001",
    "totalSpaces": 100,
    "availableSpaces": 80,
    "address": "小区东侧",
    "status": "启用"
  }
}
```

### 19.3 新增停车场
**请求地址**: `POST /api/parkingLot`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "lotName": "二号停车场",
  "lotCode": "PL002",
  "totalSpaces": 50,
  "availableSpaces": 50,
  "address": "小区西侧",
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 19.4 修改停车场
**请求地址**: `PUT /api/parkingLot`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "lotName": "一号停车场",
  "lotCode": "PL001",
  "totalSpaces": 100,
  "availableSpaces": 80,
  "address": "小区东侧",
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 19.5 删除停车场
**请求地址**: `DELETE /api/parkingLot/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 20. 停车记录管理接口

### 20.1 分页查询停车记录
**请求地址**: `GET /api/parkingRecord/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| spaceId | long | 否 | 车位ID |
| plateNumber | string | 否 | 车牌号 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "spaceId": 1001,
      "plateNumber": "京A12345",
      "entryTime": "2023-01-01 08:00:00",
      "exitTime": "2023-01-01 18:00:00",
      "duration": 10,
      "fee": 10.00,
      "status": "已缴费"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 20.2 根据ID查询停车记录
**请求地址**: `GET /api/parkingRecord/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "spaceId": 1001,
    "plateNumber": "京A12345",
    "entryTime": "2023-01-01 08:00:00",
    "exitTime": "2023-01-01 18:00:00",
    "duration": 10,
    "fee": 10.00,
    "status": "已缴费"
  }
}
```

### 20.3 新增停车记录
**请求地址**: `POST /api/parkingRecord`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "spaceId": 1001,
  "plateNumber": "京A12345",
  "entryTime": "2023-01-01 08:00:00",
  "exitTime": "2023-01-01 18:00:00",
  "duration": 10,
  "fee": 10.00,
  "status": "已缴费"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 20.4 修改停车记录
**请求地址**: `PUT /api/parkingRecord`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "spaceId": 1001,
  "plateNumber": "京A12345",
  "entryTime": "2023-01-01 08:00:00",
  "exitTime": "2023-01-01 18:00:00",
  "duration": 10,
  "fee": 10.00,
  "status": "已缴费"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 20.5 删除停车记录
**请求地址**: `DELETE /api/parkingRecord/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 21. 车位信息管理接口

### 21.1 分页查询车位信息
**请求地址**: `GET /api/parkingSpace/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| spaceCode | string | 否 | 车位编码（模糊查询） |
| lotId | long | 否 | 停车场ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "spaceCode": "PS001",
      "lotId": 1001,
      "lotName": "一号停车场",
      "area": "A区",
      "status": "空闲",
      "type": "普通车位"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 21.2 根据ID查询车位信息
**请求地址**: `GET /api/parkingSpace/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "spaceCode": "PS001",
    "lotId": 1001,
    "lotName": "一号停车场",
    "area": "A区",
    "status": "空闲",
    "type": "普通车位"
  }
}
```

### 21.3 新增车位信息
**请求地址**: `POST /api/parkingSpace`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "spaceCode": "PS002",
  "lotId": 1001,
  "lotName": "一号停车场",
  "area": "A区",
  "status": "空闲",
  "type": "普通车位"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 21.4 修改车位信息
**请求地址**: `PUT /api/parkingSpace`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "spaceCode": "PS001",
  "lotId": 1001,
  "lotName": "一号停车场",
  "area": "A区",
  "status": "空闲",
  "type": "普通车位"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 21.5 删除车位信息
**请求地址**: `DELETE /api/parkingSpace/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 22. 角色信息管理接口

### 22.1 分页查询角色信息
**请求地址**: `GET /api/role/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| roleName | string | 否 | 角色名称（模糊查询） |
| roleCode | string | 否 | 角色编码（模糊查询） |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "roleName": "超级管理员",
      "roleCode": "ROLE_SUPER_ADMIN",
      "description": "系统超级管理员",
      "status": "启用"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 22.2 根据ID查询角色信息
**请求地址**: `GET /api/role/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "roleName": "超级管理员",
    "roleCode": "ROLE_SUPER_ADMIN",
    "description": "系统超级管理员",
    "status": "启用"
  }
}
```

### 22.3 新增角色信息
**请求地址**: `POST /api/role`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "roleName": "普通管理员",
  "roleCode": "ROLE_ADMIN",
  "description": "系统普通管理员",
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 22.4 修改角色信息
**请求地址**: `PUT /api/role`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "roleName": "超级管理员",
  "roleCode": "ROLE_SUPER_ADMIN",
  "description": "系统超级管理员",
  "status": "启用"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 22.5 删除角色信息
**请求地址**: `DELETE /api/role/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 23. 员工信息管理接口

### 23.1 分页查询员工信息
**请求地址**: `GET /api/staff/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| staffName | string | 否 | 员工姓名（模糊查询） |
| phone | string | 否 | 手机号 |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "staffName": "张三",
      "gender": "男",
      "phone": "13800138000",
      "idCard": "110101199001011234",
      "deptId": 1001,
      "deptName": "物业管理部",
      "position": "物业经理",
      "status": "在职"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 23.2 根据ID查询员工信息
**请求地址**: `GET /api/staff/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "staffName": "张三",
    "gender": "男",
    "phone": "13800138000",
    "idCard": "110101199001011234",
    "deptId": 1001,
    "deptName": "物业管理部",
    "position": "物业经理",
    "status": "在职"
  }
}
```

### 23.3 新增员工信息
**请求地址**: `POST /api/staff`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "staffName": "李四",
  "gender": "女",
  "phone": "13800138001",
  "idCard": "110101199001011235",
  "deptId": 1001,
  "deptName": "物业管理部",
  "position": "客服专员",
  "status": "在职"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 23.4 修改员工信息
**请求地址**: `PUT /api/staff`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "staffName": "张三",
  "gender": "男",
  "phone": "13800138000",
  "idCard": "110101199001011234",
  "deptId": 1001,
  "deptName": "物业管理部",
  "position": "物业经理",
  "status": "在职"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 23.5 删除员工信息
**请求地址**: `DELETE /api/staff/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 24. 员工扩展信息管理接口

### 24.1 分页查询员工扩展信息
**请求地址**: `GET /api/staffExtension/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| staffId | long | 否 | 员工ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "staffId": 1001,
      "emergencyContact": "王五",
      "emergencyPhone": "13800138002",
      "education": "本科",
      "major": "物业管理",
      "entryDate": "2023-01-01",
      "contractStartDate": "2023-01-01",
      "contractEndDate": "2026-01-01"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 24.2 根据ID查询员工扩展信息
**请求地址**: `GET /api/staffExtension/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "staffId": 1001,
    "emergencyContact": "王五",
    "emergencyPhone": "13800138002",
    "education": "本科",
    "major": "物业管理",
    "entryDate": "2023-01-01",
    "contractStartDate": "2023-01-01",
    "contractEndDate": "2026-01-01"
  }
}
```

### 24.3 新增员工扩展信息
**请求地址**: `POST /api/staffExtension`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "staffId": 1001,
  "emergencyContact": "王五",
  "emergencyPhone": "13800138002",
  "education": "本科",
  "major": "物业管理",
  "entryDate": "2023-01-01",
  "contractStartDate": "2023-01-01",
  "contractEndDate": "2026-01-01"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 24.4 修改员工扩展信息
**请求地址**: `PUT /api/staffExtension`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "staffId": 1001,
  "emergencyContact": "王五",
  "emergencyPhone": "13800138002",
  "education": "本科",
  "major": "物业管理",
  "entryDate": "2023-01-01",
  "contractStartDate": "2023-01-01",
  "contractEndDate": "2026-01-01"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 24.5 删除员工扩展信息
**请求地址**: `DELETE /api/staffExtension/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 25. 车辆信息管理接口

### 25.1 分页查询车辆信息
**请求地址**: `GET /api/vehicle/page`  
**请求头**: 需要携带JWT Token  
**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| pageNum | int | 否 | 当前页码，默认为1 |
| pageSize | int | 否 | 每页条数，默认为10 |
| plateNumber | string | 否 | 车牌号（模糊查询） |
| ownerId | long | 否 | 业主ID |

**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "id": 1,
      "plateNumber": "京A12345",
      "ownerId": 1001,
      "ownerName": "张三",
      "vehicleBrand": "奔驰",
      "vehicleModel": "C200",
      "color": "白色",
      "status": "正常"
    }
  ],
  "total": 1,
  "pageNum": 1,
  "pageSize": 10
}
```

### 25.2 根据ID查询车辆信息
**请求地址**: `GET /api/vehicle/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "plateNumber": "京A12345",
    "ownerId": 1001,
    "ownerName": "张三",
    "vehicleBrand": "奔驰",
    "vehicleModel": "C200",
    "color": "白色",
    "status": "正常"
  }
}
```

### 25.3 新增车辆信息
**请求地址**: `POST /api/vehicle`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "plateNumber": "京A12346",
  "ownerId": 1001,
  "ownerName": "张三",
  "vehicleBrand": "宝马",
  "vehicleModel": "320",
  "color": "黑色",
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "新增成功"
}
```

### 25.4 修改车辆信息
**请求地址**: `PUT /api/vehicle`  
**请求头**: 需要携带JWT Token  
**请求参数**:
```json
{
  "id": 1,
  "plateNumber": "京A12345",
  "ownerId": 1001,
  "ownerName": "张三",
  "vehicleBrand": "奔驰",
  "vehicleModel": "C200",
  "color": "白色",
  "status": "正常"
}
```
**响应示例**:
```json
{
  "code": 200,
  "msg": "修改成功"
}
```

### 25.5 删除车辆信息
**请求地址**: `DELETE /api/vehicle/{id}`  
**请求头**: 需要携带JWT Token  
**响应示例**:
```json
{
  "code": 200,
  "msg": "删除成功"
}
```

## 26. 错误处理

接口调用可能返回以下错误信息：
```json
{
  "code": 500,
  "msg": "错误描述"
}
```

常见错误：
- 未登录或Token过期：请重新登录
- 权限不足：当前用户无权执行该操作
- 参数错误：请检查请求参数
- 数据不存在：请求的数据不存在

## 27. 注意事项

1. 所有时间格式为：`yyyy-MM-dd HH:mm:ss`
2. 文件上传大小限制为10MB
3. 支持的文件类型包括：pdf, doc, docx, txt等常见文档格式
4. 分页查询时，total表示总记录数，不是总页数
5. 删除操作为物理删除，请谨慎使用

# api文档系统

详细信息可参考API文档系统（启动后访问`http://localhost:8080/doc.html`）：

1. 门禁设备管理
2. 门禁记录管理
3. 管理员操作日志
4. 楼栋管理
5. 社区信息管理
6. 社区公告管理
7. 部门管理
8. 房屋管理
9. 房屋业主管理
10. 问题跟进管理
11. 水表电表配置管理
12. 水表电表信息管理
13. 抄表记录管理
14. 业主管理
15. 业主问题管理
16. 停车场管理
17. 停车记录管理
18. 停车位管理
19. 角色管理
20. 员工管理
21. 员工扩展信息管理
22. 车辆管理
