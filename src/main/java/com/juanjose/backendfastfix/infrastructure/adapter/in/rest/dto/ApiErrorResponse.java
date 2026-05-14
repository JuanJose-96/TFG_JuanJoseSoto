package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto;

public record ApiErrorResponse(
        int status,
        String error,
        String message,
        String timestamp
) {
}
