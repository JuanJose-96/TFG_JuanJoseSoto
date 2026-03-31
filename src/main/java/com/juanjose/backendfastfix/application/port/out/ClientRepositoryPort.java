package com.juanjose.backendfastfix.application.port.out;

import com.juanjose.backendfastfix.domain.model.Client;

public interface ClientRepositoryPort {
    Client save (Client client);
    boolean exitsByEmail(String email);
}
