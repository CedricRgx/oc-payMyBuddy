package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.ITransfertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransfertService implements ITransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    /**
     * Retrieves all transferts from the repository.
     * @return An Iterable containing all transferts.
     */
    public Iterable<Transfert> getTransferts(){
        log.info("Retrieving all transferts");
        return transfertRepository.findAll();
    }

    /**
     * Retrieves a Transfert by its ID.
     * @param id The ID of the Transfert to retrieve.
     * @return An Optional containing the transfert, or an empty Optional if not found.
     */
    public Optional<Transfert> getTransfertsById(Long id){
        log.info("Retrieving an transfert by its id");
        return transfertRepository.findById(id);
    }

    /**
     * Adds a new transfert to the repository.
     * @param transfert The Transfert object to be added.
     * @return The added Transfert object.
     */
    public Transfert addTransfert(Transfert transfert){
        log.info("Adding an transfert");
        return transfertRepository.save(transfert);
    }

    /**
     * Deletes a transfert by its ID.
     * @param id The ID of the transfert to be deleted.
     */
    public void deleteTransfertById(Long id){
        log.info("Deleting an transfert");
        transfertRepository.deleteById(id);
    }
}
