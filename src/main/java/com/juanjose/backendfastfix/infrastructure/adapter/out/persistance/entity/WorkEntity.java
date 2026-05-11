package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "works")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "technician_id",nullable = false)
    private TechnicianEntity technician;

    @Column(nullable = false)
    private Long clientId;

    @Column(nullable = false)
    private String clientName;

    private String clientSurname;

    @Column(nullable = false)
    private String clientProvince;
    @Column(nullable = false)
    private String clientCity;
    @Column(nullable = false)
    private LocalDate serviceDate;
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String serviceDescription;

}
