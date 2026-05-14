package com.juanjose.backendfastfix.application.port.out;

import com.juanjose.backendfastfix.domain.model.Work;

import java.util.List;
import java.util.Optional;

public interface WorkRepositoryPort {
    Work save(Work work);
    Optional<Work> findById(Long workId);
    List<Work> findByTechnicianId(Long technicianId);
    void deleteById(Long id);

}
