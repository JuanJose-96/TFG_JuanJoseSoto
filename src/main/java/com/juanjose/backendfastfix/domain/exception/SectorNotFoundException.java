package com.juanjose.backendfastfix.domain.exception;

public class SectorNotFoundException extends DomainException {
    public SectorNotFoundException(Long id) {
        super("Sector not found with id " + id);
    }
}
