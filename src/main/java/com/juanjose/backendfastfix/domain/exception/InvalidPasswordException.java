package com.juanjose.backendfastfix.domain.exception;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException() {
        super("Invalid password");
    }
}
