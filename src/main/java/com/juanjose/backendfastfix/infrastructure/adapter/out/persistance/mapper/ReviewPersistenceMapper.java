package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper;

import com.juanjose.backendfastfix.domain.model.Review;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ReviewEntity;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;

public class ReviewPersistenceMapper {
    public static ReviewEntity toEntity(Review review){
        ClientEntity client = new ClientEntity();
        client.setId(review.getClientId());

        TechnicianEntity technician = new TechnicianEntity();
        technician.setId(review.getTechnicianId());

        return ReviewEntity.builder()
                .id(review.getId())
                .clientEntity(client)
                .technicianEntity(technician)
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .technicianReply(review.getTechnicianReply())
                .technicianReplyDate(review.getTechnicianReplyDate()).build();

    }

    public static Review toDomain (ReviewEntity reviewEntity){
        return Review.builder()
                .id(reviewEntity.getId())
                .clientId(reviewEntity.getClientEntity().getId())
                .technicianId(reviewEntity.getTechnicianEntity().getId())
                .rating(reviewEntity.getRating())
                .comment(reviewEntity.getComment())
                .createdAt(reviewEntity.getCreatedAt())
                .technicianReply(reviewEntity.getTechnicianReply())
                .technicianReplyDate(reviewEntity.getTechnicianReplyDate())
                .build();
    }
}
