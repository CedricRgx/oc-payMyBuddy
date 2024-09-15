package com.openclassrooms.paymybuddy.config;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testLoadUserByUsername_UserFoundAndActive_ReturnsUserDetails() {
        // Given
        String email = "user@example.com";
        User mockUser = User.builder()
                .email(email)
                .password("password")
                .isActive(true)
                .role("USER")
                .build();
        mockUser.setUserId(1L);

        // When
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        UserDetails result = customUserDetailsService.loadUserByUsername(email);

        // Then
        assertNotNull(result);
        assertEquals(email, result.getUsername());
        assertTrue(result.isEnabled());
    }

    @Test
    void testLoadUserByUsername_UserFoundButNotActive_ReturnsDisabledUser() {
        // Given
        String email = "user@example.com";
        User mockUser = User.builder()
                .email(email)
                .password("password")
                .isActive(false)
                .role("USER")
                .build();
        mockUser.setUserId(1L);

        // When
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));
        UserDetails result = customUserDetailsService.loadUserByUsername(email);

        // Then
        assertNotNull(result);
        assertFalse(result.isEnabled());
    }

    @Test
    void testLoadUserByUsername_UserActive_UpdatesLastConnection() {
        // Given
        String email = "user@example.com";
        User mockUser = User.builder()
                .email(email)
                .password("password")
                .isActive(true)
                .role("USER")
                .build();
        mockUser.setUserId(1L);

        // When
        when(userService.findByEmail(email)).thenReturn(Optional.of(mockUser));

        UserDetails result = customUserDetailsService.loadUserByUsername(email);

        // Then
        verify(userService).updateLastConnectionDate(mockUser.getUserId());
    }
}
