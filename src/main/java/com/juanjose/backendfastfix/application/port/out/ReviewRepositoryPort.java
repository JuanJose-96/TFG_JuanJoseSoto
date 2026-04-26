package com.juanjose.backendfastfix.application.port.out;

import com.juanjose.backendfastfix.domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryPort {
    Review save (Review review);
    Optional<Review> findById(Long id);
    List<Review> findByTechnicianId(Long technicianId);
    List<Review> findByClientId(Long clientId);
    boolean existsByClientAndTechnicianId(Long clientId, Long TechnicianId);
}
