package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyReviewRequest(
        @NotNull(message = "Technician id is required")
        Long technicianId,
        @NotBlank(message = "Reply is required")
        String technicianReply
) {
}
