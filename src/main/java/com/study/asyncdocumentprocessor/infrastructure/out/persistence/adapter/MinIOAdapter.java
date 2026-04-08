package com.study.asyncdocumentprocessor.infrastructure.out.persistence.adapter;

import com.study.asyncdocumentprocessor.domain.port.MinIOPort;
import com.study.asyncdocumentprocessor.infrastructure.config.S3Properties;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class MinIOAdapter implements MinIOPort {

    private final S3Client s3Client;
    private final S3Properties props;


    @Override
    public void upload(String fileName, InputStream inputStream, String contentType) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(props.getBucket())
                    .key(fileName)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(inputStream, inputStream.available())
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo para MinIO", e);
        }
    }

    @Override
    public InputStream getImage(String bucketName, String objectName){
        return s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectName)
                        .build()
        );
    }
}
