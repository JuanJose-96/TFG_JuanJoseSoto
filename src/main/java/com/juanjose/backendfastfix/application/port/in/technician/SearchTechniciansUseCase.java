package com.juanjose.backendfastfix.application.port.in.technician;

import com.juanjose.backendfastfix.domain.model.Technician;

import java.util.List;

public interface SearchTechniciansUseCase {
    List<Technician> searchTechnicians(Long sectorId, String province, String city, Double rating);
}
