package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper;

import com.juanjose.backendfastfix.domain.model.Sector;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.RegisterTechnicianRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.TechnicianResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.UpdateTechnicianProfileRequest;

public class TechnicianRestMapper {
    public static Technician technicianRegistrationToDomain (RegisterTechnicianRequest request){
        return Technician.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(request.password())
                .phone(request.phone())
                .province(request.province())
                .city(request.city())
                .aboutMe(request.aboutMe())
                .sector(new Sector(request.sectorId(),null))
                .priceDescription(request.priceDescription())
                .emergencyAvailability(request.emergencyAvailability())
                .scheduleAvailability(request.scheduleAvailability())
                .build();
    }
    public static TechnicianResponse fromDomain(Technician technician) {
        return new TechnicianResponse( technician.getId(),
                technician.getName(),
                technician.getSurname(),
                technician.getEmail(),
                technician.getPhone(),
                technician.getProfileImageUrl(),
                technician.getProvince(),
                technician.getCity(),
                technician.getAboutMe(),
                technician.getSector().getId(),
                technician.getSector().getName(),
                technician.getPriceDescription(),
                technician.isEmergencyAvailability(),
                technician.getScheduleAvailability(),
                technician.isWhatsappAvailable(),
                technician.getAverageRating(),
                technician.getTotalReviews()
        );

    }

    public static Technician updateProfileToDomain(UpdateTechnicianProfileRequest request){
        return  Technician.builder()
                .name(request.name())
                .surname(request.surname())
                .phone(request.phone())
                .province(request.province())
                .city(request.city())
                .aboutMe(request.aboutMe())
                .sector(new Sector(request.sectorId(),null))
                .priceDescription(request.priceDescription())
                .emergencyAvailability(request.emergencyAvailability())
                .scheduleAvailability(request.scheduleAvailability())
                .whatsappAvailable(request.whatsappAvailable())
                .build();

    }
}
