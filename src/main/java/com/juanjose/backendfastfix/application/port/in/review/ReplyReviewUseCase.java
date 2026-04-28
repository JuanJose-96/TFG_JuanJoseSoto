package com.juanjose.backendfastfix.application.port.in.review;

import com.juanjose.backendfastfix.domain.model.Review;

public interface ReplyReviewUseCase {
    Review reply(Long reviewId, Long technicianId, String technicianReply);
}
