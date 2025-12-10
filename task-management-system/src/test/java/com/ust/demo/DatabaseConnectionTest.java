package com.ust.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @SpringBootTest loads entire Spring context
@SpringBootTest
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;   // Injects HikariCP datasource

    @Test
    void testConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);
            System.out.println("Connected to DB successfully!");
        }
    }
}
