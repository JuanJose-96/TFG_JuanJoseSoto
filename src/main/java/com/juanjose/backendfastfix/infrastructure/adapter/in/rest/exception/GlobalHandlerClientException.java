package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.exception;

import com.juanjose.backendfastfix.domain.exception.*;
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
public class GlobalHandlerClientException {

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
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleClientNotFoundByEmailException(ClientNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Not found",
                        ex.getMessage(),
                        LocalDateTime.now().toString()));

    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPasswordException(InvalidPasswordException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiErrorResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        "Unauthorized",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));

    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ApiErrorResponse> handleImageUpload(ImageUploadException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal server error",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));

    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidFile(InvalidFileException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad request",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));
    }


}
