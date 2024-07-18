package com.example.carrentalproject.exception;

public class OpinionNotFoundException extends RuntimeException {
    public OpinionNotFoundException() {
        super("Department not found");
    }

    public OpinionNotFoundException(String message) {
        super(message);
    }
}
