package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.exceptions.UpdateLastConnectionDateFailedException;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.IUserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


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

    public Optional<UserAccount> findByEmail(String email){
        log.info("Found an user account by its email address");
        return Optional.ofNullable(userAccountRepository.findByEmail(email));
    }

    /**
     * Check if email is unique for registration
     * @param email
     * @return boolean
     */
    public boolean isEmailUnique(String email){
        String sql = "SELECT COUNT(*) FROM user_account ua WHERE ua.email = ?;";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count==0;
    }

    public void updateLastConnectionDate(Long userAccountId){
        String sql = "UPDATE user_account " +
                "SET last_connection_date = CURRENT_TIMESTAMP " +
                "WHERE user_account_id = ?;";
        int resultCount = jdbcTemplate.update(sql, userAccountId);
        if (resultCount!=1) {
            log.error("The update of the last connection date has succeed.");
            throw new UpdateLastConnectionDateFailedException("The update of the last connection date has failed.");
        }else{
            log.info("The update of the last connection date has succeed.");
        }
    }

    public boolean savePassword(String password, String email){
        String sql = "UPDATE user_account " +
                "SET password = ? " +
                "WHERE email = ?;";
        int resultCount = jdbcTemplate.update(sql, password, email);
        return resultCount==1;
    }
}
