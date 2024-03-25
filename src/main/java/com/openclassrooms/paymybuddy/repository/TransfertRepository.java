package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Transfert entities.
 */
@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {
}
