package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper;


import com.juanjose.backendfastfix.domain.model.Work;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.WorkEntity;

public class WorkPersistenceMapper {
    public static WorkEntity toEntity(Work work){
        TechnicianEntity tech = new TechnicianEntity();
        tech.setId(work.getTechnicianId());

        return WorkEntity.builder()
                .id(work.getId())
                .technician(tech)
                .clientId(work.getClientId())
                .clientName(work.getClientName())
                .clientSurname(work.getClientSurname())
                .clientProvince(work.getClientProvince())
                .clientCity(work.getClientCity())
                .serviceDate(work.getServiceDate())
                .totalAmount(work.getTotalAmount())
                .serviceDescription(work.getServiceDescription())
                .build();

    }

    public static Work toDomain (WorkEntity entity){
        return Work.builder()
                .id(entity.getId())
                .technicianId(entity.getTechnician().getId())
                .clientId(entity.getClientId())
                .clientName(entity.getClientName())
                .clientSurname(entity.getClientSurname())
                .clientProvince(entity.getClientProvince())
                .clientCity(entity.getClientCity())
                .serviceDate(entity.getServiceDate())
                .totalAmount(entity.getTotalAmount())
                .serviceDescription(entity.getServiceDescription())
                .build();
    }
}
