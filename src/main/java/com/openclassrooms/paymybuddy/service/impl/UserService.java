package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.exceptions.UpdateLastConnectionDateFailedException;
import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.IUserService;
import com.openclassrooms.paymybuddy.util.Formatter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService class provides business logic related to User entities.
 */
@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieves all users from the repository.
     * @return An Iterable containing all users.
     */
    public Iterable<User> getUsers(){
        log.info("Retrieving the list of users");
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by its ID.
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the user, or an empty Optional if not found.
     */
    public Optional<User> getUserById(Long id){
        log.info("Retrieving an user by its id");
        return userRepository.findById(id);
    }

    /**
     * Adds a new user to the repository.
     * @param user The User object to be added.
     * @return The added User object.
     */
    public User addUser(User user){
        log.info("Adding an user");
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to be deleted.
     */
    public void deleteUserById(Long id){
        log.info("Deleting an user");
        userRepository.deleteById(id);
    }

    /**
     * Retrieve userId of an user from its email address
     * @param email email of the user
     * @return userId
     */
/*    public Long getUserIdByEmail(String email) {
        log.info("getUserIdByEmail in UserService");
        String jpql = "SELECT u.id FROM User u " +
                "JOIN u.userAccount ua " +
                "WHERE ua.email = :email";
        Long userId = entityManager.createQuery(jpql, Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return userId;
    }*/

    /**
     * Retrieves a list of active friends for a given list of user friends.
     *
     * @param listOfFriends The list of user friends.
     * @return A list of ConnectionDTOs representing active friends.
     */
    public List<ConnectionDTO> getActiveFriends(List<User> listOfFriends){
        log.info("getActiveFriends in UserService");
        List<ConnectionDTO> listOfActiveFriends = new ArrayList<ConnectionDTO>();
        for(User u : listOfFriends) {
            if(u.getIsActive() == true) {
                ConnectionDTO connectionDTO = ConnectionDTO.builder()
                        .userId(u.getUserId())
                        .firstname(u.getFirstname())
                        .lastname(u.getLastname())
                        .build();
                listOfActiveFriends.add(connectionDTO);
            }
        }
        return listOfActiveFriends;
    }

    /**
     * Converts a User entity into a UserDTO for the given user email.
     *
     * @param email The email address of the user to convert.
     * @return A UserDTO representation of the User.
     */
    public UserDTO getUserDTOFromUser(String email){
        log.info("getUserDTOFromUser in UserService");
        Long userId = findByEmail(email).get().getUserId();
        User user = getUserById(userId).get();
        Formatter fd = new Formatter();
        String balanceFormatted = fd.formatDoubleToString(user.getAppAccount().getBalance());
        String balanceFormattedWithCurrency = fd.addCurrency(balanceFormatted);
        UserDTO userDTO = UserDTO.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .address(user.getAddress())
                .balance(balanceFormattedWithCurrency)
                .iban(user.getAppAccount().getIban())
                .build();
        return userDTO;
    }

    /**
     * Retrieve the balance of the user's account.
     *
     * @param userId the ID of the user
     * @return the balance of the user's account, or null if no balance is found
     */
    public Double getUserBalance(Long userId){
        log.info("getUserBalance in UserService");
        User user = getUserById(userId).get();
        AppAccount appAccount = user.getAppAccount();
        if (appAccount == null) {
            log.error("User ID: " + userId + " does not have an app account");
            return null;
        }
        return appAccount.getBalance();
    }

    /**
     * Updates the user's balance in the app_account table.
     *
     * @param userId     the ID of the user whose balance needs to be updated
     * @param newBalance the new balance to be assigned to the user
     * @return true if the balance update was successful, false otherwise
     */
    public boolean updateUserBalance(Long userId, Double newBalance) {
        User user = userRepository.findById(userId).get();
        AppAccount appAccount = user.getAppAccount();
        if (appAccount != null) {
            appAccount.setBalance(newBalance);
            appAccountRepository.save(appAccount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Credits the balance of a user's associated app account.
     *
     * @param userId The ID of the user whose balance needs to be credited.
     * @param amount The amount to credit.
     * @throws IllegalArgumentException If the user with the specified ID is not found.
     * @throws IllegalStateException    If the app account associated with the user is not found.
     */
    public void creditUserBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            AppAccount appAccount = user.getAppAccount();
            if (appAccount != null) {
                double newBalance = appAccount.getBalance() + amount;
                appAccount.setBalance(newBalance);
                userRepository.save(user);
            } else {
                throw new IllegalStateException("AppAccount not found for User with ID: " + userId);
            }
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    /**
     * Debits the balance of a user's associated app account.
     *
     * @param userId The ID of the user whose balance needs to be debited.
     * @param amount The amount to debit.
     * @throws IllegalArgumentException If the user with the specified ID is not found.
     * @throws IllegalStateException    If the app account associated with the user is not found.
     */
    public void debitUserBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            AppAccount appAccount = user.getAppAccount();
            if (appAccount != null) {
                double newBalance = appAccount.getBalance() - amount;
                appAccount.setBalance(newBalance);
                userRepository.save(user);
            } else {
                throw new IllegalStateException("AppAccount not found for User with ID: " + userId);
            }
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    /**
     * Finds a user account by email.
     *
     * @param email The email address to search for.
     * @return An UserAccount containing the user account if found, otherwise an empty Optional.
     */
    public Optional<User> findByEmail(String email){
        log.info("Found an user by its email address: {} " + email);
        return Optional.ofNullable(userRepository.findByEmail(email));
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
     * @param userId The ID of the user account to update.
     * @throws UpdateLastConnectionDateFailedException if the update operation fails.
     */
    public void updateLastConnectionDate(Long userId) {
        log.info("updateLastConnectionDate in UserAccountService");
        User user = userRepository.findById(userId).get();
        user.setLastConnectionDate(LocalDateTime.now());
        userRepository.save(user);
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
        User user = userRepository.findByEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

}