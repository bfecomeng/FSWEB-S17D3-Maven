package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ZooGlobalExceptionHandler {

    // 1. Projeye özel ZooException hatalarını yakalar
    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException exception) {
        log.error("ZooException occurred: {}", exception.getMessage());
        ZooErrorResponse response = new ZooErrorResponse(
                exception.getMessage(),
                exception.getHttpStatus().value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    // 2. Genel (beklenmeyen) Exception hatalarını yakalar
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleGeneralException(Exception exception) {
        log.error("Unexpected exception occurred: {}", exception.getMessage());
        ZooErrorResponse response = new ZooErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}