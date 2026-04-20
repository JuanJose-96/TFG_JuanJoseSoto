package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.GetSectorsUseCase;
import com.juanjose.backendfastfix.application.port.out.SectorRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Sector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService implements GetSectorsUseCase {
    private final SectorRepositoryPort sectorRepositoryPort;

    public SectorService(SectorRepositoryPort sectorRepositoryPort) {
        this.sectorRepositoryPort = sectorRepositoryPort;
    }

    @Override
    public List<Sector> getAllSectors() {
        return sectorRepositoryPort.findAll();
    }
}
