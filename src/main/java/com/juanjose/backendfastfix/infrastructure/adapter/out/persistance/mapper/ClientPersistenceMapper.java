package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper;

import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;

public class ClientPersistenceMapper {
    public static ClientEntity toEntity (Client client){
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setSurname(client.getSurname());
        entity.setEmail(client.getEmail());
        entity.setPassword(client.getPassword());
        entity.setPhone(client.getPhone());
        entity.setProfileImageUrl(client.getProfileImageUrl());
        entity.setProvince(client.getProvince());
        entity.setCity(client.getCity());
        entity.setWhatsappAvailable(client.isWhatsappAvailable());
        return entity;

    }
    public static Client toDomain (ClientEntity entity){
        return Client.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .profileImageUrl(entity.getProfileImageUrl())
                .province(entity.getProvince())
                .city(entity.getCity())
                .whatsappAvailable(entity.isWhatsappAvailable())
                .build();


    }
}
