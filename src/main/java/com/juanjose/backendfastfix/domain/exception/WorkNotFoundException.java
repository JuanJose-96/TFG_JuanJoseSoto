package com.juanjose.backendfastfix.domain.exception;

public class WorkNotFoundException extends DomainException {
    public WorkNotFoundException(Long id) {
        super("Work not found with id " + id);
    }
}
