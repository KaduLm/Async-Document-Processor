package com.study.asyncdocumentprocessor.domain.port;

import com.study.asyncdocumentprocessor.application.dto.DocumentUploadedEvent;


public interface DocumentEventPublisherPort {
    void publish(DocumentUploadedEvent message);
}
