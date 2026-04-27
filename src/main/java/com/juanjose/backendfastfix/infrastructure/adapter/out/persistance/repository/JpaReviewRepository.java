package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<ReviewEntity,Long> {
    List<ReviewEntity> findByTechnicianId(Long technicianId);
    List<ReviewEntity> findByClientId(Long clientId);
    boolean existsByClientIdAndTechnicianId(Long clientId, Long TechnicianId);
}
