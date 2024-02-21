package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;

@Slf4j
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        log.info("Running testGetUsers() test in UserServiceTest class");
        userService.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    public void testGetUserByIdFound() {
        log.info("Running testGetUserByIdFound() test in UserServiceTest class");
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        userService.getUserById(userId);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testGetUserByIdNotFound() {
        log.info("Running testGetUserByIdNotFound() test in UserServiceTest class");
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        userService.getUserById(userId);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testAddUser() {
        log.info("Running testAddUser() test in UserServiceTest class");
        User user = new User();
        userService.addUser(user);
        verify(userRepository).save(user);
    }

    @Test
    public void testDeleteUserById() {
        log.info("Running testDeleteUserById() test in UserServiceTest class");
        Long userId = 1L;
        userService.deleteUserById(userId);
        verify(userRepository).deleteById(userId);
    }
}
