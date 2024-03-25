package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AppAccount entities.
 */
@Repository
public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
}
