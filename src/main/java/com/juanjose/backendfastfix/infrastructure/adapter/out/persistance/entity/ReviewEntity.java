package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    @ManyToOne
    @JoinColumn(name = "technician_id", nullable = false)
    private TechnicianEntity technicianEntity;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(columnDefinition = "TEXT")
    private String technicianReply;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime technicianReplyDate;
}
