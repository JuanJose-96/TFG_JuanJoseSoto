package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.UpdateClientProfileUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.ClientNotFoundException;
import com.juanjose.backendfastfix.domain.model.Client;

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
                .name(name != null ? name : client.getName())
                .surname(surname != null ? surname : client.getSurname())
                .phone(phone != null ? phone : client.getPhone())
                .province(province != null ? province : client.getProvince())
                .city(city != null ? city : client.getCity()).build();

        return clientRepositoryPort.save(clientUpdated);
    }
}
