package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.technician;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTechnicianRepository extends JpaRepository<TechnicianEntity,Long> {
    boolean existsByEmail(String email);
    Optional<TechnicianEntity> findByEmail(String email);
}
