package com.study.asyncdocumentprocessor.application.usecase;

import com.study.asyncdocumentprocessor.application.command.DocumentRequestCommand;
import com.study.asyncdocumentprocessor.application.dto.DocumentUploadedEvent;
import com.study.asyncdocumentprocessor.domain.enums.ProcessingStatus;
import com.study.asyncdocumentprocessor.domain.model.Document;
import com.study.asyncdocumentprocessor.domain.port.DocumentEventPublisherPort;
import com.study.asyncdocumentprocessor.domain.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final DocumentEventPublisherPort documentEventPublisherPort;

    public void uploadDocument(DocumentRequestCommand documentRequestCommand){
        Document document = new Document(
                UUID.randomUUID(),
                documentRequestCommand.filePath(),
                ProcessingStatus.PENDING,
                null,
                LocalDateTime.now());
        documentRepository.save(document);

        DocumentUploadedEvent documentUploadedEvent = new DocumentUploadedEvent(document.getId(), document.getFilePath(), document.getStatus().name());

        documentEventPublisherPort.publish(documentUploadedEvent);
    }
}
