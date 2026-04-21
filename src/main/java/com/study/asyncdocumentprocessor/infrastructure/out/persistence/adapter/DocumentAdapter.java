package com.study.asyncdocumentprocessor.infrastructure.out.persistence.adapter;

import com.study.asyncdocumentprocessor.domain.model.Document;
import com.study.asyncdocumentprocessor.domain.repository.DocumentRepository;
import com.study.asyncdocumentprocessor.infrastructure.out.persistence.mapper.DocumentMapper;
import com.study.asyncdocumentprocessor.infrastructure.out.persistence.repository.JpaDocumentRepository;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DocumentAdapter implements DocumentRepository {
    private final JpaDocumentRepository jpaDocumentRepository;
    private final DocumentMapper documentMapper;
    private final EmbeddingModel embeddingModel;


    @Override
    public void save(Document document) {
        jpaDocumentRepository.save(documentMapper.toEntity(document));
    }

    @Override
    public Optional<Document> findById(UUID documentId) {
        return jpaDocumentRepository.findById(documentId).map(documentMapper::toDomain);
    }

    @Override
    public List<Document> findByQuery(String query) {
        return jpaDocumentRepository
                .findSimilarDocuments(Arrays.toString(embeddingModel.embed(query).content().vector()), 3)
                .stream()
                .map(documentMapper::toDomain)
                .toList();
    }


}
