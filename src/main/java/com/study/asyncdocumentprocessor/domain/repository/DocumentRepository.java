package com.study.asyncdocumentprocessor.domain.repository;

import com.study.asyncdocumentprocessor.domain.model.Document;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DocumentRepository {

    void save(Document document);

    Optional<Document> findById(UUID documentId);

    List<Document> findByQuery(String query);
}
