package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        Long clientId,
        Long technicianId,
        int rating,
        String comment,
        String technicianReply,
        LocalDateTime createdAt,
        LocalDateTime technicianReplyDate
) {
}
