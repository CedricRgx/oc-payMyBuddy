package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.exceptions.UpdateLastConnectionDateFailedException;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.IUserAccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service implementation for managing user accounts in the PayMyBuddy application.
 */
@Slf4j
@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieves all user accounts from the repository.
     * @return An Iterable containing all user accounts.
     */
    public Iterable<UserAccount> getUserAccounts(){
        log.info("Retrieving all user accounts");
        return userAccountRepository.findAll();
    }

    /**
     * Retrieves a user account by its ID.
     * @param id The ID of the user account to retrieve.
     * @return An Optional containing the user account, or an empty Optional if not found.
     */
    public Optional<UserAccount> getUserAccountById(Long id){
        log.info("Retrieving an user account by its id");
        return userAccountRepository.findById(id);
    }

    /**
     * Adds a new user account to the repository.
     * @param userAccount The UserAccount object to be added.
     * @return The added UserAccount object.
     */
    public UserAccount addUserAccount(UserAccount userAccount){
        log.info("Adding an user account");
        return userAccountRepository.save(userAccount);
    }

    /**
     * Deletes a user account by their ID.
     * @param id The ID of the user account to be deleted.
     */
    public void deleteUserAccountById(Long id){
        log.info("Deleting an user account");
        userAccountRepository.deleteById(id);
    }

    /**
     * Finds a user account by email.
     *
     * @param email The email address to search for.
     * @return An UserAccount containing the user account if found, otherwise an empty Optional.
     */
    public Optional<UserAccount> findByEmail(String email){
        log.info("Found an user account by its email address");
        return Optional.ofNullable(userAccountRepository.findByEmail(email));
    }

    /**
     * Check if email is unique for registration
     * @param email email for query
     * @return boolean
     */
    public boolean isEmailUnique(String email){
        log.info("isEmailUnique in UserAccountService");
        String jpql = "SELECT COUNT(ua) FROM UserAccount ua WHERE ua.email = :email";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("email", email);
        Long count = query.getSingleResult();
        return count==0;
    }

    /**
     * Updates the last connection date of a user account.
     *
     * @param userAccountId The ID of the user account to update.
     * @throws UpdateLastConnectionDateFailedException if the update operation fails.
     */
    public void updateLastConnectionDate(Long userAccountId) {
        log.info("updateLastConnectionDate in UserAccountService");
        UserAccount userAccount = userAccountRepository.findById(userAccountId).get();
        userAccount.setLastConnectionDate(LocalDateTime.now());
        userAccountRepository.save(userAccount);
    }

    /**
     * Updates the password for a user account identified by email.
     *
     * @param password The new password to set, typically after being encrypted.
     * @param email The email address of the user account to update.
     * @return True if the password was successfully updated, False otherwise.
     */
    public boolean savePassword(String password, String email) {
        log.info("savePassword in UserAccountService");
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        userAccount.setPassword(password);
        userAccountRepository.save(userAccount);
        return true;
    }
}
