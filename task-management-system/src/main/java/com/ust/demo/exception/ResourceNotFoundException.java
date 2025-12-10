package com.ust.demo.exception;

// Custom exception to handle "resource not found" errors
public class ResourceNotFoundException extends RuntimeException {

    // Constructor that passes the message to the superclass (RuntimeException)
    public ResourceNotFoundException(String message) {
        super(message);  // Sets the exception message
    }
}
