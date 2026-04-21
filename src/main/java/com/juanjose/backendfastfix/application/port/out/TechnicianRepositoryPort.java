package com.juanjose.backendfastfix.application.port.out;

import com.juanjose.backendfastfix.domain.model.Technician;

import java.util.Optional;

public interface TechnicianRepositoryPort {
    Technician save(Technician technician);
    boolean existsByEmail(String email);
    Optional<Technician> findByEmail(String email);
    Optional<Technician> findById (Long id);
}
