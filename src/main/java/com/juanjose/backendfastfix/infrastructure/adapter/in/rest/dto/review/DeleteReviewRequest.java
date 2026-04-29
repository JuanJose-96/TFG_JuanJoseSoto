package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review;

import jakarta.validation.constraints.NotNull;

public record DeleteReviewRequest(
        @NotNull(message = "Client id is required")
        Long clientId
) {
}
