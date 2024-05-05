/*
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

*/
/**
 * Service implementation for managing user accounts in the PayMyBuddy application.
 *//*

@Slf4j
@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    */
/**
     * Retrieves all user accounts from the repository.
     * @return An Iterable containing all user accounts.
     *//*

    public Iterable<UserAccount> getUserAccounts(){
        log.info("Retrieving all user accounts");
        return userAccountRepository.findAll();
    }

    */
/**
     * Retrieves a user account by its ID.
     * @param id The ID of the user account to retrieve.
     * @return An Optional containing the user account, or an empty Optional if not found.
     *//*

    public Optional<UserAccount> getUserAccountById(Long id){
        log.info("Retrieving an user account by its id");
        return userAccountRepository.findById(id);
    }

    */
/**
     * Adds a new user account to the repository.
     * @param userAccount The UserAccount object to be added.
     * @return The added UserAccount object.
     *//*

    public UserAccount addUserAccount(UserAccount userAccount){
        log.info("Adding an user account");
        return userAccountRepository.save(userAccount);
    }

    */
/**
     * Deletes a user account by their ID.
     * @param id The ID of the user account to be deleted.
     *//*

    public void deleteUserAccountById(Long id){
        log.info("Deleting an user account");
        userAccountRepository.deleteById(id);
    }


}
*/
