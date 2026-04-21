package com.study.asyncdocumentprocessor.infrastructure.out.persistence.entity;

import com.study.asyncdocumentprocessor.domain.enums.ProcessingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "document", schema = "processor_document")
public class DocumentEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String filePath;

    @Enumerated(EnumType.STRING)
    private ProcessingStatus status;

    @Column(columnDefinition = "TEXT")
    private String extractedText;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
