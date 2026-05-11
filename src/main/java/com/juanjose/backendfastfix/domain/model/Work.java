package com.juanjose.backendfastfix.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Work {
    private Long id;
    private Long technicianId;
    private Long clientId;
    private String clientName;
    private String clientSurname;
    private String clientProvince;
    private String clientCity;
    private LocalDate serviceDate;
    private BigDecimal totalAmount;
    private String serviceDescription;

}
