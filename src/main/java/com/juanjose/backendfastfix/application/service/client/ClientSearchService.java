package com.juanjose.backendfastfix.application.service.client;

import com.juanjose.backendfastfix.application.port.in.client.SearchClientsUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientSearchService implements SearchClientsUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    public ClientSearchService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public List<Client> searchClients(String name, String province, String city) {
        return clientRepositoryPort.searchClients(name,province,city);

    }
}
