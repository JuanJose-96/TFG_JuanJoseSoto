package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.client;

import com.juanjose.backendfastfix.application.port.in.client.SearchClientsUseCase;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ClientRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients/search")
public class ClientSearchController {
    private final SearchClientsUseCase searchClientsUseCase;

    public ClientSearchController(SearchClientsUseCase searchClientsUseCase) {
        this.searchClientsUseCase = searchClientsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> searchClients(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String province,
                                                              @RequestParam(required = false) String city){
       List<ClientResponse> clients = searchClientsUseCase.searchClients(name,province,city)
                .stream()
               .map(ClientRestMapper::fromDomain).toList();

        return ResponseEntity.ok(clients);

    }
}
