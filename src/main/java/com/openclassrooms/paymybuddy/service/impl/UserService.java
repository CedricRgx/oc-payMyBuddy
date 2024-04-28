package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.IUserService;
import com.openclassrooms.paymybuddy.util.Formatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public Long getUserIdByEmail(String email){
        log.info("getUserIdByEmail in UserService");
        String sql = "SELECT u.user_id FROM user u " +
                "JOIN user_account ua ON u.user_account_id = ua.user_account_id " +
                "WHERE ua.email = ?";
        Long userId = jdbcTemplate.queryForObject(sql, new Object[]{email}, Long.class);
        return userId;
    }

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
            if(u.getUserAccount().getIsActive() == true) {
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
        Long userId = getUserIdByEmail(email);
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
        String sql = "SELECT appa.balance FROM app_account appa " +
                "INNER JOIN user u ON appa.app_account_id = u.app_account_id " +
                "WHERE u.user_id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Double.class);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
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

}