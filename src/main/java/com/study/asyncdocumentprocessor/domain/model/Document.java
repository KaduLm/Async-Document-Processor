package com.study.asyncdocumentprocessor.domain.model;

import com.study.asyncdocumentprocessor.domain.enums.ProcessingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Document {
   private UUID id;
   private String filePath;
   private ProcessingStatus status;
   private String extractedText;
   private LocalDateTime createdAt;

    public Document(UUID id, String filePath, ProcessingStatus status, String extractedText, LocalDateTime createdAt) {
        this.id = id;
        this.filePath = filePath;
        this.status = status;
        this.extractedText = extractedText;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public ProcessingStatus getStatus() {
        return status;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}


