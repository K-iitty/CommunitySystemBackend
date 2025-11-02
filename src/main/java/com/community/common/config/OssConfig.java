package com.community.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssConfig {
    
    private String endpoint;
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
    
    @Bean
    public OSS ossClient() {
        // 创建OSSClient实例
        return new OSSClientBuilder()
                .build(endpoint, System.getenv("OSS_ACCESS_KEY_ID"), 
                      System.getenv("OSS_ACCESS_KEY_SECRET"));
    }
}