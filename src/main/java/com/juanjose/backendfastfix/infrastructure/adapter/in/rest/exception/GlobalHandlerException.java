package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.exception;

import com.juanjose.backendfastfix.domain.exception.DomainException;
import com.juanjose.backendfastfix.domain.exception.EmailAlreadyExists;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ApiErrorResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity <ApiErrorResponse> handleEmailAlreadyExists(EmailAlreadyExists ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(HttpStatus.CONFLICT.value(),
                        "Conflict",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));

    }
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiErrorResponse> handleDomainException(DomainException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad request",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                fieldErrors.put(e.getField(),e.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation failed",
                        fieldErrors,
                        LocalDateTime.now().toString()
                ));

    }


}
