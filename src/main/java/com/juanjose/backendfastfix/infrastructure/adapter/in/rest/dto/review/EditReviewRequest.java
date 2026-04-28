package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EditReviewRequest(
        @NotNull(message = "Client id is required")
        Long clientId,

        @Max(value = 1, message ="Rating must be at least 1")
        @Min(value = 5, message = "Rating must be at most 5")
        Integer rating,

        String comment
) {
}
