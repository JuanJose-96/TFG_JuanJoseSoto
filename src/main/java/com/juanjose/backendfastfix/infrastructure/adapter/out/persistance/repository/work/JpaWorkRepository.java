package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.work;


import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWorkRepository extends JpaRepository<WorkEntity,Long> {

    List<WorkEntity>findByTechnician_Id(Long technicianId);
}
