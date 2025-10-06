package com.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.domain.entity.Department;

public interface DepartmentService extends IService<Department> {
    
    /**
     * 分页查询部门信息
     * @param page 分页对象
     * @param department 查询条件
     * @return 部门信息分页数据
     */
    IPage<Department> selectDepartmentPage(IPage<Department> page, Department department);
}