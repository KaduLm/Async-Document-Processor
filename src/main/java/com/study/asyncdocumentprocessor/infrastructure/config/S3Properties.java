package com.study.asyncdocumentprocessor.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
public class S3Properties {

    private String url;
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
