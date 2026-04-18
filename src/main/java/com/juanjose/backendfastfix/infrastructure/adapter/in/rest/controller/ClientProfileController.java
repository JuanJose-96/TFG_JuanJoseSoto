package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller;

import com.juanjose.backendfastfix.application.port.in.UpdateClientProfileUseCase;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.UpdateClientProfileRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ClientRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client/profile")
public class ClientProfileController {
    private final UpdateClientProfileUseCase updateClientProfileUseCase;

    public ClientProfileController(UpdateClientProfileUseCase updateClientProfileUseCase) {
        this.updateClientProfileUseCase = updateClientProfileUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody UpdateClientProfileRequest request){
        return ResponseEntity.ok(ClientRestMapper
                .fromDomain(updateClientProfileUseCase
                        .updateProfile(id,
                                request.name(),
                                request.surname(),
                                request.phone(),
                                request.province(),
                                request.city())));

    }
}
