package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.client;

import com.juanjose.backendfastfix.application.port.in.client.DeleteClientImageUseCase;
import com.juanjose.backendfastfix.application.port.in.client.UpdateClientProfileUseCase;
import com.juanjose.backendfastfix.application.port.in.client.UploadClientImageUseCase;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.client.UpdateClientProfileRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ClientRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/client/profile")
public class ClientProfileController {
    private final UpdateClientProfileUseCase updateClientProfileUseCase;
    private final UploadClientImageUseCase uploadClientImageUseCase;
    private final DeleteClientImageUseCase deleteClientImageUseCase;

    public ClientProfileController(UpdateClientProfileUseCase updateClientProfileUseCase, UploadClientImageUseCase uploadClientImageUseCase, DeleteClientImageUseCase deleteClientImageUseCase) {
        this.updateClientProfileUseCase = updateClientProfileUseCase;
        this.uploadClientImageUseCase = uploadClientImageUseCase;
        this.deleteClientImageUseCase = deleteClientImageUseCase;
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
                                request.city(),
                                request.whatsappAvailable())));

    }

    @PostMapping("/{id}/image")
    public ResponseEntity<ClientResponse> uploadImage(@PathVariable Long id,
                                                      @RequestParam("clientImage") MultipartFile file){
        return ResponseEntity.ok(ClientRestMapper.fromDomain(
                uploadClientImageUseCase.uploadProfileImage(id,file)
        ));

    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<ClientResponse> deleteImage(@PathVariable Long id){
        Client client = deleteClientImageUseCase.deleteProfileImage(id);

        return ResponseEntity.ok(ClientRestMapper.fromDomain(client));

    }
}
