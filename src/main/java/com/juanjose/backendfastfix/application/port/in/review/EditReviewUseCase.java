package com.juanjose.backendfastfix.application.port.in.review;

import com.juanjose.backendfastfix.domain.model.Review;

public interface EditReviewUseCase {
    Review edit(Long reviewId, Long clientId, Integer newRating, String newComment);
}
