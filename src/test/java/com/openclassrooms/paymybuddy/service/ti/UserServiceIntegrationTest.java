package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
    public void testDeleteUserById() {
        // Given
        User user = User.builder()
                .firstname("Jane")
                .lastname("Doe").build();

        // When
        user = userService.addUser(user);

        userService.deleteUserById(user.getUserId());
        Optional<User> deletedUser = userRepository.findById(user.getUserId());

        // Then
        assertTrue(deletedUser.isEmpty());
    }

    @Test
    public void testGetUserById() {
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