package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.client;

import com.juanjose.backendfastfix.application.port.in.client.LoginClientUseCase;
import com.juanjose.backendfastfix.application.port.in.client.RegisterClientUseCase;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.LoginClientRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.RegisterClientRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ClientRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/auth")
public class ClientAuthController {
    private final RegisterClientUseCase registerClientUseCase;
    private final LoginClientUseCase loginClientUseCase;

    public ClientAuthController(RegisterClientUseCase registerClientUseCase, LoginClientUseCase loginClientUseCase) {
        this.registerClientUseCase = registerClientUseCase;
        this.loginClientUseCase = loginClientUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ClientResponse> register(@Valid @RequestBody RegisterClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClientRestMapper.fromDomain(registerClientUseCase
                        .register(ClientRestMapper.clientRegistrationToDomain(request))));

    }

    @PostMapping("/login")
    public ResponseEntity<ClientResponse> login(@Valid @RequestBody LoginClientRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ClientRestMapper.fromDomain(loginClientUseCase
                        .login(request.email(),request.password())));

    }
}
