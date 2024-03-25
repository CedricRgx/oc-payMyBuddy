package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;

/**
 * Interface for services related to user registration within the PayMyBuddy application.
 */
public interface IRegisterService {

    /**
     * Adds a new user based on the registration DTO provided.
     *
     * @param registerDTO The DTO containing the registration information for the new user.
     * @return The User object representing the newly registered user.
     */
    public User addUser(RegisterDTO registerDTO);
}
