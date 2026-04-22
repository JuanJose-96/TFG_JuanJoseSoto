package com.juanjose.backendfastfix.application.port.in.client;

import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.web.multipart.MultipartFile;

public interface UploadClientImageUseCase {
    Client uploadProfileImage(Long id, MultipartFile file);
}
