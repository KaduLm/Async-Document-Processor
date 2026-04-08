package com.study.asyncdocumentprocessor.domain.port;

import java.io.InputStream;

public interface MinIOPort {
    void upload(String fileName, InputStream inputStream, String contentType);
    InputStream getImage(String bucketName, String objectName);
}
