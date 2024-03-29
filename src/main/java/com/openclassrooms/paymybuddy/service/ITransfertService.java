package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.Transfert;
import java.util.List;
import java.util.Optional;

/**
 * Interface for services related to transfers within the PayMyBuddy application.
 */
public interface ITransfertService {

    /**
     * Retrieves all transfers.
     *
     * @return An iterable containing all transfers.
     */
    public Iterable<Transfert> getTransferts();

    /**
     * Retrieves a transfer by its ID.
     *
     * @param id The ID of the transfer to retrieve.
     * @return An optional containing the transfer if found, otherwise empty.
     */
    public Optional<Transfert> getTransfertById(Long id);

    /**
     * Adds a new transfer.
     *
     * @param transfert The transfer to add.
     * @return The added transfer.
     */
    public Transfert addTransfert(Transfert transfert);

    /**
     * Deletes a transfer by its ID.
     *
     * @param id The ID of the transfer to delete.
     */
    public void deleteTransfertById(Long id);

    /**
     * Retrieves a list of user connections.
     *
     * @param userId The ID of the user for whom to retrieve connections.
     * @return A list of user connections as strings.
     */
    public List<String> getListOfConnections(Long userId);

    /**
     * Retrieves a paginated list of transfers for a given user.
     *
     * @param userId The ID of the user for whom to retrieve transfers.
     * @param page   The page number (zero-based).
     * @param size   The size of each page.
     * @return A list of transfer DTOs.
     */
    public List<TransfertDTO> getListOfTransferts(Long userId, int page, int size);

    /**
     * Counts the total number of transfers for a given user.
     *
     * @param userId The ID of the user for whom to count transfers.
     * @return The total number of transfers.
     */
    public int countTransferts(Long userId);

    /**
     * Adds a new transfer based on the information provided in the new transfer DTO.
     *
     * @param newTransfertDTO The DTO containing the information for the new transfer.
     * @return The added transfer.
     * @throws Exception If an error occurs while adding the transfer.
     */
    public boolean addNewTransfert(NewTransfertDTO newTransfertDTO) throws Exception;

}
