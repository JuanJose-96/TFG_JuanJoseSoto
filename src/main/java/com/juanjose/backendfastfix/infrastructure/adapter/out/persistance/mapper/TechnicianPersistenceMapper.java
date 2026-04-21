package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper;

import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;

public class TechnicianPersistenceMapper {
    public static TechnicianEntity toEntity(Technician technician) {
        TechnicianEntity entity = new TechnicianEntity();
        entity.setId(technician.getId());
        entity.setName(technician.getName());
        entity.setSurname(technician.getSurname());
        entity.setEmail(technician.getEmail());
        entity.setPassword(technician.getPassword());
        entity.setPhone(technician.getPhone());
        entity.setProfileImageUrl(technician.getProfileImageUrl());
        entity.setProvince(technician.getProvince());
        entity.setCity(technician.getCity());
        entity.setAboutMe(technician.getAboutMe());
        entity.setMainSectorId(technician.getMainSectorId());
        entity.setPriceDescription(technician.getPriceDescription());
        entity.setEmergencyAvailability(technician.isEmergencyAvailability());
        entity.setScheduleAvailability(technician.getScheduleAvailability());
        entity.setWhatsappAvailable(technician.isWhatsappAvailable());
        entity.setAverageRating(technician.getAverageRating());
        entity.setTotalReviews(technician.getTotalReviews());
        return entity;
    }
    public static Technician toDomain(TechnicianEntity entity) {
        return Technician.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .profileImageUrl(entity.getProfileImageUrl())
                .province(entity.getProvince())
                .city(entity.getCity())
                .aboutMe(entity.getAboutMe())
                .mainSectorId(entity.getMainSectorId())
                .priceDescription(entity.getPriceDescription())
                .emergencyAvailability(entity.isEmergencyAvailability())
                .scheduleAvailability(entity.getScheduleAvailability())
                .whatsappAvailable(entity.isWhatsappAvailable())
                .averageRating(entity.getAverageRating())
                .totalReviews(entity.getTotalReviews())
                .build();
    }
}
