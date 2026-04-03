package com.study.asyncdocumentprocessor.infrastructure.out.persistence.adapter;

import com.study.asyncdocumentprocessor.application.dto.DocumentUploadedEvent;
import com.study.asyncdocumentprocessor.domain.port.DocumentEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentEventPublisherAdapter implements DocumentEventPublisherPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(DocumentUploadedEvent message) {
        kafkaTemplate.send("document-topic", message);
    }
}
