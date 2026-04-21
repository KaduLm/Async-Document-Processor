package com.study.asyncdocumentprocessor.infrastructure.in.web.controller;

import com.study.asyncdocumentprocessor.application.command.DocumentRequestCommand;
import com.study.asyncdocumentprocessor.application.usecase.FindDocumentUseCase;
import com.study.asyncdocumentprocessor.application.usecase.UploadDocumentUseCase;
import com.study.asyncdocumentprocessor.infrastructure.in.web.dto.DocumentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
@Tag(name = "Document")
public class DocumentController {
    private final UploadDocumentUseCase uploadDocumentUseCase;
    private final FindDocumentUseCase findDocumentUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDocument(
            @RequestPart("file") MultipartFile file) throws IOException {

        uploadDocumentUseCase.uploadDocument(new DocumentRequestCommand(file));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<DocumentResponseDTO>> findByQuery(@PathVariable String query) {
        return ResponseEntity.ok().body(findDocumentUseCase.findByQuery(query));
    }
}
