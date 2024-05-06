package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.User;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method searches an user from its email
     *
     * @param email email of the user has to be found
     * @return an UserAccount
     */
    public User findByEmail(String email);

    /**
     * Searches and returns connections based on an email query
     *
     * @param email The email search query to match against user's email.
     * @param userId ID of the user who made the query.
     * @return A list of users representing the matched connections.
     */
    @Query("SELECT user FROM User user WHERE LOWER(user.email) LIKE LOWER(CONCAT('%', :email, '%')) AND user.userId <> :userId AND user.isActive = true")
    public List<User> searchConnections(String email, Long userId);

    /**
     * Check if email is unique for registration
     * @param email email for query
     * @return boolean true if the email address is unique
     */
    @Query("SELECT COUNT(user) FROM User user WHERE user.email = :email")
    public int isEmailUnique(String email);

}
