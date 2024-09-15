package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AppAccountServiceTest {

    @Mock
    private AppAccountRepository appAccountRepository;

    @InjectMocks
    private AppAccountService appAccountService;

    @Test
    public void testGetAppAccounts() {
        // Given
        appAccountService.getAppAccounts();

        // When Then
        verify(appAccountRepository).findAll();
    }

    @Test
    public void testGetAppAccountByIdFound() {
        // Given
        Long appAccountId = 1L;

        // When
        when(appAccountRepository.findById(appAccountId)).thenReturn(Optional.of(new AppAccount()));
        appAccountService.getAppAccountById(appAccountId);

        // Then
        verify(appAccountRepository).findById(appAccountId);
    }

    @Test
    public void testGetAppAccountByIdNotFound() {
        // Given
        Long appAccountId = 1L;

        // When
        when(appAccountRepository.findById(appAccountId)).thenReturn(Optional.empty());
        appAccountService.getAppAccountById(appAccountId);

        // Then
        verify(appAccountRepository).findById(appAccountId);
    }

    @Test
    public void testAddAppAccount() {
        // Given
        AppAccount appAccount = new AppAccount();

        // When
        appAccountService.addAppAccount(appAccount);

        // Then
        verify(appAccountRepository).save(appAccount);
    }

    @Test
    public void testDeleteAppAccountById() {
        // Given
        Long appAccountId = 1L;

        // When
        appAccountService.deleteAppAccountById(appAccountId);

        // Then
        verify(appAccountRepository).deleteById(appAccountId);
    }
}
