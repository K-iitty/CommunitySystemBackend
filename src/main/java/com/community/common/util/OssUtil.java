package com.community.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Component
public class OssUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(OssUtil.class);
    
    @Autowired
    private OSS ossClient;
    
    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    
    /**
     * 上传文件到OSS
     * @param file 文件
     * @param folder 文件夹路径，例如 "images/" 或 "documents/"
     * @return 文件在OSS中的URL
     */
    public String uploadFile(MultipartFile file, String folder) {
        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 生成唯一文件名
            String fileName = folder + UUID.randomUUID() + "_" + originalFilename;
            
            // 上传文件
            PutObjectResult result = ossClient.putObject(bucketName, fileName, file.getInputStream());
            
            // 构造文件访问URL
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            logger.info("文件上传成功: {}", url);
            return url;
        } catch (OSSException oe) {
            logger.error("OSS错误: {}", oe.getMessage());
            logger.error("错误代码: {}", oe.getErrorCode());
            logger.error("请求ID: {}", oe.getRequestId());
            logger.error("主机ID: {}", oe.getHostId());
            throw new RuntimeException("文件上传失败: " + oe.getMessage(), oe);
        } catch (IOException e) {
            logger.error("IO错误: {}", e.getMessage());
            throw new RuntimeException("文件上传失败", e);
        } catch (Exception e) {
            logger.error("未知错误: {}", e.getMessage());
            throw new RuntimeException("文件上传失败", e);
        }
    }
    
    /**
     * 上传文件流到OSS
     * @param inputStream 文件流
     * @param fileName 文件名
     * @param folder 文件夹路径
     * @return 文件在OSS中的URL
     */
    public String uploadFile(InputStream inputStream, String fileName, String folder) {
        try {
            // 生成唯一文件名
            String uniqueFileName = folder + UUID.randomUUID() + "_" + fileName;
            
            // 上传文件
            PutObjectResult result = ossClient.putObject(bucketName, uniqueFileName, inputStream);
            
            // 构造文件访问URL
            String url = "https://" + bucketName + "." + endpoint + "/" + uniqueFileName;
            logger.info("文件上传成功: {}", url);
            return url;
        } catch (OSSException oe) {
            logger.error("OSS错误: {}", oe.getMessage());
            logger.error("错误代码: {}", oe.getErrorCode());
            logger.error("请求ID: {}", oe.getRequestId());
            logger.error("主机ID: {}", oe.getHostId());
            throw new RuntimeException("文件上传失败: " + oe.getMessage(), oe);
        } catch (Exception e) {
            logger.error("文件上传异常: {}", e.getMessage());
            throw new RuntimeException("文件上传失败", e);
        }
    }
    
    /**
     * 删除OSS中的文件
     * @param fileName 文件名
     */
    public void deleteFile(String fileName) {
        try {
            ossClient.deleteObject(bucketName, fileName);
            logger.info("文件删除成功: {}", fileName);
        } catch (OSSException oe) {
            logger.error("OSS错误: {}", oe.getMessage());
            logger.error("错误代码: {}", oe.getErrorCode());
            logger.error("请求ID: {}", oe.getRequestId());
            logger.error("主机ID: {}", oe.getHostId());
            throw new RuntimeException("文件删除失败: " + oe.getMessage(), oe);
        } catch (Exception e) {
            logger.error("文件删除异常: {}", e.getMessage());
            throw new RuntimeException("文件删除失败", e);
        }
    }
    
    /**
     * 生成文件的预签名URL，用于临时访问私有文件
     * @param fileName 文件名
     * @param expiration 过期时间（毫秒）
     * @return 预签名URL
     */
    public String generatePresignedUrl(String fileName, long expiration) {
        try {
            // 设置URL过期时间
            Date expirationDate = new Date(System.currentTimeMillis() + expiration);
            // 生成以GET方法访问对象的签名URL
            URL url = ossClient.generatePresignedUrl(bucketName, fileName, expirationDate);
            return url.toString();
        } catch (Exception e) {
            logger.error("生成预签名URL异常: {}", e.getMessage());
            throw new RuntimeException("生成预签名URL失败", e);
        }
    }
}