package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sectors")
public class SectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    String name;
    @OneToMany(mappedBy = "sectorEntity" )
    private List<TechnicianEntity> technicians = new ArrayList<>();






}
