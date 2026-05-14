package com.juanjose.backendfastfix.application.port.in.client;

import com.juanjose.backendfastfix.domain.model.Client;

public interface GetClientProfileUseCase {
    Client getProfile(Long id);
}
