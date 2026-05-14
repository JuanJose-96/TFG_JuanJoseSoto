package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.technician;

import com.juanjose.backendfastfix.application.PagedResult;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.TechnicianPersistenceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PagedResult<Technician> searchTechnicians(Long sectorId, String province, String city, Double rating, int page) {
        Pageable pageable;
        if(rating != null){
            pageable = PageRequest.of(page,20, Sort.by(Sort.Order.desc("averageRating")));
        }else{
            pageable = PageRequest.of(page,20, Sort.by(Sort.Order.desc("id")));
        }

        Page<TechnicianEntity> result = jpaTechnicianRepository.searchTechnicians(sectorId,province,city,rating,pageable);

        List<Technician> technicians = result.getContent().stream()
                .map(TechnicianPersistenceMapper::toDomain).toList();

        return new PagedResult<>(technicians,
                result.getNumber(),
                result.getTotalPages(),
                result.getTotalElements(),
                result.hasNext());
    }


}
