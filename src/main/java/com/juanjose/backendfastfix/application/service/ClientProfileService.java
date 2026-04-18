package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.UpdateClientProfileUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.ClientNotFoundException;
import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientProfileService implements UpdateClientProfileUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientProfileService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public Client updateProfile(Long id, String name, String surname, String phone, String province, String city) {
        Client client = clientRepositoryPort.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        Client clientUpdated = client.toBuilder()
                .name(name)
                .surname(surname)
                .phone(phone)
                .province(province)
                .city(city).build();

        return clientRepositoryPort.save(clientUpdated);
    }
}
