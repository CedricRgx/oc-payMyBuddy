package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.AppAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAccountRepository extends CrudRepository<AppAccount, Integer> {
}
