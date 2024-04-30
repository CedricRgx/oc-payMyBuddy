package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.UserAccount;
import java.util.Optional;

/**
 * Interface for services related to user accounts within the PayMyBuddy application.
 */
public interface IUserAccountService {

    /**
     * Retrieves all user accounts.
     *
     * @return An iterable containing all user accounts.
     */
    public Iterable<UserAccount> getUserAccounts();

    /**
     * Retrieves a user account by its ID.
     *
     * @param id The ID of the user account to retrieve.
     * @return An optional containing the user account if found, otherwise empty.
     */
    public Optional<UserAccount> getUserAccountById(Long id);

    /**
     * Adds a new user account.
     *
     * @param userAccount The user account to add.
     * @return The added user account.
     */
    public UserAccount addUserAccount(UserAccount userAccount);

    /**
     * Deletes a user account by its ID.
     *
     * @param id The ID of the user account to delete.
     */
    public void deleteUserAccountById(Long id);

    /**
     * This method searches an user from its email
     *
     * @param email email of the user has to be found
     * @return an UserAccount
     */
    public Optional<UserAccount> findByEmail(String email);

    /**
     * Checks if an email is unique.
     *
     * @param email The email to check for uniqueness.
     * @return true if the email is unique, false otherwise.
     */
    public boolean isEmailUnique(String email);

    /**
     * Updates the last connection date for a user account.
     *
     * @param userAccountId The ID of the user account for which to update the last connection date.
     */
    public void updateLastConnectionDate(Long userAccountId);

    /**
     * Saves a password for a user account.
     *
     * @param password The password to save.
     * @param email    The email of the user account for which to save the password.
     * @return true if the password is saved successfully, false otherwise.
     */
    public boolean savePassword(String password, String email);

}
