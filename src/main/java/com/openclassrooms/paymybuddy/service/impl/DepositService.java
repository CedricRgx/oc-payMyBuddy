package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import com.openclassrooms.paymybuddy.service.IDepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class DepositService implements IDepositService {

    @Autowired
    private DepositRepository depositRepository;

    /**
     * Retrieves all deposits from the repository.
     * @return An Iterable containing all deposits.
     */
    public Iterable<Deposit> getDeposits(){
        log.info("Retrieving all deposits");
        return depositRepository.findAll();
    }

    /**
     * Retrieves a deposit by its ID.
     * @param id The ID of the deposit to retrieve.
     * @return An Optional containing the deposit, or an empty Optional if not found.
     */
    public Optional<Deposit> getDepositById(Long id){
        log.info("Retrieving an deposit by its id");
        return depositRepository.findById(id);
    }

    /**
     * Adds a new deposit to the repository.
     * @param deposit The Deposit object to be added.
     * @return The added Deposit object.
     */
    public Deposit addDeposit(Deposit deposit){
        log.info("Adding an deposit");
        return depositRepository.save(deposit);
    }

    /**
     * Deletes a deposit by its ID.
     * @param id The ID of the deposit to be deleted.
     */
    public void deleteDepositById(Long id){
        log.info("Deleting an deposit");
        depositRepository.deleteById(id);
    }
}
