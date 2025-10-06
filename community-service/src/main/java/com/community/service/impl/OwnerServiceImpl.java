package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.dao.OwnerDao;
import com.community.domain.entity.Owner;
import com.community.service.OwnerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerDao, Owner> implements OwnerService {

    @Override
    public IPage<Owner> selectOwnerPage(IPage<Owner> page, Owner owner) {
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据用户名模糊查询
        if (StringUtils.isNotBlank(owner.getUsername())) {
            queryWrapper.like(Owner::getUsername, owner.getUsername());
        }
        
        // 根据姓名模糊查询
        if (StringUtils.isNotBlank(owner.getName())) {
            queryWrapper.like(Owner::getName, owner.getName());
        }
        
        // 根据手机号查询
        if (StringUtils.isNotBlank(owner.getPhone())) {
            queryWrapper.eq(Owner::getPhone, owner.getPhone());
        }
        
        // 根据身份证号查询
        if (StringUtils.isNotBlank(owner.getIdCard())) {
            queryWrapper.eq(Owner::getIdCard, owner.getIdCard());
        }
        
        // 根据业主类型查询
        if (StringUtils.isNotBlank(owner.getOwnerType())) {
            queryWrapper.eq(Owner::getOwnerType, owner.getOwnerType());
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(owner.getStatus())) {
            queryWrapper.eq(Owner::getStatus, owner.getStatus());
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Owner::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<Owner> selectOwnerPageByMultiple(IPage<Owner> page, String name, String phone, String ownerType, String status) {
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据姓名模糊查询
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Owner::getName, name);
        }
        
        // 根据手机号查询
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.eq(Owner::getPhone, phone);
        }
        
        // 根据业主类型查询
        if (StringUtils.isNotBlank(ownerType)) {
            queryWrapper.eq(Owner::getOwnerType, ownerType);
        }
        
        // 根据状态查询
        if (StringUtils.isNotBlank(status)) {
            queryWrapper.eq(Owner::getStatus, status);
        }
        
        // 默认按创建时间倒序排列
        queryWrapper.orderByDesc(Owner::getCreatedAt);
        
        return this.page(page, queryWrapper);
    }
}