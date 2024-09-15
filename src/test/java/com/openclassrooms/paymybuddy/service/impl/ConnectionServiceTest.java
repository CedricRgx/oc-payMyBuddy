package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ConnectionServiceTest {

    @InjectMocks
    private ConnectionService connectionService;

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @Mock
    private User userFriend;

    @Mock
    private List<User> mockedFriendsList;

    @Test
    public void addConnection_ShouldReturnTrueWhenSuccessful() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(userService.getUserById(friendId)).thenReturn(Optional.of(userFriend));
        when(user.getFriends()).thenReturn(new ArrayList<>());
        when(userService.addUser(user)).thenReturn(user);

        // When
        boolean result = connectionService.addConnection(userId, friendId);

        // Then
        assertTrue(result);
        verify(userService).addUser(user);
        assertTrue(user.getFriends().contains(userFriend));
    }

    @Test
    public void addConnection_ShouldReturnFalseWhenAdditionFails() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;

        // When
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(userService.getUserById(friendId)).thenReturn(Optional.of(userFriend));
        when(user.getFriends()).thenReturn(mockedFriendsList);
        when(mockedFriendsList.add(any(User.class))).thenReturn(false);
        boolean result = connectionService.addConnection(userId, friendId);

        // Then
        assertFalse(result);
        verify(userService, never()).addUser(user);
    }

    @Test
    public void addConnection_ShouldReturnFalseWhenUserNotFound() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // When
        boolean result = connectionService.addConnection(userId, friendId);

        // Then
        assertFalse(result);
        verify(userService, never()).addUser(any(User.class));
    }

    @Test
    public void removeConnection_ShouldReturnTrueWhenSuccessful() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;

        User friend = new User();
        friend.setUserId(friendId);

        List<User> friendsList = new ArrayList<>();
        friendsList.add(friend);

        User user = new User();
        user.setUserId(userId);
        user.setFriends(friendsList);

        // When
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));
        boolean result = connectionService.removeConnection(userId, friendId);

        // Then
        assertTrue(result);
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void removeConnection_ShouldReturnFalseWhenNoMatchFound() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        Long nonExistingFriendId = 3L;

        User friend = new User();
        friend.setUserId(friendId);

        List<User> friendsList = new ArrayList<>();
        friendsList.add(friend);

        User user = new User();
        user.setUserId(userId);
        user.setFriends(friendsList);

        // When
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));
        boolean result = connectionService.removeConnection(userId, nonExistingFriendId);

        // Then
        assertFalse(result);
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void removeConnection_ShouldReturnFalseWhenUserNotFound() {
        // Given
        Long userId = 1L;
        Long friendId = 2L;
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        // When
        boolean result = connectionService.removeConnection(userId, friendId);

        // Then
        assertFalse(result);
        verify(userService, times(1)).getUserById(userId);
    }

}