package com.study.asyncdocumentprocessor.infrastructure.out.persistence.adapter;

import com.study.asyncdocumentprocessor.domain.port.MinIOPort;
import com.study.asyncdocumentprocessor.infrastructure.config.S3Properties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinIOAdapter implements MinIOPort {

    private final S3Client s3Client;
    private final S3Properties props;

    @Override
    public void uploadStream(String fileName, InputStream inputStream, Long size, String contentType) {
        try {
            log.info("Uploading file stream: {} | Size: {} bytes", fileName, size);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(props.getBucket())
                    .key(fileName)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .contentType(contentType)
                    .contentLength(size)
                    .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(inputStream, size)
            );

            log.info("File uploaded successfully: {}", fileName);

        } catch (Exception e) {
            log.error("Error uploading file to MinIO: {}", fileName, e);
            throw new RuntimeException("Erro ao enviar arquivo para MinIO", e);
        }
    }

    @Override
    public InputStream getImage(String bucketName, String objectName) {
        return s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectName)
                        .build()
        );
    }
}