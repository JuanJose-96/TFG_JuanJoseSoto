package com.juanjose.backendfastfix.application.port.in.review;

import com.juanjose.backendfastfix.domain.model.Review;

import java.util.List;

public interface GetTechnicianReviewsUseCase {
    List<Review> getReviewsByTechnicianId(Long technicianId);
}
