package com.juanjose.backendfastfix.application.port.in;

import com.juanjose.backendfastfix.domain.model.Sector;

import java.util.List;

public interface GetSectorsUseCase {
    List<Sector> getAllSectors();
}
