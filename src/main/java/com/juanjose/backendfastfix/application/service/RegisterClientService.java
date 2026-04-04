package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.RegisterClientUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.EmailAlreadyExists;
import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientService implements RegisterClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    public RegisterClientService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Client register(Client client) {
        if(clientRepositoryPort.exitsByEmail(client.getEmail())){
            throw new EmailAlreadyExists(client.getEmail());
        }
        return clientRepositoryPort.save(client);
    }
}
