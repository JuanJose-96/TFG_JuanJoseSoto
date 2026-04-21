package com.juanjose.backendfastfix.domain.exception;

public class TechnicianNotFoundException extends DomainException {
    public TechnicianNotFoundException(Long id) {
        super("Technician not found with id " + id);
    }
    public TechnicianNotFoundException(String email) {
        super("Technician not found with email " + email);
    }
}
