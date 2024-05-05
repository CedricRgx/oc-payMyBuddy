package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
