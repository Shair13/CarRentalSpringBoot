package com.example.carrentalproject.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException() {
        super("Car not found");
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
