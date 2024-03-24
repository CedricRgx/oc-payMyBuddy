package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService class provides business logic related to User entities.
 * It interacts with the UserRepository to perform CRUD operations on User objects.
 */
@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

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
     * @param email
     * @return userId
     */
    public Long getUserIdByEmail(String email){
        String sql = "SELECT u.user_id FROM user u " +
                "JOIN user_account ua ON u.user_account_id = ua.user_account_id " +
                "WHERE ua.email = ?";
        Long userId = jdbcTemplate.queryForObject(sql, new Object[]{email}, Long.class);
        return userId;
    }

    public List<ConnectionDTO> getActiveFriends(List<User> listOfFriends){
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

    public UserDTO getUserDTOFromUser(String email){
        Long userId = getUserIdByEmail(email);
        User user = getUserById(userId).get();
        UserDTO userDTO = UserDTO.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
        return userDTO;
    }

}