package com.juanjose.backendfastfix.application.service.client;

import com.juanjose.backendfastfix.application.port.in.client.LoginClientUseCase;
import com.juanjose.backendfastfix.application.port.in.client.RegisterClientUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.PasswordEncoderPort;
import com.juanjose.backendfastfix.domain.exception.ClientNotFoundException;
import com.juanjose.backendfastfix.domain.exception.EmailAlreadyExists;
import com.juanjose.backendfastfix.domain.exception.InvalidPasswordException;
import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthService implements RegisterClientUseCase, LoginClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public ClientAuthService(ClientRepositoryPort clientRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
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

    @Override
    public Client login(String email, String password) {
        Client client = clientRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(email));

        if(!passwordEncoderPort.matches(password,client.getPassword())){
            throw new InvalidPasswordException();
        }

        return client;
    }
}
