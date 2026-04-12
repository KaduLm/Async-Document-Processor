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
    @Operation(summary = "Upload de documento", description = "Recebe um arquivo e inicia o processamento do documento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento enviado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar o documento")
    })
    public ResponseEntity<Void> uploadDocument(
            @RequestPart("file") MultipartFile file) throws IOException {

        uploadDocumentUseCase.uploadDocument(new DocumentRequestCommand(file));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar documento por ID", description = "Retorna os dados de um documento com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento encontrado",
                    content = @Content(schema = @Schema(implementation = DocumentResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Documento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar o documento")
    })
    public ResponseEntity<DocumentResponseDTO> findDocumentById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(findDocumentUseCase.findById(id));
    }
}
