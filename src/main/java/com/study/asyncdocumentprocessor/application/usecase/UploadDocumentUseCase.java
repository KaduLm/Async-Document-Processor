package com.study.asyncdocumentprocessor.application.usecase;

import com.study.asyncdocumentprocessor.application.command.DocumentRequestCommand;
import com.study.asyncdocumentprocessor.application.dto.DocumentUploadedEvent;
import com.study.asyncdocumentprocessor.domain.enums.ProcessingStatus;
import com.study.asyncdocumentprocessor.domain.model.Document;
import com.study.asyncdocumentprocessor.domain.port.DocumentEventPublisherPort;
import com.study.asyncdocumentprocessor.domain.port.MinIOPort;
import com.study.asyncdocumentprocessor.domain.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadDocumentUseCase {
    private final DocumentRepository documentRepository;
    private final DocumentEventPublisherPort documentEventPublisherPort;
    private final MinIOPort minIOPort;

    @Transactional
    public void uploadDocument(DocumentRequestCommand documentRequestCommand) throws IOException {
        MultipartFile file = documentRequestCommand.file();

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (!file.getContentType().equals("application/pdf")) {
            throw new RuntimeException("File must be a PDF");
        }

        log.info("Processing upload - Original size: {} | Read bytes: {}",
                file.getSize(), file.getBytes().length);


        UUID id = UUID.randomUUID();
        String fileName = id + ".pdf";

        Document document = new Document(
                id,
                fileName,
                ProcessingStatus.PENDING,
                null,
                LocalDateTime.now()
        );

        documentRepository.save(document);

        minIOPort.uploadStream(fileName, file.getInputStream(), file.getSize(), file.getContentType());

        log.info("Document saved and uploaded successfully: {}", id);

        DocumentUploadedEvent documentUploadedEvent = new DocumentUploadedEvent(
                document.getId(),
                fileName,
                document.getStatus().name()
        );

        documentEventPublisherPort.publish(documentUploadedEvent);
    }
}