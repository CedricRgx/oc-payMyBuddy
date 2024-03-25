package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UserAccount entities.
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    public UserAccount findByEmail(String email);
}
