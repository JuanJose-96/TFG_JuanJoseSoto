package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work;

import jakarta.validation.constraints.NotNull;

public record DeleteWorkRequest(
        @NotNull(message = "Technician id is required")
        Long technicianId
) {
}
