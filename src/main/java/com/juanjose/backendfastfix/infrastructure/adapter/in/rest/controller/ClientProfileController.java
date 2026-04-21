package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller;

import com.juanjose.backendfastfix.application.port.in.UpdateClientProfileUseCase;
import com.juanjose.backendfastfix.application.port.in.UploadClientImageUseCase;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.ClientResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.UpdateClientProfileRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ClientRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/client/profile")
public class ClientProfileController {
    private final UpdateClientProfileUseCase updateClientProfileUseCase;
    private final UploadClientImageUseCase uploadClientImageUseCase;

    public ClientProfileController(UpdateClientProfileUseCase updateClientProfileUseCase, UploadClientImageUseCase uploadClientImageUseCase) {
        this.updateClientProfileUseCase = updateClientProfileUseCase;
        this.uploadClientImageUseCase = uploadClientImageUseCase;
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
                                                      @RequestParam("profileImage") MultipartFile file){
        return ResponseEntity.ok(ClientRestMapper.fromDomain(
                uploadClientImageUseCase.uploadProfileImage(id,file)
        ));

    }
}
