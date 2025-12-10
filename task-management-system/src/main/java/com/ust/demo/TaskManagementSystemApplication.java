package com.ust.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
// Automatically scans packages under com.example.tms.*
// Starts embedded Tomcat server and loads Spring context
@SpringBootApplication
public class TaskManagementSystemApplication {

    public static void main(String[] args) {
        // Bootstraps Spring Boot application
        SpringApplication.run(TaskManagementSystemApplication.class, args);
    }
}
