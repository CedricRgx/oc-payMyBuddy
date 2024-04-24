package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.RegisterService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterService registerService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user = userService.addUser(user);

        Assertions.assertNotNull(user.getUserId(), "The saved user should have a non-null ID.");
        Assertions.assertEquals("John", user.getFirstname(), "The saved user should have the name 'John'.");
    }

    @Test
    public void testDeleteUserById() {
        User user = new User();
        user.setFirstname("Jane");
        user.setLastname("Doe");
        user = userRepository.save(user);

        userService.deleteUserById(user.getUserId());
        Optional<User> deletedUser = userRepository.findById(user.getUserId());

        Assertions.assertTrue(deletedUser.isEmpty(), "The user should have been deleted.");
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setFirstname("Alice");
        user.setLastname("Wonderland");
        user = userRepository.save(user);

        Optional<User> foundUser = userService.getUserById(user.getUserId());
        Assertions.assertTrue(foundUser.isPresent(), "User should be found with the given ID.");
        Assertions.assertEquals("Alice", foundUser.get().getFirstname(), "The user's name should be Alice.");
    }

}