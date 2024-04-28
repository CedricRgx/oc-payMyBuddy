package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppAccountServiceIntegrationTest {

    @Autowired
    private AppAccountService appAccountService;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Test
    public void testGetAppAccounts() {
        // Given
        AppAccount account = AppAccount.builder()
                .balance(100.0).build();
        appAccountRepository.save(account);

        // When
        Iterable<AppAccount> accounts = appAccountService.getAppAccounts();

        // Then
        assertNotNull(accounts);
        assertTrue(accounts.iterator().hasNext());
    }

    @Test
    public void testGetAppAccountById() {
        // Given
        AppAccount account = AppAccount.builder()
                .balance(100.0).build();
        account = appAccountRepository.save(account);
        Long id = account.getAppAccountId();

        // When
        Optional<AppAccount> retrievedAccount = appAccountService.getAppAccountById(id);

        // Then
        assertTrue(retrievedAccount.isPresent());
        assertEquals(100.0, retrievedAccount.get().getBalance());
    }

    @Test
    public void testAddAppAccount() {
        // Given
        AppAccount account = AppAccount.builder()
                .balance(200.0).build();

        // When
        AppAccount savedAccount = appAccountService.addAppAccount(account);

        // Then
        assertNotNull(savedAccount.getAppAccountId());
        assertEquals(200.0, savedAccount.getBalance());
    }

    @Test
    public void testDeleteAppAccountById() {
        // Given
        AppAccount account = AppAccount.builder()
                .balance(300.0).build();
        account = appAccountRepository.save(account);
        Long id = account.getAppAccountId();

        // When
        appAccountService.deleteAppAccountById(id);
        Optional<AppAccount> deletedAccount = appAccountRepository.findById(id);

        // Then
        assertFalse(deletedAccount.isPresent());
    }
}
