package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<ReviewEntity,Long> {
    List<ReviewEntity> findByTechnicianEntity_Id(Long technicianId);
    List<ReviewEntity> findByClientEntity_Id(Long clientId);
    boolean existsByClientEntity_IdAndTechnicianEntity_Id(Long clientId, Long TechnicianId);
}
