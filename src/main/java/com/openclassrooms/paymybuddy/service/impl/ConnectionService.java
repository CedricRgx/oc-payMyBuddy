package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.IConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implements the IConnectionService interface to manage user connections in PayMyBuddy application.
 */
@Service
@Slf4j
public class ConnectionService implements IConnectionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Finds and returns all connections for a given user.
     * @return A list of users representing all the user's connections.
     */
    public List<User> findAllConnections(){
        return jdbcTemplate.query("SELECT * FROM user",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getLong("user_id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    return user;
                });
    }

    /**
     * Searches and returns connections based on a query that matches either first name or last name.
     * @param query The search query to match against user's firstname or lastname.
     * @return A list of users representing the matched connections.
     */
    public List<User> searchConnections(String query){
        String sql = "SELECT * FROM user WHERE LOWER(firstname) LIKE LOWER(?) OR LOWER(lastname) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, new Object[]{"%" + query + "%", "%" + query + "%"},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getLong("user_id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    return user;
                });
    }

    /**
     * Adds a new connection between two users.
     *
     * @param userId The user ID of the first user.
     * @param friendId The user ID of the friend to connect with.
     * @return True if the connection was successfully added, False otherwise.
     */
    public boolean addConnection(Long userId, Long friendId){
        String sql = "INSERT INTO assoc_user_friend (user_id, friend_id) VALUES (?, ?)";
        try{
            int result = jdbcTemplate.update(sql, userId, friendId);
            return result > 0;
        }catch(DataAccessException e){
            log.error("Error adding connection between user {} and friend {}: {}", userId, friendId, e.getMessage());
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
    public boolean removeConnection(Long userId, Long friendId){
        String sql = "DELETE FROM assoc_user_friend WHERE user_id = ? AND friend_id = ?";
        try{
            int result = jdbcTemplate.update(sql, userId, friendId);
            return result > 0;
        }catch(DataAccessException e){
            log.error("Error removing connection between user {} and friend {}: {}", userId, friendId, e.getMessage());
            return false;
        }
    }

}
