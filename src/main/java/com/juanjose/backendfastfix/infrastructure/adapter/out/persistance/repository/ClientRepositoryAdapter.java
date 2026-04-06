package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository;

import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.ClientPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements ClientRepositoryPort {
    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryAdapter(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }


    @Override
    public Client save(Client client) {
        ClientEntity entity = ClientPersistenceMapper.toEntity(client);
        ClientEntity savedEntity = jpaClientRepository.save(entity);
        return ClientPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public boolean exitsByEmail(String email) {
        return jpaClientRepository.existsByEmail(email);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return jpaClientRepository.findByEmail(email)
                .map(clientEntity -> ClientPersistenceMapper.toDomain(clientEntity));
    }


}
