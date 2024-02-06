package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
