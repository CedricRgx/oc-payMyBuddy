package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppAccountServiceTest {

    @Mock
    private AppAccountRepository appAccountRepository;

    @InjectMocks
    private AppAccountService appAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAppAccounts() {
        appAccountService.getAppAccounts();
        verify(appAccountRepository).findAll();
    }

    @Test
    public void testGetAppAccountByIdFound() {
        Long appAccountId = 1L;
        when(appAccountRepository.findById(appAccountId)).thenReturn(Optional.of(new AppAccount()));
        appAccountService.getAppAccountById(appAccountId);
        verify(appAccountRepository).findById(appAccountId);
    }

    @Test
    public void testGetAppAccountByIdNotFound() {
        Long appAccountId = 1L;
        when(appAccountRepository.findById(appAccountId)).thenReturn(Optional.empty());
        appAccountService.getAppAccountById(appAccountId);
        verify(appAccountRepository).findById(appAccountId);
    }

    @Test
    public void testAddAppAccount() {
        AppAccount appAccount = new AppAccount();
        appAccountService.addAppAccount(appAccount);
        verify(appAccountRepository).save(appAccount);
    }

    @Test
    public void testDeleteAppAccountById() {
        Long appAccountId = 1L;
        appAccountService.deleteAppAccountById(appAccountId);
        verify(appAccountRepository).deleteById(appAccountId);
    }
}
