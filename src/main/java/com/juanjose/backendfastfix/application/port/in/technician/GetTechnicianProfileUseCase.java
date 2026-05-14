package com.juanjose.backendfastfix.application.port.in.technician;

import com.juanjose.backendfastfix.domain.model.Technician;

public interface GetTechnicianProfileUseCase {
    Technician getProfile(Long id);
}
