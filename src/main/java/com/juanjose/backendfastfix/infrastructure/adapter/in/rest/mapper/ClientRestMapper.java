package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper;

import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.RegisterClientRequest;

public class ClientRestMapper {
    public static Client clientRegistrationToDomain(RegisterClientRequest request){
        return Client.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(request.password())
                .phone(request.phone())
                .province(request.province())
                .city(request.city())
                .build();

    }
    public static ClientResponse fromDomain(Client client){
        return new ClientResponse(client.getId(),
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPhone(),
                client.getProfileImageUrl(),
                client.getProvince(),
                client.getCity(),
                client.isWhatsappAvailable());

    }
}
