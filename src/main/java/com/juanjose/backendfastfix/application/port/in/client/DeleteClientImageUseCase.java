package com.juanjose.backendfastfix.application.port.in.client;

import com.juanjose.backendfastfix.domain.model.Client;

public interface DeleteClientImageUseCase {
    Client deleteProfileImage(Long id);
}
