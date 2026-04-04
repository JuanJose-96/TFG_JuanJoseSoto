package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper;

import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.RegisterClientRequest;

public class ClientRestMapper {
    public static Client clientRegistrationToDomain(RegisterClientRequest registerClientRequest){
        return new Client(registerClientRequest.name(),
                registerClientRequest.surname(),
                registerClientRequest.email(),
                registerClientRequest.password(),
                registerClientRequest.phone(),
                null,
                registerClientRequest.province(),
                registerClientRequest.city(),
                false);
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
                client.isVerified());

    }
}
