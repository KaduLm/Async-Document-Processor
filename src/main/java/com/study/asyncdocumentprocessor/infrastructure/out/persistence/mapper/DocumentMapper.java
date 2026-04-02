package com.study.asyncdocumentprocessor.infrastructure.out.persistence.mapper;

import com.study.asyncdocumentprocessor.domain.enums.ProcessingStatus;
import com.study.asyncdocumentprocessor.domain.model.Document;
import com.study.asyncdocumentprocessor.infrastructure.in.web.dto.DocumentResponseDTO;
import com.study.asyncdocumentprocessor.infrastructure.out.persistence.entity.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentEntity toEntity(Document domain);

    Document toDomain(DocumentEntity entity);

    @Mapping(source = "filePath", target = "path")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    @Mapping(target = "contentType", ignore = true)
    DocumentResponseDTO toDocumentResponseDTO(Document domain);

    @Named("statusToString")
    default String statusToString(ProcessingStatus status) {
        return status != null ? status.name() : null;
    }

}
