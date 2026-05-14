package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.client;

import com.juanjose.backendfastfix.application.PagedResult;
import com.juanjose.backendfastfix.application.port.in.client.SearchClientsUseCase;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.PagedResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ClientRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients/search")
public class ClientSearchController {
    private final SearchClientsUseCase searchClientsUseCase;

    public ClientSearchController(SearchClientsUseCase searchClientsUseCase) {
        this.searchClientsUseCase = searchClientsUseCase;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ClientResponse>> searchClients(@RequestParam(required = false) String name,
                                                                       @RequestParam(required = false) String province,
                                                                       @RequestParam(required = false) String city,
                                                                       @RequestParam(defaultValue = "0") int page){

        PagedResult<Client> result = searchClientsUseCase
                .searchClients(name,province,city,page);

        List<ClientResponse> clients = result.getContent().stream()
                .map(ClientRestMapper::fromDomain).toList();

        PagedResponse<ClientResponse> response = new PagedResponse<>(
                clients,
                result.getCurrentPage(),
                result.getTotalPages(),
                result.getTotalElements(),
                result.isHasNext()
        );

        return ResponseEntity.ok(response);


    }
}
