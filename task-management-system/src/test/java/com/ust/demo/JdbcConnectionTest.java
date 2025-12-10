package com.ust.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JdbcConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcConnection() {
        // Try executing a simple query to check if the connection works
        String sql = "SELECT 1";

        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);

        // Assert that the query executed successfully
        assertNotNull(result);
        System.out.println("Connection is successful! Result: " + result);
    }
}
