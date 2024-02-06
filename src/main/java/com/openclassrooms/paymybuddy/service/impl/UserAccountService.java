package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.IUserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

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
    public Optional<UserAccount> getUserAccountsById(Long id){
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
}