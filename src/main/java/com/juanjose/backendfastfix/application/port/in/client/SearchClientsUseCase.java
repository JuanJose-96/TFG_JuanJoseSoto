package com.juanjose.backendfastfix.application.port.in.client;

import com.juanjose.backendfastfix.domain.model.Client;

import java.util.List;

public interface SearchClientsUseCase {
    List<Client> searchClients(String name, String province, String city);
}
