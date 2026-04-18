package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto;


import jakarta.validation.constraints.NotBlank;


public record UpdateClientProfileRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @NotBlank(message = "Phone is required")
        String phone,
        @NotBlank(message = "Province is required")
        String province,
        @NotBlank(message = "City is required")
        String city
) {
}
