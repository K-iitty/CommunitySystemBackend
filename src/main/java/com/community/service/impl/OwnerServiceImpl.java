package com.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.common.service.OssService;
import com.community.dao.OwnerDao;
import com.community.domain.entity.Owner;
import com.community.service.OwnerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OwnerServiceImpl extends ServiceImpl<OwnerDao, Owner> implements OwnerService {

    @Autowired
    private OwnerDao ownerDao;
    
    @Autowired
    private OssService ossService;
    
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
    
    @Override
    public long countAll() {
        return this.count();
    }

    public boolean updateAccessControlPhotos(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "owner/access_control/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            Owner owner = this.getById(id);
            if (owner != null) {
                // 这里可以将URL列表转换为JSON字符串存储
                // 简化处理，直接用逗号分隔
                String photos = String.join(",", photoUrls);
                owner.setAccessControlPhotos(photos);
                return this.updateById(owner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getAccessControlPhotos(Long id) {
        if (id == null) {
            return null;
        }

        Owner owner = this.getById(id);
        return owner != null ? owner.getAccessControlPhotos() : null;
    }

    public boolean updateIdCardPhotos(Long id, MultipartFile[] files) {
        if (id == null || files == null || files.length == 0) {
            return false;
        }

        try {
            List<String> photoUrls = new ArrayList<>();
            // 限制最多2张身份证照片
            int count = Math.min(files.length, 2);
            for (int i = 0; i < count; i++) {
                MultipartFile file = files[i];
                if (!file.isEmpty()) {
                    // 保存文件到OSS并获取URL
                    String url = ossService.uploadFile(file, "owner/id_card/");
                    photoUrls.add(url);
                }
            }

            // 更新数据库
            Owner owner = this.getById(id);
            if (owner != null) {
                String photos = String.join(",", photoUrls);
                owner.setIdCardPhotos(photos);
                return this.updateById(owner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getIdCardPhotos(Long id) {
        if (id == null) {
            return null;
        }

        Owner owner = this.getById(id);
        return owner != null ? owner.getIdCardPhotos() : null;
    }

    private String saveFile(MultipartFile file, String subPath) throws IOException {
        // 使用OSS服务保存文件并返回URL
        return ossService.uploadFile(file, subPath);
    }
}