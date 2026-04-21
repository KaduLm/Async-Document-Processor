package com.study.asyncdocumentprocessor.application.usecase;

import com.study.asyncdocumentprocessor.domain.model.Document;
import com.study.asyncdocumentprocessor.domain.port.MinIOPort;
import com.study.asyncdocumentprocessor.domain.repository.DocumentRepository;
import com.study.asyncdocumentprocessor.infrastructure.in.web.dto.DocumentResponseDTO;
import com.study.asyncdocumentprocessor.infrastructure.out.persistence.entity.DocumentEntity;
import com.study.asyncdocumentprocessor.infrastructure.out.persistence.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;


    public List<DocumentResponseDTO> findByQuery(String query){
        return documentRepository.findByQuery(query).stream().map(documentMapper::toDocumentResponseDTO).toList();
    }
}
