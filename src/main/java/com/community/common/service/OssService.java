package com.community.common.service;

import com.community.common.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OssService {
    
    @Autowired
    private OssUtil ossUtil;
    
    /**
     * 上传文件到OSS
     * @param file 文件
     * @param folder 文件夹路径，例如 "images/" 或 "documents/"
     * @return 文件在OSS中的URL
     */
    public String uploadFile(MultipartFile file, String folder) {
        return ossUtil.uploadFile(file, folder);
    }
    
    /**
     * 删除OSS中的文件
     * @param fileUrl 文件的完整URL
     */
    public void deleteFile(String fileUrl) {
        // 从URL中提取文件名
        String fileName = extractFileNameFromUrl(fileUrl);
        if (fileName != null) {
            ossUtil.deleteFile(fileName);
        }
    }
    
    /**
     * 从URL中提取文件名
     * @param fileUrl 文件的完整URL
     * @return 文件名
     */
    private String extractFileNameFromUrl(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return null;
        }
        
        // 例如：https://your-bucket-name.oss-cn-hangzhou.aliyuncs.com/images/uuid_filename.jpg
        int lastSlashIndex = fileUrl.lastIndexOf("/");
        if (lastSlashIndex != -1 && lastSlashIndex < fileUrl.length() - 1) {
            return fileUrl.substring(lastSlashIndex + 1);
        }
        
        return null;
    }
}