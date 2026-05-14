package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician;

public record TechnicianResponse(
        Long id,
        String name,
        String surname,
        String email,
        String phone,
        String profileImageUrl,
        String province,
        String city,
        String aboutMe,
        Long sectorId,
        String sectorName,
        String priceDescription,
        boolean emergencyAvailability,
        String scheduleAvailability,
        boolean whatsappAvailable,
        Double averageRating,
        Integer totalReviews


) {
}
