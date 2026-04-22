package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginTechnicianRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email format is not valid")
        String email,
        @NotBlank(message = "Password is required")
        String password
) {
}
