package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.Deposit;
import java.util.Optional;

/**
 * Interface for services related to managing deposits within the PayMyBuddy application.
 */
public interface IDepositService {

    /**
     * Retrieves all deposits.
     *
     * @return an Iterable containing all Deposit instances available in the system.
     */
    public Iterable<Deposit> getDeposits();

    /**
     * Retrieves a deposit by its ID.
     *
     * @param id The ID of the deposit to retrieve.
     * @return A Deposit containing the deposit if found, otherwise empty.
     */
    public Optional<Deposit> getDepositById(Long id);

    /**
     * Adds a new deposit.
     *
     * @param deposit The deposit to add.
     * @return The added Deposit.
     */
    public Deposit addDeposit(Deposit deposit);

    /**
     * Deletes a deposit by its ID.
     *
     * @param id The ID of the deposit to delete.
     */
    public void deleteDepositById(Long id);

}
