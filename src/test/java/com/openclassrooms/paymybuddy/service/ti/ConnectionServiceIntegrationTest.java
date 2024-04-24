package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.ConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConnectionServiceIntegrationTest {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        // Prepare your database here, clean and insert necessary test data
    }

    @Test
    public void testFindAllConnections() {
        // Assuming you've inserted test data for users and their connections
        Long userId = 1L; // Example user ID
        List<User> connections = connectionService.findAllConnections(userId);
        assertFalse(connections.isEmpty(), "Should retrieve connections");
    }

    @Test
    public void testAddConnection() {
        Long userId = 1L;
        Long friendId = 2L;
        assertTrue(connectionService.addConnection(userId, friendId), "Connection should be added successfully");
    }

    @Test
    public void testRemoveConnection() {
        Long userId = 1L;
        Long friendId = 2L;
        connectionService.addConnection(userId, friendId); // First, ensure there is a connection
        assertTrue(connectionService.removeConnection(userId, friendId), "Connection should be removed successfully");
    }
}
