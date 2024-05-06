package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.IConnectionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Implements the IConnectionService interface to manage user connections in PayMyBuddy application.
 */
@Service
@Slf4j
public class ConnectionService implements IConnectionService{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds and returns all connections for a given user that are not already friends.
     * @param userId The ID of the user whose friends should be excluded.
     * @return A list of users representing all the user's connections excluding existing friends.
     */
    public List<User> findAllConnections(Long userId){
        log.info("findAllConnections in ConnectionService");
        User user = userService.getUserById(userId).get();
        List<User> listConnections = user.getFriends();
        return listConnections;
    }

    /**
     * Searches and returns connections based on an email query
     *
     * @param email The email search query to match against user's email.
     * @return A list of users representing the matched connections.
     */
    public List<User> searchConnections(String email, Long userId){
        log.info("searchConnections in ConnectionService");
        return userRepository.searchConnections(email, userId);
    }

    /**
     * Adds a new connection between two users.
     *
     * @param userId The user ID of the first user.
     * @param friendId The user ID of the friend to connect with.
     * @return True if the connection was successfully added, False otherwise.
     */
    public boolean addConnection(Long userId, Long friendId){
        log.info("addConnection in ConnectionService");
        try {
            User user = userService.getUserById(userId).orElseThrow(() -> new Exception("User not found"));
            User userFriend = userService.getUserById(friendId).orElseThrow(() -> new Exception("Friend not found"));

            boolean added = user.getFriends().add(userFriend);
            if (added) {
                userService.addUser(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Failed to add connection: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Removes an existing connection between two users.
     *
     * @param userId The user ID of the first user.
     * @param friendId The user ID of the friend to disconnect.
     * @return True if the connection was successfully removed, False otherwise.
     */
    @Transactional
    public boolean removeConnection(Long userId, Long friendId){
        log.info("removeConnection in ConnectionService");
        User user = userService.getUserById(userId).get();
        if (user == null){
            log.error("User not found with ID: {}", userId);
            return false;
        }
        List<User> listFriends = user.getFriends();
        Iterator<User> iterator = listFriends.iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            User friend = iterator.next();
            if (friend.getUserId().equals(friendId)) {
                iterator.remove();
                result = true;
                break;
            }
        }
        if(result){
            user.setFriends(listFriends);
        }
        return result;
    }

}
