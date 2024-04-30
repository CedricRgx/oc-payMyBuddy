package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.ConnectionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConnectionServiceIntegrationTest {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testFindAllConnections() {
        // Given
        User friend = User.builder()
                .firstname("Jane")
                .lastname("Doe")
                .build();
        friend = userRepository.save(friend);

        List<User> friends = new ArrayList<>();
        friends.add(friend);

        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .phone("1234567890")
                .address("123 Main St")
                .friends(friends)
                .build();

        user = userRepository.save(user);
        // When
        List<User> connections = connectionService.findAllConnections(user.getUserId());

        // Then
        assertNotNull(connections);
    }

}