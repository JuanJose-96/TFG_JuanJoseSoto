package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.exception;

import com.juanjose.backendfastfix.domain.exception.InvalidRatingException;
import com.juanjose.backendfastfix.domain.exception.ReviewAlreadyExistsException;
import com.juanjose.backendfastfix.domain.exception.ReviewNotFoundException;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerReviewException {

    @ExceptionHandler(ReviewAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleReviewAlreadyExists(ReviewAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        "Conflict",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));

    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleReviewNotFound(ReviewNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Not found",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));

    }

    @ExceptionHandler(InvalidRatingException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRating(InvalidRatingException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Not found",
                        ex.getMessage(),
                        LocalDateTime.now().toString()
                ));

    }
}
