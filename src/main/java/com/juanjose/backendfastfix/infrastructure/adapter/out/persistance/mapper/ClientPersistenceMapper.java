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
        entity.setVerified(client.isVerified());
        return entity;

    }
    public static Client toDomain (ClientEntity entity){
        return new Client( entity.getId(), entity.getName(),
                entity.getSurname(), entity.getEmail(), entity.getPassword(),
                entity.getPhone(), entity.getProfileImageUrl(),
                entity.getProvince(), entity.getCity(), entity.isVerified() );

    }
}
