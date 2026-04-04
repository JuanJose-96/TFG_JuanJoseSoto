package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto;

public record ClientResponse(
        Long id,
        String name,
        String surname,
        String email,
        String phone,
        String profileImageUrl,
        String province,
        String city,
        boolean verified
) {
}
