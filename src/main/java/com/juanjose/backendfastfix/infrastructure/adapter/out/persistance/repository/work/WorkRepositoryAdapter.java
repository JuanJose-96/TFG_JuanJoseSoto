package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.work;

import com.juanjose.backendfastfix.application.port.out.WorkRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Work;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.WorkEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.WorkPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkRepositoryAdapter implements WorkRepositoryPort {
    private final JpaWorkRepository jpaWorkRepository;


    public WorkRepositoryAdapter(JpaWorkRepository jpaWorkRepository) {
        this.jpaWorkRepository = jpaWorkRepository;
    }


    @Override
    public Work save(Work work) {
        WorkEntity workSaved = jpaWorkRepository
                .save(WorkPersistenceMapper.toEntity(work));

        return WorkPersistenceMapper.toDomain(workSaved);
    }

    @Override
    public Optional<Work> findById(Long workId) {
        return jpaWorkRepository.findById(workId)
                .map(WorkPersistenceMapper::toDomain);
    }

    @Override
    public List<Work> findByTechnicianId(Long technicianId) {
        return jpaWorkRepository.findAll()
                .stream()
                .map(WorkPersistenceMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaWorkRepository.deleteById(id);

    }
}
