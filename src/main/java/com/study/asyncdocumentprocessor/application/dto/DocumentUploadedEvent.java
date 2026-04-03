package com.study.asyncdocumentprocessor.application.dto;

import java.util.UUID;

public record DocumentUploadedEvent(
        UUID id,
        String filePath,
        String status
) {
}
