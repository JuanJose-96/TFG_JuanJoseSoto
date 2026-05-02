package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.technician;

import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.TechnicianPersistenceMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<Technician> searchTechnicians(Long sectorId, String province, String city, Double rating) {
        Pageable pageable = PageRequest.of(0,20);

        return jpaTechnicianRepository.searchTechnicians(sectorId,province,city,rating,pageable)
                .stream().map(TechnicianPersistenceMapper::toDomain).toList();
    }
}
