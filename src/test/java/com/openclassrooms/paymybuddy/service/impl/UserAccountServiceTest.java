package com.openclassrooms.paymybuddy.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.paymybuddy.exceptions.UpdateLastConnectionDateFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserAccountServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserAccountService userAccountService;

    @Test
    public void testIsEmailUnique_True() {
        // Given
        String email = "test@example.com";

        // When
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(0);

        // Then
        assertTrue(userAccountService.isEmailUnique(email));
    }

    @Test
    public void testIsEmailUnique_False() {
        // Given
        String email = "test@example.com";

        // When
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);

        // Then
        assertFalse(userAccountService.isEmailUnique(email));
    }

    @Test
    public void testUpdateLastConnectionDate_Success() {
        // Given
        Long userAccountId = 1L;

        // When
        when(jdbcTemplate.update(anyString(), eq(userAccountId))).thenReturn(1);

        // Then
        assertDoesNotThrow(() -> userAccountService.updateLastConnectionDate(userAccountId));
    }

    @Test
    public void testUpdateLastConnectionDate_Failure() {
        // Given
        Long userAccountId = 1L;

        // When
        when(jdbcTemplate.update(anyString(), eq(userAccountId))).thenReturn(0);

        // Then
        assertThrows(UpdateLastConnectionDateFailedException.class, () -> userAccountService.updateLastConnectionDate(userAccountId));
    }

    @Test
    public void testSavePassword_Success() {
        // Given
        String password = "newPassword";
        String email = "test@example.com";

        // When
        when(jdbcTemplate.update(anyString(), eq(password), eq(email))).thenReturn(1);

        // Then
        assertTrue(userAccountService.savePassword(password, email));
    }

    @Test
    public void testSavePassword_Failure() {
        // Given
        String password = "newPassword";
        String email = "test@example.com";

        // When
        when(jdbcTemplate.update(anyString(), eq(password), eq(email))).thenReturn(0);

        // Then
        assertFalse(userAccountService.savePassword(password, email));
    }
}
