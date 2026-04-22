package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.technician;

import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.TechnicianPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TechnicianRepositoryAdapter implements TechnicianRepositoryPort {
    private final JpaTechnicianRepository jpaTechnicianRepository;

    public TechnicianRepositoryAdapter(JpaTechnicianRepository jpaTechnicianRepository) {
        this.jpaTechnicianRepository = jpaTechnicianRepository;
    }

    @Override
    public Technician save(Technician technician) {
        return TechnicianPersistenceMapper.toDomain(jpaTechnicianRepository.save(
                TechnicianPersistenceMapper.toEntity(technician)
        ));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaTechnicianRepository.existsByEmail(email);
    }

    @Override
    public Optional<Technician> findByEmail(String email) {
        return jpaTechnicianRepository.findByEmail(email).map(TechnicianPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Technician> findById(Long id) {
        return jpaTechnicianRepository.findById(id).map(TechnicianPersistenceMapper::toDomain);
    }
}
