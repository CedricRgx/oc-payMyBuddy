package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}