package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.sector;

import com.juanjose.backendfastfix.application.port.out.SectorRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Sector;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.SectorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SectorRepositoryAdapter implements SectorRepositoryPort {
    private final JpaSectorRepository jpaSectorRepository;

    public SectorRepositoryAdapter(JpaSectorRepository jpaSectorRepository) {
        this.jpaSectorRepository = jpaSectorRepository;
    }

    @Override
    public List<Sector> findAll() {
        return jpaSectorRepository.findAll()
                .stream().map(SectorMapper::toDomain).toList();
    }

    @Override
    public Optional<Sector> findById(Long id) {
        return jpaSectorRepository.findById(id)
                .map(SectorMapper::toDomain);

    }

    @Override
    public boolean exitsById(Long id) {
        return jpaSectorRepository.existsById(id);
    }
}
