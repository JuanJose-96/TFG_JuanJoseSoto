package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper;

import com.juanjose.backendfastfix.domain.model.Work;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.CreateWorkRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.EditWorkRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.WorkResponse;

public class WorkRestMapper {
    public static Work createToDomain(CreateWorkRequest request){
        return Work.builder()
                .technicianId(request.technicianId())
                .clientId(request.clientId())
                .clientName(request.clientName())
                .clientSurname(request.clientSurname())
                .clientProvince(request.clientProvince())
                .clientCity(request.clientCity())
                .serviceDate(request.serviceDate())
                .totalAmount(request.totalAmount())
                .serviceDescription(request.serviceDescription())
                .build();
    }

    public static WorkResponse fromDomain(Work work){
        return new WorkResponse(
                work.getId(),
                work.getTechnicianId(),
                work.getClientId(),
                work.getClientName(),
                work.getClientSurname(),
                work.getClientProvince(),
                work.getClientCity(),
                work.getServiceDate(),
                work.getTotalAmount(),
                work.getServiceDescription()
        );
    }

    public static Work editToDomain(EditWorkRequest request){
        return Work.builder()
                .clientId(request.clientId())
                .clientName(request.clientName())
                .clientSurname(request.clientSurname())
                .clientProvince(request.clientProvince())
                .clientCity(request.clientCity())
                .serviceDate(request.serviceDate())
                .totalAmount(request.totalAmount())
                .serviceDescription(request.serviceDescription())
                .build();
    }
}
