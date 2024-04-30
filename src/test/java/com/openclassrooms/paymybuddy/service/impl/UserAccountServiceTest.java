package com.openclassrooms.paymybuddy.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.paymybuddy.exceptions.UpdateLastConnectionDateFailedException;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
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
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserAccountServiceTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserAccountService userAccountService;

    @Mock
    private UserAccountRepository userAccountRepository;

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
        boolean isUnique = userAccountService.isEmailUnique(email);

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
        boolean isUnique = userAccountService.isEmailUnique(email);

        // Then
        assertFalse(isUnique);
    }

    @Test
    public void testUpdateLastConnectionDate_Success() {
        // Given
        Long userAccountId = 1L;
        LocalDateTime previousLastConnectionDate = LocalDateTime.now().minusDays(1);

        UserAccount userAccount = UserAccount.builder().build();
        userAccount.setLastConnectionDate(previousLastConnectionDate);

        when(userAccountRepository.findById(userAccountId)).thenReturn(Optional.of(userAccount));

        // When
        userAccountService.updateLastConnectionDate(userAccountId);

        // Then
        LocalDateTime afterUpdate = userAccount.getLastConnectionDate();
        assertTrue(Duration.between(afterUpdate, LocalDateTime.now()).getSeconds() < 1);

        verify(userAccountRepository, times(1)).findById(userAccountId);
        verify(userAccountRepository, times(1)).save(userAccount);
    }

    @Test
    public void testUpdateLastConnectionDate_Failure_AccountNotFound() {
        // Given
        Long userAccountId = 1L;

        when(userAccountRepository.findById(userAccountId)).thenReturn(Optional.empty());

        // When Then
        assertThrows(NoSuchElementException.class, () -> {
            userAccountService.updateLastConnectionDate(userAccountId);
        });

        verify(userAccountRepository, times(1)).findById(userAccountId);
        verify(userAccountRepository, never()).save(any(UserAccount.class));
    }

    @Test
    public void testSavePassword_Success() {
        // Given
        String email = "user@example.com";
        String password = "newSecurePassword";
        UserAccount userAccount = new UserAccount();
        when(userAccountRepository.findByEmail(email)).thenReturn(userAccount);

        // When
        boolean result = userAccountService.savePassword(password, email);

        // Then
        assertEquals(true, result);
        verify(userAccountRepository, times(1)).findByEmail(email);
        verify(userAccountRepository, times(1)).save(userAccount);
        assertEquals(userAccount.getPassword(), password);
    }

    @Test
    public void testSavePassword_UserNotFound() {
        // Given
        String email = "nonexistent@example.com";
        String password = "password";
        when(userAccountRepository.findByEmail(email)).thenReturn(null);

        // When Then
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userAccountService.savePassword(password, email);
        });
        assertEquals("Cannot invoke \"com.openclassrooms.paymybuddy.model.UserAccount.setPassword(String)\" because \"userAccount\" is null", exception.getMessage());
        verify(userAccountRepository, times(1)).findByEmail(email);
        verify(userAccountRepository, never()).save(any(UserAccount.class));
    }

}
