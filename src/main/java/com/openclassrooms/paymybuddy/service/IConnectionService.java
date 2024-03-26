package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.User;
import java.util.List;

/**
 * Interface for services related to managing connections between users within the PayMyBuddy application.
 */
public interface IConnectionService {

    /**
     * Retrieves all connections between users.
     *
     * @return A List<User> containing all the connections.
     */
    public List<User> findAllConnections(Long userId);

    /**
     * Searches for connections based on a query.
     *
     * @param query The search query.
     * @param userId The id of the user.
     * @return A List<User> containing the search results.
     */
    public List<User> searchConnections(String query, Long userId);

    /**
     * Adds a new connection between users.
     *
     * @param userId   The ID of the user initiating the connection.
     * @param friendId The ID of the user to be connected with.
     * @return True if the connection was successfully added, False otherwise.
     */
    public boolean addConnection(Long userId, Long friendId);

    /**
     * Removes an existing connection between users.
     *
     * @param userId   The ID of the user initiating the removal.
     * @param friendId The ID of the user whose connection is to be removed.
     * @return True if the connection was successfully removed, False otherwise.
     */
    public boolean removeConnection(Long userId, Long friendId);
}
