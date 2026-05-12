package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WorkResponse(
        Long id,
        Long technicianId,
        Long clientId,
        String clientName,
        String clientSurname,
        String clientProvince,
        String clientCity,
        LocalDate serviceDate,
        BigDecimal totalAmount,
        String serviceDescription
) {
}
