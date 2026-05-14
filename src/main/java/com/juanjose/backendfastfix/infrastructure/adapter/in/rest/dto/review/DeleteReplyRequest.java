package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review;

import jakarta.validation.constraints.NotNull;

public record DeleteReplyRequest(
        @NotNull(message = "Technician id is required")
        Long technicianId
) {
}
