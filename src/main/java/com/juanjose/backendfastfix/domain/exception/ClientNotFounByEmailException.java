package com.juanjose.backendfastfix.domain.exception;

public class ClientNotFounByEmailException extends DomainException {
    public ClientNotFounByEmailException(String email) {
        super("Client not found with email " + email);
    }
}
