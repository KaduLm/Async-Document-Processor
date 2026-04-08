package com.study.asyncdocumentprocessor.infrastructure.in.web.dto;

import lombok.Builder;

import java.io.InputStream;
import java.time.LocalDateTime;

@Builder
public record DocumentResponseDTO(String path, String contentType, String status, String extractedText, LocalDateTime createdAt) {
}
