package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Deposit entities.
 */
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
