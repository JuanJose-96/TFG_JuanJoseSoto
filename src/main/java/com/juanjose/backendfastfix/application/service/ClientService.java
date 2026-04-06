package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.RegisterClientUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.PasswordEncoderPort;
import com.juanjose.backendfastfix.domain.exception.EmailAlreadyExists;
import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements RegisterClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public ClientService(ClientRepositoryPort clientRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public Client register(Client client) {
        if(clientRepositoryPort.exitsByEmail(client.getEmail())){
            throw new EmailAlreadyExists(client.getEmail());
        }
        String hashedPassword = passwordEncoderPort.encode(client.getPassword());
        Client clientToSave = client.toBuilder()
                .password(hashedPassword)
                .build();
        return clientRepositoryPort.save(clientToSave);
    }
}
