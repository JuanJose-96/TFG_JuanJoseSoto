package com.juanjose.backendfastfix.application.port.in.review;

import com.juanjose.backendfastfix.domain.model.Review;

public interface PublishReviewUseCase {
    Review publish (Long clientId, Long technicianId, int rating, String comment);
}
