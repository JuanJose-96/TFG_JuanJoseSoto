package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository;

import com.juanjose.backendfastfix.application.port.out.ReviewRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Review;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ReviewEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper.ReviewPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepositoryAdapter implements ReviewRepositoryPort {
    private final JpaReviewRepository jpaReviewRepository;

    public ReviewRepositoryAdapter(JpaReviewRepository jpaReviewRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
    }

    @Override
    public Review save(Review review) {
        ReviewEntity reviewSaved = jpaReviewRepository.save(ReviewPersistenceMapper.toEntity(review));
        return ReviewPersistenceMapper.toDomain(reviewSaved);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaReviewRepository.findById(id)
                .map(ReviewPersistenceMapper::toDomain);
    }

    @Override
    public List<Review> findByTechnicianId(Long technicianId) {
        return jpaReviewRepository.findByTechnicianEntity_Id(technicianId).stream()
                .map(ReviewPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Review> findByClientId(Long clientId) {
        return jpaReviewRepository.findByClientEntity_Id(clientId).stream()
                .map(ReviewPersistenceMapper::toDomain).toList();
    }

    @Override
    public boolean existsByClientAndTechnicianId(Long clientId, Long technicianId) {
        return jpaReviewRepository.existsByClientEntity_IdAndTechnicianEntity_Id(clientId,technicianId);
    }


}
