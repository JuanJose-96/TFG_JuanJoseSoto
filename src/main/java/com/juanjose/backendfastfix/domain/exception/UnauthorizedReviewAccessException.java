package com.juanjose.backendfastfix.domain.exception;

public class UnauthorizedReviewAccessException extends DomainException {
    public UnauthorizedReviewAccessException() {
        super("You can only edit your own reviews");
    }
}
