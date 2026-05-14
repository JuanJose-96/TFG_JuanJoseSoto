package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PublishReviewRequest(
        @NotNull(message = "Client id is required")
        Long clientId,

        @NotNull (message = "Technician id is required")
        Long technicianId,

        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        int rating,

        String comment
) {
}
