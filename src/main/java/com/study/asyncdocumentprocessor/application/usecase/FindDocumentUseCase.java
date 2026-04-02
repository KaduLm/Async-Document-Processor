package com.study.asyncdocumentprocessor.application.usecase;

import com.study.asyncdocumentprocessor.domain.repository.DocumentRepository;
import com.study.asyncdocumentprocessor.infrastructure.in.web.dto.DocumentResponseDTO;
import com.study.asyncdocumentprocessor.infrastructure.out.persistence.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;


    public DocumentResponseDTO findById(UUID id){
        return documentRepository.findById(id).map(documentMapper::toDocumentResponseDTO)
                .orElseThrow();

    }
}
