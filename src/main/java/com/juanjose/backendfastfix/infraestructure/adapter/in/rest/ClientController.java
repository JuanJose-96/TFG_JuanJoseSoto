package com.juanjose.backendfastfix.infraestructure.adapter.in.rest;

import com.juanjose.backendfastfix.application.port.in.RegisterClientUseCase;
import com.juanjose.backendfastfix.infraestructure.adapter.in.rest.dto.ClientResponse;
import com.juanjose.backendfastfix.infraestructure.adapter.in.rest.dto.RegisterClientRequest;
import com.juanjose.backendfastfix.infraestructure.adapter.in.rest.mapper.ClientRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    private final RegisterClientUseCase registerClientUseCase;

    public ClientController(RegisterClientUseCase registerClientUseCase) {
        this.registerClientUseCase = registerClientUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ClientResponse> register(@Valid @RequestBody RegisterClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClientRestMapper.fromDomain(registerClientUseCase
                        .register(ClientRestMapper.clientRegistrationToDomain(request))));

    }
}
