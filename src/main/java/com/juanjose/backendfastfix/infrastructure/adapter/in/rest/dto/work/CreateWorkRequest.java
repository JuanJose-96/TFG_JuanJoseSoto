package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateWorkRequest(
        @NotNull(message = "Technician id is required")
        Long technicianId,
        @NotNull(message = "Client id is required")
        Long clientId,
        @NotBlank(message = "Client name is required")
        String clientName,
        String clientSurname,
        @NotBlank(message = "Client province is required")
        String clientProvince,
        @NotBlank(message = "Client city is required")
        String clientCity,
        @NotNull(message = "Service date is required")
        LocalDate serviceDate,
        @NotNull(message = "Total Amount is required")
        BigDecimal totalAmount,
        @NotNull(message = "Service description is required")
        String serviceDescription
) {
}
