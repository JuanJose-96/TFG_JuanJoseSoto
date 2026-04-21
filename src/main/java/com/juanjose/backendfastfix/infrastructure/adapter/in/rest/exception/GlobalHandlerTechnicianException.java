package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.exception;

import com.juanjose.backendfastfix.domain.exception.SectorNotFoundException;
import com.juanjose.backendfastfix.domain.exception.TechnicianNotFoundException;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerTechnicianException {

    @ExceptionHandler(TechnicianNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTechnicianNotFound(TechnicianNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Not found",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                )
        );
    }

    @ExceptionHandler(SectorNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleSectorNotFound(SectorNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Not found",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                )
        );
    }
}
