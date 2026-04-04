package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterClientRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @NotBlank(message = "Email is required")
        @Email(message = "Email format is not valid")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,
        @NotBlank(message = "Phone is required")
        String phone,
        @NotBlank(message = "Province is required")
        String province,
        @NotBlank(message = "City is required")
        String city
) {
}
