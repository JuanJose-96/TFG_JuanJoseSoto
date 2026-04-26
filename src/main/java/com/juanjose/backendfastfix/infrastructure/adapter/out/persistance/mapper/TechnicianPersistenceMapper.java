package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper;

import com.juanjose.backendfastfix.domain.model.Sector;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.SectorEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;

public class TechnicianPersistenceMapper {
    public static TechnicianEntity toEntity(Technician technician) {
        SectorEntity sector = new SectorEntity();
        sector.setId(technician.getSector().getId());
        sector.setName(technician.getSector().getName());
        return TechnicianEntity.builder()
                .id(technician.getId())
                .name(technician.getName())
                .surname(technician.getSurname())
                .email(technician.getEmail())
                .password(technician.getPassword())
                .phone(technician.getPhone())
                .profileImageUrl(technician.getProfileImageUrl())
                .province(technician.getProvince())
                .city(technician.getCity())
                .aboutMe(technician.getAboutMe())
                .sectorEntity(sector)
                .priceDescription(technician.getPriceDescription())
                .emergencyAvailability(technician.isEmergencyAvailability())
                .scheduleAvailability(technician.getScheduleAvailability())
                .whatsappAvailable(technician.isWhatsappAvailable())
                .averageRating(technician.getAverageRating())
                .totalReviews(technician.getTotalReviews())
                .build();

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
                .sector(new Sector(entity.getSectorEntity().getId(), entity.getSectorEntity().getName()))
                .priceDescription(entity.getPriceDescription())
                .emergencyAvailability(entity.isEmergencyAvailability())
                .scheduleAvailability(entity.getScheduleAvailability())
                .whatsappAvailable(entity.isWhatsappAvailable())
                .averageRating(entity.getAverageRating())
                .totalReviews(entity.getTotalReviews())
                .build();
    }
}
