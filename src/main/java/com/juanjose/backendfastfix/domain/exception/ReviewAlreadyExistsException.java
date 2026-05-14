package com.juanjose.backendfastfix.domain.exception;

public class ReviewAlreadyExistsException extends DomainException {
    public ReviewAlreadyExistsException() {
        super("You have already reviewed this technician");
    }
}
