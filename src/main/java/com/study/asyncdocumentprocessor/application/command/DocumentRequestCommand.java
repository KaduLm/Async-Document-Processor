package com.study.asyncdocumentprocessor.application.command;

import org.springframework.web.multipart.MultipartFile;

public record DocumentRequestCommand(MultipartFile file) {
}
