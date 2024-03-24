package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.IConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConnectionService implements IConnectionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
