package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.service.IAppAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for managing application accounts in the PayMyBuddy application.
 */
@Service
@Slf4j
public class AppAccountService implements IAppAccountService {

    @Autowired
    private AppAccountRepository appAccountRepository;

    /**
     * Retrieves all app accounts from the repository.
     * @return An Iterable containing all app accounts.
     */
    public Iterable<AppAccount> getAppAccounts(){
        log.info("Retrieving the list of app accounts");
        return appAccountRepository.findAll();
    }

    /**
     * Retrieves an app account by its ID.
     * @param id The ID of the app account to retrieve.
     * @return An Optional containing the app account, or an empty Optional if not found.
     */
    public Optional<AppAccount> getAppAccountById(Long id){
        log.info("Retrieving an app account by its id");
        return appAccountRepository.findById(id);
    }

    /**
     * Adds a new app account to the repository.
     * @param appAccount The AppAccount object to be added.
     * @return The added AppAccount object.
     */
    public AppAccount addAppAccount(AppAccount appAccount){
        log.info("Adding an app account");
        return appAccountRepository.save(appAccount);
    }

    /**
     * Deletes an app account by their ID.
     * @param id The ID of the app account to be deleted.
     */
    public void deleteAppAccountById(Long id){
        log.info("Deleting an app account");
        appAccountRepository.deleteById(id);
    }

}
