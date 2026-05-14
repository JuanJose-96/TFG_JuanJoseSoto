package com.juanjose.backendfastfix.domain.exception;

public class ReviewNotFoundException extends DomainException {
    public ReviewNotFoundException(Long id) {
        super("Review not found with id: " + id);
    }
}
