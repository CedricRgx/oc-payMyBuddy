package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Interface for services related to users within the PayMyBuddy application.
 */
public interface IUserService {

    /**
     * Retrieves all users.
     *
     * @return An iterable containing all users.
     */
    public Iterable<User> getUsers();

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return An optional containing the user if found, otherwise empty.
     */
    public Optional<User> getUserById(Long id);

    /**
     * Adds a new user.
     *
     * @param user The user to add.
     * @return The added user.
     */
    public User addUser(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     */
    public void deleteUserById(Long id);

    /**
     * Retrieves active friends of a user.
     *
     * @param listOfFriends The list of friends of the user.
     * @return A list of connection DTOs representing the active friends.
     */
    public List<ConnectionDTO> getActiveFriends(List<User> listOfFriends);

    /**
     * Obtains a user DTO from a user entity.
     *
     * @param email The email of the user.
     * @return The user DTO corresponding to the specified user email.
     */
    public UserDTO getUserDTOFromUser(String email);

}
