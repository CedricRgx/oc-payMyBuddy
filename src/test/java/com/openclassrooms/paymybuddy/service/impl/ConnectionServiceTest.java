package com.openclassrooms.paymybuddy.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ConnectionServiceTest {

    @InjectMocks
    private ConnectionService connectionService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    void addConnection_ShouldReturnTrueWhenSuccessful() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        String sql = "INSERT INTO assoc_user_friend (user_id, friend_id) VALUES (?, ?)";


        // When
        when(jdbcTemplate.update(eq(sql), anyLong(), anyLong())).thenReturn(1);
        boolean result = connectionService.addConnection(userId, friendId);

        // Then
        assertTrue(result);
    }

    @Test
    void addConnection_ShouldReturnFalseWhenUpdateFails() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        String sql = "INSERT INTO assoc_user_friend (user_id, friend_id) VALUES (?, ?)";
        when(jdbcTemplate.update(eq(sql), anyLong(), anyLong())).thenReturn(0);

        // When
        boolean result = connectionService.addConnection(userId, friendId);

        // Then
        assertFalse(result);
    }

    @Test
    void removeConnection_ShouldReturnTrueWhenSuccessful() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        String sql = "DELETE FROM assoc_user_friend WHERE user_id = ? AND friend_id = ?";
        when(jdbcTemplate.update(eq(sql), anyLong(), anyLong())).thenReturn(1);

        // When
        boolean result = connectionService.removeConnection(userId, friendId);

        // Then
        assertTrue(result);
    }

    @Test
    void removeConnection_ShouldReturnFalseWhenUpdateFails() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        String sql = "DELETE FROM assoc_user_friend WHERE user_id = ? AND friend_id = ?";
        when(jdbcTemplate.update(eq(sql), anyLong(), anyLong())).thenReturn(0);

        // When
        boolean result = connectionService.removeConnection(userId, friendId);

        // Then
        assertFalse(result);
    }
}