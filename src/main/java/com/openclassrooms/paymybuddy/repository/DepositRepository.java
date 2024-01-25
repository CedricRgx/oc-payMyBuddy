package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Integer> {
}
