package com.example.carrentalproject.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException() {
        super("Department not found");
    }

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
