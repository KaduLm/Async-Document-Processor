package com.study.asyncdocumentprocessor.infrastructure.in.web.dto;

import java.time.LocalDateTime;

public record DocumentResponseDTO(String path, String contentType, String status, String extractedText, LocalDateTime createdAt) {
}
