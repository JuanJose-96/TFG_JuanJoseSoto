package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.client;

import com.juanjose.backendfastfix.application.PagedResult;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.ClientPersistenceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PagedResult<Client> searchClients(String name, String province, String city, int page) {
        Pageable pageable = PageRequest.of(page,20, Sort.by(Sort.Order.desc("name")));
        String namePattern = startsWithPattern(name);

        Page<ClientEntity> result = jpaClientRepository.searchClients(namePattern,province,city,pageable);

        List<Client> clients = result.getContent()
                .stream().map(ClientPersistenceMapper::toDomain).toList();

        return new PagedResult<>(clients, result.getNumber(), result.getTotalPages()
                ,result.getTotalElements(), result.hasNext());

    }


    private String startsWithPattern(String name){
        if(name == null || name.isBlank()){
            return name;
        }
        return name.trim().toLowerCase() + '%';
    }


}
