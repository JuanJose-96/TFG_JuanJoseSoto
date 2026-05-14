package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper;


import com.juanjose.backendfastfix.domain.model.Review;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review.ReviewResponse;

public class ReviewRestMapper {

    public static ReviewResponse fromDomain(Review review){
        return  new ReviewResponse(
                review.getId(),
                review.getClientId(),
                review.getTechnicianId(),
                review.getRating(),
                review.getComment(),
                review.getTechnicianReply(),
                review.getCreatedAt(),
                review.getTechnicianReplyDate()
        );
    }
}
