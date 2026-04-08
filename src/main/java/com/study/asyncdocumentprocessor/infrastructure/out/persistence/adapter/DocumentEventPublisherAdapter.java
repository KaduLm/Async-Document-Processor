package com.study.asyncdocumentprocessor.infrastructure.out.persistence.adapter;

import com.study.asyncdocumentprocessor.application.dto.DocumentUploadedEvent;
import com.study.asyncdocumentprocessor.domain.port.DocumentEventPublisherPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentEventPublisherAdapter implements DocumentEventPublisherPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(DocumentUploadedEvent message) {
        log.info("Message: {} ", message);
        kafkaTemplate.send("document-topic", message);
    }
}
