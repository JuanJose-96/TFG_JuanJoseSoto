package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name= "technicians")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    private String profileImageUrl;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String city;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    @ManyToOne
    @JoinColumn(name = "main_sector_id", nullable = false)
    private SectorEntity sectorEntity;

    private String priceDescription;

    @Column(nullable = false)
    private boolean emergencyAvailability;

    private String scheduleAvailability;

    @Column(nullable = false)
    private boolean whatsappAvailable;

    @Column(nullable = false)
    private Double averageRating;

    @Column(nullable = false)
    private Integer totalReviews;

    @OneToMany(mappedBy = "technicianEntity")
    private List<ReviewEntity> reviews = new ArrayList<>();
}
