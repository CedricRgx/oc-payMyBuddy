package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AppAccountRepository appAccountRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserService userService;

    @Test
    public void getUsers_shouldReturnAllUsers() {
        // Given
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        // When
        Iterable<User> result = userService.getUsers();

        // Then
        assertSame(userList, result);
    }

    @Test
    public void getUserById_shouldReturnUser_whenUserExists() {
        // Given
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertTrue(result.isPresent());
        assertSame(user, result.get());
    }

    @Test
    public void getUserById_shouldReturnEmptyOptional_whenUserDoesNotExist() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(empty());

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void addUser_shouldReturnAddedUser() {
        // Given
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // When
        User result = userService.addUser(user);

        // Then
        assertSame(user, result);
        verify(userRepository).save(user);
    }

    @Test
    public void deleteUserById_shouldDeleteUser() {
        // Given
        Long userId = 1L;

        // When
        userService.deleteUserById(userId);

        // Then
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void getUserIdByEmail_shouldReturnUserId() {
        // Given
        String email = "test@example.com";
        Long userId = 1L;
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Long.class))).thenReturn(userId);

        // When
        Long result = userService.getUserIdByEmail(email);

        // Then
        assertEquals(userId, result);
    }

    @Test
    public void getActiveFriends_shouldReturnListOfActiveFriends() {
        // Given
        List<User> listOfFriends = new ArrayList<>();
        User activeFriend = new User();
        activeFriend.setUserAccount(UserAccount.builder()
                .email("john.doe@example.com")
                .password("XXXX")
                .isActive(true)
                .lastConnectionDate(LocalDateTime.now())
                .role("USER").build());
        listOfFriends.add(activeFriend);
        User inactiveFriend = new User();
        inactiveFriend.setUserAccount(UserAccount.builder().isActive(false).build());
        listOfFriends.add(inactiveFriend);

        // When
        List<ConnectionDTO> result = userService.getActiveFriends(listOfFriends);

        // Then
        assertEquals(1, result.size());
        assertEquals(activeFriend.getUserId(), result.get(0).getUserId());
    }


    @Test
    public void getUserById_existingId_shouldReturnOptionalWithUser() {
        // Given
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertTrue(result.isPresent());
        assertSame(user, result.get());
    }

    @Test
    public void getUserById_nonExistingId_shouldReturnEmptyOptional() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(empty());

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    public void updateUserBalance_existingUserId_shouldUpdateBalanceAndReturnTrue() {
        // Given
        Long userId = 1L;
        Double newBalance = 100.0;
        User user = User.builder().build();
        user.setAppAccount(AppAccount.builder().build());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        boolean result = userService.updateUserBalance(userId, newBalance);

        // Then
        assertTrue(result);
        assertEquals(newBalance, user.getAppAccount().getBalance());
        verify(appAccountRepository).save(user.getAppAccount());
    }

    @Test
    public void updateUserBalance_noExistingUserId_shouldReturnFalse() {
        // Given
        Long userId = 1L;
        Double newBalance = 100.0;
        User user = User.builder()
                .appAccount(null).build();
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        // When
        boolean result = userService.updateUserBalance(userId, newBalance);

        // Then
        assertFalse(result);
        verify(appAccountRepository, never()).save(any());
    }

    @Test
    public void testCreditUserBalance_Success() {
        // Given
        Long userId = 1L;
        double amount = 100.0;
        User user = mock(User.class);
        AppAccount appAccount = AppAccount.builder()
                .balance(200.0).build();

        // When
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(user.getAppAccount()).thenReturn(appAccount);
        userService.creditUserBalance(userId, amount);

        // Then
        assertEquals(300.0, appAccount.getBalance());
        verify(userRepository).save(user);
    }

    @Test
    public void testCreditUserBalance_UserNotFound() {
        // Given
        Long userId = 1L;
        double amount = 100.0;

        // When
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {userService.creditUserBalance(userId, amount);});
        assertEquals("User not found with ID: " + userId, exception.getMessage());
    }

    @Test
    public void testCreditUserBalance_AppAccountNotFound() {
        // Given
        Long userId = 1L;
        double amount = 100.0;
        User user = mock(User.class);

        // When
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(user.getAppAccount()).thenReturn(null);

        // Then
        Exception exception = assertThrows(IllegalStateException.class, () -> {userService.creditUserBalance(userId, amount);});
        assertEquals("AppAccount not found for User with ID: " + userId, exception.getMessage());
    }

}
