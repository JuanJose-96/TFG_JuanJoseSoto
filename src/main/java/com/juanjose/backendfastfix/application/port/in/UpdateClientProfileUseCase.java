package com.juanjose.backendfastfix.application.port.in;

import com.juanjose.backendfastfix.domain.model.Client;

public interface UpdateClientProfileUseCase {
    Client updateProfile(Long id, String name, String surname, String phone,
                         String province, String city);
}
