package com.juanjose.backendfastfix.domain.exception;

public class EmailAlreadyExists extends DomainException {
    public EmailAlreadyExists(String email) {
        super("Email already registered: " + email);
    }
}
