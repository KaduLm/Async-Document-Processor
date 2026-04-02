package com.study.asyncdocumentprocessor.infrastructure.out.persistence.repository;

import com.study.asyncdocumentprocessor.infrastructure.out.persistence.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, UUID> {
}
