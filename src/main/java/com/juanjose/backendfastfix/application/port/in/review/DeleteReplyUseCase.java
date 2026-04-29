package com.juanjose.backendfastfix.application.port.in.review;

import com.juanjose.backendfastfix.domain.model.Review;

public interface DeleteReplyUseCase {
    Review deleteReply(Long reviewId, Long technicianId);
}
