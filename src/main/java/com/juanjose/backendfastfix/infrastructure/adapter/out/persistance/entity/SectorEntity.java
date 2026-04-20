package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sectors")
public class SectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    String name;

    public SectorEntity(){
    }

    public SectorEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
