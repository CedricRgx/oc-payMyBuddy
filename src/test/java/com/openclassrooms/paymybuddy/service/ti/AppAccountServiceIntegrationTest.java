package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppAccountServiceIntegrationTest {

    @Autowired
    private AppAccountService appAccountService;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Test
    public void testGetAppAccounts() {
        AppAccount account = new AppAccount();
        account.setBalance(100.0);
        appAccountRepository.save(account);

        Iterable<AppAccount> accounts = appAccountService.getAppAccounts();

        assertNotNull(accounts);
        assertTrue(accounts.iterator().hasNext(), "Expected at least one account in the list");
    }

    @Test
    public void testGetAppAccountById() {
        AppAccount account = new AppAccount();
        account.setBalance(100.0);
        account = appAccountRepository.save(account);
        Long id = account.getAppAccountId();

        Optional<AppAccount> retrievedAccount = appAccountService.getAppAccountById(id);

        assertTrue(retrievedAccount.isPresent(), "Account should be found with ID " + id);
        assertEquals(100.0, retrievedAccount.get().getBalance(), "Balance should be 100.0");
    }

    @Test
    public void testAddAppAccount() {
        AppAccount account = new AppAccount();
        account.setBalance(200.0);

        AppAccount savedAccount = appAccountService.addAppAccount(account);

        assertNotNull(savedAccount.getAppAccountId(), "Saved account should have an ID");
        assertEquals(200.0, savedAccount.getBalance(), "Balance should be 200.0");
    }

    @Test
    public void testDeleteAppAccountById() {
        AppAccount account = new AppAccount();
        account.setBalance(300.0);
        account = appAccountRepository.save(account);
        Long id = account.getAppAccountId();

        appAccountService.deleteAppAccountById(id);

        Optional<AppAccount> deletedAccount = appAccountRepository.findById(id);
        assertFalse(deletedAccount.isPresent(), "Account should not exist after being deleted");
    }
}
