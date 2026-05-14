package com.juanjose.backendfastfix.application.port.in.review;

public interface DeleteReviewUseCase {
    void delete(Long reviewId, Long clientId);
}
