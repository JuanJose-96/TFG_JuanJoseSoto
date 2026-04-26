package com.juanjose.backendfastfix.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Review {
    private Long id;
    private Long clientId;
    private Long technicianId;
    private int rating;
    private String comment;
    private String technicianReply;
    private LocalDateTime createdAt;
    private LocalDateTime technicianReplyDate;


}
