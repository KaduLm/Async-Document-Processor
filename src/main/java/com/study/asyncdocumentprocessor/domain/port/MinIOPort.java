package com.study.asyncdocumentprocessor.domain.port;

import java.io.InputStream;

public interface MinIOPort {
    void uploadStream(String fileName, InputStream inputStream, Long size, String contentType);
    InputStream getImage(String bucketName, String objectName);
}
