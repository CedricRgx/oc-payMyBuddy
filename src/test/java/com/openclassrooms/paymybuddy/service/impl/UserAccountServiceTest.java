package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;

@Slf4j
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
        log.info("Running testGetUserAccounts() test in UserAccountServiceTest class");
        userAccountService.getUserAccounts();
        verify(userAccountRepository).findAll();
    }

    @Test
    public void testGetUserAccountByIdFound() {
        log.info("Running testGetUserAccountByIdFound() test in UserAccountServiceTest class");
        Long userAccountId = 1L;
        when(userAccountRepository.findById(userAccountId)).thenReturn(Optional.of(new UserAccount()));
        userAccountService.getUserAccountById(userAccountId);
        verify(userAccountRepository).findById(userAccountId);
    }

    @Test
    public void testGetUserAccountByIdNotFound() {
        log.info("Running testGetUserAccountByIdNotFound() test in UserAccountServiceTest class");
        Long appAccountId = 1L;
        when(userAccountRepository.findById(appAccountId)).thenReturn(Optional.empty());
        userAccountService.getUserAccountById(appAccountId);
        verify(userAccountRepository).findById(appAccountId);
    }

    @Test
    public void testAddUserAccount() {
        log.info("Running testAddUserAccount() test in UserAccountServiceTest class");
        UserAccount userAccount = new UserAccount();
        userAccountService.addUserAccount(userAccount);
        verify(userAccountRepository).save(userAccount);
    }

    @Test
    public void testDeleteUserAccountById() {
        log.info("Running testDeleteUserAccountById() test in UserAccountServiceTest class");
        Long userAccountId = 1L;
        userAccountService.deleteUserAccountById(userAccountId);
        verify(userAccountRepository).deleteById(userAccountId);
    }
}
