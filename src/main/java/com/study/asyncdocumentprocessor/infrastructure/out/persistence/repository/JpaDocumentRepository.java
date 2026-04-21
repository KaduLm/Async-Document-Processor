package com.study.asyncdocumentprocessor.infrastructure.out.persistence.repository;

import com.study.asyncdocumentprocessor.infrastructure.out.persistence.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, UUID> {

    @Query(value = """
        SELECT d.* FROM processor_document.document d
        JOIN processor_document.document_embeddings de ON d.id = de.document_id
        ORDER BY de.embedding <=> CAST(:vector AS vector)
        LIMIT :limit
        """, nativeQuery = true)
    List<DocumentEntity> findSimilarDocuments(@Param("vector") String vectorString, @Param("limit") int limit);
}
