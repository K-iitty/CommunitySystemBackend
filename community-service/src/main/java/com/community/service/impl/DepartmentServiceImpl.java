package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.DepartmentDao;
import com.community.domain.entity.Department;
import com.community.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {

    @Override
    public IPage<Department> selectDepartmentPage(IPage<Department> page, Department department) {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据部门名称模糊查询
        if (StringUtils.isNotBlank(department.getDepartmentName())) {
            queryWrapper.like(Department::getDepartmentName, department.getDepartmentName());
        }
        
        // 根据部门编码查询
        if (StringUtils.isNotBlank(department.getDepartmentCode())) {
            queryWrapper.eq(Department::getDepartmentCode, department.getDepartmentCode());
        }
        
        // 根据父部门ID查询
        if (department.getParentId() != null) {
            queryWrapper.eq(Department::getParentId, department.getParentId());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(department.getStatus())) {
            queryWrapper.eq(Department::getStatus, department.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Department::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}