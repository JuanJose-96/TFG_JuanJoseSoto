package com.juanjose.backendfastfix.application.port.in.client;

import com.juanjose.backendfastfix.domain.model.Client;

public interface LoginClientUseCase {
    Client login(String email, String password);
}
