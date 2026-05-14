package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician;

import jakarta.validation.constraints.*;

public record RegisterTechnicianRequest(
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
        @Pattern(regexp = "^[0-9]{9}$", message = "Phone must be 9 digits")
        String phone,
        @NotBlank(message = "Province is required")
        String province,
        @NotBlank(message = "City is required")
        String city,

        String aboutMe,

        @NotNull (message = "Sector is required")
        Long sectorId,

        String priceDescription,

        boolean emergencyAvailability,

        String scheduleAvailability
) {
}
