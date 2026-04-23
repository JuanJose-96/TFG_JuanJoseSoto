package com.juanjose.backendfastfix.application.port.in.technician;

import com.juanjose.backendfastfix.domain.model.Technician;

public interface UpdateTechnicianProfileUseCase {
    Technician updateProfile(Long id, Technician technician);
}
