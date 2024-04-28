package com.openclassrooms.paymybuddy.config;

import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserByUsername_UserFoundAndActive_ReturnsUserDetails() {
        // Given
        UserAccount userAccount = UserAccount.builder()
                .email("email@example.com")
                .password("password")
                .isActive(true)
                .role("USER")
                .build();
        when(userAccountRepository.findByEmail("email@example.com")).thenReturn(userAccount);

        // When
        UserDetails result = customUserDetailsService.loadUserByUsername("email@example.com");

        // Then
        assertNotNull(result);
        assertEquals("email@example.com", result.getUsername());
        assertTrue(result.isEnabled());
    }

    @Test
    void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        // Given
        when(userAccountRepository.findByEmail("email@example.com")).thenReturn(null);

        // When Then
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("email@example.com"));
    }

    @Test
    void loadUserByUsername_UserFoundButNotActive_ReturnsDisabledUser() {
        // Given
        UserAccount userAccount = UserAccount.builder()
                .email("email@example.com")
                .password("password")
                .isActive(false)
                .role("USER")
                .build();
        when(userAccountRepository.findByEmail("email@example.com")).thenReturn(userAccount);

        // When
        UserDetails result = customUserDetailsService.loadUserByUsername("email@example.com");

        // Then
        assertNotNull(result);
        assertFalse(result.isEnabled());
    }

    @Test
    void loadUserByUsername_UserActive_UpdatesLastConnection() {
        // Given
        UserAccount userAccount = UserAccount.builder()
                .email("email@example.com")
                .password("password")
                .isActive(true)
                .role("USER")
                .build();
        when(userAccountRepository.findByEmail("email@example.com")).thenReturn(userAccount);

        // When
        UserDetails result = customUserDetailsService.loadUserByUsername("email@example.com");

        // Then
        verify(userAccountService).updateLastConnectionDate(userAccount.getUserAccountId());
    }
}
