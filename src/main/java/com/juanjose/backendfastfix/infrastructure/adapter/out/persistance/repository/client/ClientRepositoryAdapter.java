package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.client;

import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.ClientPersistenceMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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
                .map(ClientPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaClientRepository.findById(id).map(ClientPersistenceMapper::toDomain);
    }

    @Override
    public List<Client> searchClients(String name, String province, String city) {
        Pageable pageable = PageRequest.of(20,0);

        return jpaClientRepository.searchClients(name,province,city,pageable)
                .stream().map(ClientPersistenceMapper::toDomain).toList();
    }


}
