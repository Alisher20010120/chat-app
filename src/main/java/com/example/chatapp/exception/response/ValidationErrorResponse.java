package com.example.chatapp.exception.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;
    
    public ValidationErrorResponse(int status, Map<String, String> errors, LocalDateTime timestamp) {
        super(status, null, timestamp);
        this.errors = errors;
    }
}