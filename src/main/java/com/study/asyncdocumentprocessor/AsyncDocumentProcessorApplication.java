package com.study.asyncdocumentprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AsyncDocumentProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncDocumentProcessorApplication.class, args);
    }

}
