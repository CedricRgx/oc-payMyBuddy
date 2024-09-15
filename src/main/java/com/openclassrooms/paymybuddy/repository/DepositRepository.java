package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Deposit entities.
 */
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
