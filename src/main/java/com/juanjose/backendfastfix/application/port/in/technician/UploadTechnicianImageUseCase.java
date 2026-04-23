package com.juanjose.backendfastfix.application.port.in.technician;

import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.web.multipart.MultipartFile;

public interface UploadTechnicianImageUseCase {
    Technician uploadProfileImage (Long id, MultipartFile file);
}
