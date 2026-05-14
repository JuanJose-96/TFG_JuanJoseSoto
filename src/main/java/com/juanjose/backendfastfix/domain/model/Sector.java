package com.juanjose.backendfastfix.domain.model;


public class Sector {
    private final Long id;
    private final String name;

    public Sector(Long id, String name) {
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
