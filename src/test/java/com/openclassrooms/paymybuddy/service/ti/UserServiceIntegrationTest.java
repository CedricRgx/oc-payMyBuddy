package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = User.builder()
                .firstname("John")
                .lastname("Doe").build();
        user = userService.addUser(user);

        assertNotNull(user.getUserId());
        assertEquals("John", user.getFirstname());
    }

    @Test
    public void testGetUserById() {
        // Given
        User user = User.builder()
                .firstname("Alice")
                .lastname("Wonderland").build();

        // When
        user = userService.addUser(user);
        Optional<User> foundUser = userService.getUserById(user.getUserId());

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("Alice", foundUser.get().getFirstname());
    }

}