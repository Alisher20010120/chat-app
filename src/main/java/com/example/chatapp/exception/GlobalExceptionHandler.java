package com.example.chatapp.exception;

import com.example.chatapp.exception.response.ErrorResponse;
import com.example.chatapp.exception.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        ErrorResponse response = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponse response = ErrorResponse.builder().status(HttpStatus.NOT_FOUND.value()).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        ErrorResponse response = ErrorResponse.builder().status(HttpStatus.UNAUTHORIZED.value()).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(400, errors, LocalDateTime.now());
        return ResponseEntity.status(400).body(validationErrorResponse);
    }

}
