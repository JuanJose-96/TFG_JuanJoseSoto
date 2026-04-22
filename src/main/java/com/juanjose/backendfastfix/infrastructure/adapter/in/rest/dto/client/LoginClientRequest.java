package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginClientRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email format is not valid")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {
}
