package com.ust.demo.exception;

// Custom exception to handle validation errors
public class ValidationException extends RuntimeException {

    // Constructor that passes the message to the superclass (RuntimeException)
    public ValidationException(String message) {
        super(message);  // Sets the exception message
    }
}
