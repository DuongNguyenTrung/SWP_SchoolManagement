package com.swp.SchoolManagement.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalExeption.class)
    public ResponseEntity<String> handleNotFoundException(GlobalExeption e) {
        // e.printStackTrace();// Thực tế người ta dùng logger
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // e.printStackTrace();// Thực tế người ta dùng logger
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
