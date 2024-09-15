package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

/**
 * Spring Data JPA repository for the Transfert entities.
 */
@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {

    @Query("SELECT COUNT(*) FROM Transfert t WHERE t.author.userId = :userId")
    public int countByUser(Long userId);

}
