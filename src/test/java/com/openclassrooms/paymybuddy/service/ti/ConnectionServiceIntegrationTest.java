package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.ConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConnectionServiceIntegrationTest {

    @Autowired
    private ConnectionService connectionService;

    @Test
    public void testFindAllConnections() {
        // Given
        Long userId = 1L;

        // When
        List<User> connections = connectionService.findAllConnections(userId);

        // Then
        assertFalse(connections.isEmpty());
    }

}