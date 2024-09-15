package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.AppAccount;

import java.util.Optional;

/**
 * Interface for services related to managing application accounts within the PayMyBuddy application.
 */
public interface IAppAccountService {

    /**
     * Retrieves all AppAccount instances.
     *
     * @return an Iterable containing all AppAccount instances available.
     */
    public Iterable<AppAccount> getAppAccounts();

    /**
     * Retrieves an application account by its unique identifier.
     *
     * @param id the unique identifier of the AppAccount to be retrieved.
     * @return an Optional containing the found AppAccount if present
     */
    public Optional<AppAccount> getAppAccountById(Long id);

    /**
     * Adds a new application account.
     *
     * @param appAccount The AppAccount to be added.
     * @return The AppAccount entity after it has been saved to the database.
     */
    public AppAccount addAppAccount(AppAccount appAccount);

    /**
     * Deletes an application account by its unique identifier.
     *
     * @param id The unique identifier of the app account to be deleted.
     */
    public void deleteAppAccountById(Long id);

}
