package com.lunch.ops.backend.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException domainException) {
        int statusCode = domainException.getStatusCode();
        Object detail = domainException.getDetail();

        ErrorResponse errorResponse = new ErrorResponse(detail);

        return ResponseEntity.status(statusCode).body(errorResponse);
    }
}
