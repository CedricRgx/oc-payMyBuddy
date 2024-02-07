package com.openclassrooms.paymybuddy.Service.Impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;

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
        userService.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    public void testGetUserByIdFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        userService.getUserById(userId);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testGetUserByIdNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        userService.getUserById(userId);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        userService.addUser(user);
        verify(userRepository).save(user);
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;
        userService.deleteUserById(userId);
        verify(userRepository).deleteById(userId);
    }
}
