package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record UpdateClientProfileRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^[0-9]{9}$",message = "Phone must be 9 digits")
        String phone,
        @NotBlank(message = "Province is required")
        String province,
        @NotBlank(message = "City is required")
        String city,
        boolean whatsappAvailable
) {
}
