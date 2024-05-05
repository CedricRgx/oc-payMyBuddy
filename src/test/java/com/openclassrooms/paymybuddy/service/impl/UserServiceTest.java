package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    private EntityManager entityManager;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUsers_shouldReturnAllUsers() {
        // Given
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        // When
        Iterable<User> result = userService.getUsers();

        // Then
        assertSame(userList, result);
    }

    @Test
    public void testGetUserById_shouldReturnUser_whenUserExists() {
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
    public void testGetUserById_shouldReturnEmptyOptional_whenUserDoesNotExist() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(empty());

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddUser_shouldReturnAddedUser() {
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
    public void testDeleteUserById_shouldDeleteUser() {
        // Given
        Long userId = 1L;

        // When
        userService.deleteUserById(userId);

        // Then
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testGetActiveFriends_shouldReturnListOfActiveFriends() {
        // Given
        List<User> listOfFriends = new ArrayList<>();
        User activeFriend = User.builder()
                .email("john.doe@example.com")
                .password("XXXX")
                .isActive(true)
                .lastConnectionDate(LocalDateTime.now())
                .role("USER").build();
        listOfFriends.add(activeFriend);
        User inactiveFriend = User.builder().isActive(false).build();
        listOfFriends.add(inactiveFriend);

        // When
        List<ConnectionDTO> result = userService.getActiveFriends(listOfFriends);

        // Then
        assertEquals(1, result.size());
        assertEquals(activeFriend.getUserId(), result.get(0).getUserId());
    }

    @Test
    public void findByEmailTest() {
        // Given
        String email = "testEmail@example.com";
        User user = new User();
        user.setEmail(email);

        // When
        when(userRepository.findByEmail(email)).thenReturn(user);
        Optional<User> optionalUser = userService.findByEmail(email);

        // Then
        assertTrue(optionalUser.isPresent());
        assertEquals(optionalUser.get().getEmail(), email);
        verify(userRepository, times(1)).findByEmail(email);
    }


    @Test
    public void testGetUserById_existingId_shouldReturnOptionalWithUser() {
        // Given
        Long userId = 1L;
        User user = User.builder().build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertTrue(result.isPresent());
        assertSame(user, result.get());
    }

    @Test
    public void testGetUserById_nonExistingId_shouldReturnEmptyOptional() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(empty());

        // When
        Optional<User> result = userService.getUserById(userId);

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    public void testUpdateUserBalance_existingUserId_shouldUpdateBalanceAndReturnTrue() {
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
    public void testUpdateUserBalance_noExistingUserId_shouldReturnFalse() {
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

    @Test
    public void testIsEmailUnique_True() {
        // Given
        String email = "test@example.com";
        long count = 0;

        TypedQuery<Long> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(Long.class))).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(count);

        // When
        boolean isUnique = userService.isEmailUnique(email);

        // Then
        assertTrue(isUnique);
    }

    @Test
    public void testIsEmailUnique_False() {
        // Given
        String email = "test@example.com";
        long count = 1;

        TypedQuery<Long> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(Long.class))).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(count);

        // When
        boolean isUnique = userService.isEmailUnique(email);

        // Then
        assertFalse(isUnique);
    }

    @Test
    public void testUpdateLastConnectionDate_Success() {
        // Given
        Long userAccountId = 1L;
        LocalDateTime previousLastConnectionDate = LocalDateTime.now().minusDays(1);

        User user = User.builder().build();
        user.setLastConnectionDate(previousLastConnectionDate);

        when(userRepository.findById(userAccountId)).thenReturn(Optional.of(user));

        // When
        userService.updateLastConnectionDate(userAccountId);

        // Then
        LocalDateTime afterUpdate = user.getLastConnectionDate();
        assertTrue(Duration.between(afterUpdate, LocalDateTime.now()).getSeconds() < 1);

        verify(userRepository, times(1)).findById(userAccountId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateLastConnectionDate_Failure_AccountNotFound() {
        // Given
        Long userAccountId = 1L;

        when(userRepository.findById(userAccountId)).thenReturn(Optional.empty());

        // When Then
        assertThrows(NoSuchElementException.class, () -> {
            userService.updateLastConnectionDate(userAccountId);
        });

        verify(userRepository, times(1)).findById(userAccountId);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testSavePassword_Success() {
        // Given
        String email = "user@example.com";
        String password = "newSecurePassword";
        User user = User.builder().build();
        when(userRepository.findByEmail(email)).thenReturn(user);

        // When
        boolean result = userService.savePassword(password, email);

        // Then
        assertEquals(true, result);
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(user);
        assertEquals(user.getPassword(), password);
    }

    @Test
    public void testSavePassword_UserNotFound() {
        // Given
        String email = "nonexistent@example.com";
        String password = "password";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // When Then
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.savePassword(password, email);
        });
        assertEquals("Cannot invoke \"com.openclassrooms.paymybuddy.model.User.setPassword(String)\" because \"user\" is null", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

}
