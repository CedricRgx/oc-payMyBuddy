package com.openclassrooms.paymybuddy.Service.Impl;

import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;

class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountService userAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserAccounts() {
        userAccountService.getUserAccounts();
        verify(userAccountRepository).findAll();
    }

    @Test
    public void testGetUserAccountByIdFound() {
        Long userAccountId = 1L;
        when(userAccountRepository.findById(userAccountId)).thenReturn(Optional.of(new UserAccount()));
        userAccountService.getUserAccountsById(userAccountId);
        verify(userAccountRepository).findById(userAccountId);
    }

    @Test
    public void testGetUserAccountByIdNotFound() {
        Long appAccountId = 1L;
        when(userAccountRepository.findById(appAccountId)).thenReturn(Optional.empty());
        userAccountService.getUserAccountsById(appAccountId);
        verify(userAccountRepository).findById(appAccountId);
    }

    @Test
    public void testAddUserAccount() {
        UserAccount userAccount = new UserAccount();
        userAccountService.addUserAccount(userAccount);
        verify(userAccountRepository).save(userAccount);
    }

    @Test
    public void testDeleteUserAccountById() {
        Long userAccountId = 1L;
        userAccountService.deleteUserAccountById(userAccountId);
        verify(userAccountRepository).deleteById(userAccountId);
    }
}
