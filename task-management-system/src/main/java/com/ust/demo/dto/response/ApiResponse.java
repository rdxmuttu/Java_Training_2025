package com.ust.demo.dto.response;

import lombok.*;

// Generic API response wrapper class to standardize responses across the application
@Getter @Setter  // Lombok to automatically generate getter and setter methods
@NoArgsConstructor @AllArgsConstructor  // Lombok annotations for default constructor and all-args constructor
@Builder  // Lombok Builder pattern for creating ApiResponse objects
public class ApiResponse<T> {

    private boolean success;  // Indicates if the request was successful (true or false)
    private String message;   // The message that provides additional information (e.g., error or success message)
    private T data;           // The actual data returned in the response (could be any type)
}
