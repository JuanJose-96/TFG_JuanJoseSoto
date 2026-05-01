package com.juanjose.backendfastfix.application.port.out;

import com.juanjose.backendfastfix.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {
    Client save (Client client);
    boolean exitsByEmail(String email);
    Optional<Client> findByEmail(String email);
    Optional<Client> findById(Long id);
    List<Client> searchClients(String name, String province, String city);
}
