package com.juanjose.backendfastfix.domain.exception;

public class ClientNotFoundException extends DomainException {
    public ClientNotFoundException(String email) {
        super("Client not found with email " + email);
    }
    public ClientNotFoundException(Long id){
        super("Client not found with id " + id);
    }
}
