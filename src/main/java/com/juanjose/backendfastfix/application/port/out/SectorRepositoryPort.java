package com.juanjose.backendfastfix.application.port.out;

import com.juanjose.backendfastfix.domain.model.Sector;

import java.util.List;
import java.util.Optional;

public interface SectorRepositoryPort {
    List<Sector> findAll();
    Optional<Sector> findById(Long id);
    boolean exitsById(Long id);
}
