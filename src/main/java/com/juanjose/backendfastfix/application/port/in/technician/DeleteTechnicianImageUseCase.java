package com.juanjose.backendfastfix.application.port.in.technician;

import com.juanjose.backendfastfix.domain.model.Technician;

public interface DeleteTechnicianImageUseCase {
    Technician deleteProfileImage(Long id);
}
