package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.ITransfertService;
import jakarta.transaction.InvalidTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransfertService implements ITransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public Optional<Transfert> getTransfertById(Long id){
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

    /**
     * Retrieve the list of connection of an user from its id
     * @param userId
     * @return a list of connections
     */
    public List<String> getListOfConnections(Long userId) {
        String sql = "SELECT u.firstname, u.lastname " +
                "FROM user u " +
                "INNER JOIN assoc_user_friend auf ON u.user_id = auf.friend_id " +
                "WHERE auf.user_id = ?";

        List<String> listOfConnections = new ArrayList<String>();
        listOfConnections = jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                rs.getString("firstname").concat(" ").concat(rs.getString("lastname")));

        return listOfConnections;
    }


    /**
     * Retrieve the list of transferts of an user from its id
     * @param userId, page for displaying transferts, size of display
     * @return a list of transferts
     */
    public List<TransfertDTO> getListOfTransferts(Long userId, int page, int size){
        int offset = page * size;

        String sql = "SELECT recipient.firstname AS recipient_firstname, recipient.lastname AS recipient_lastname, t.description, t.amount " +
                "FROM transfert t " +
                "INNER JOIN user AS author ON t.author_id = author.user_id " +
                "INNER JOIN user AS recipient ON t.recipient_id = recipient.user_id " +
                "WHERE t.author_id = ? " +
                "ORDER BY t.transaction_date DESC " +
                "LIMIT ? OFFSET ?";

        log.info("getListOfTransferts");
        List<TransfertDTO> listOfTransfertsDTO = new ArrayList<TransfertDTO>();
        listOfTransfertsDTO = jdbcTemplate.query(sql, new Object[]{userId, size, offset}, (rs, rowNum) ->
                TransfertDTO.builder()
                        .recipientFirstname(rs.getString("recipient_firstname"))
                        .recipientLastname(rs.getString("recipient_lastname"))
                        .description(rs.getString("description"))
                        .amount((int)rs.getDouble("amount"))
                        .build());
        return listOfTransfertsDTO;
    }

    public int countTransferts(Long userId){
        String sql = "SELECT COUNT(*) " +
                "FROM transfert t " +
                "INNER JOIN user AS author ON t.author_id = author.user_id " +
                "WHERE t.author_id = ?";
        int result = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return result;
    }

    public Transfert addNewTransfert(NewTransfertDTO newTransfertDTO) throws Exception{
        log.info("addNewTransfert service");
        double fee = 0.05;
        String emailAuthor = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userAuthorId = userService.getUserIdByEmail(emailAuthor);
        User userAuthor = userService.getUserById(userAuthorId).get();

        User userRecipient = userService.getUserById(newTransfertDTO.getRecipientId()).get();
        if (newTransfertDTO.getAmount() <= 0) {
            throw new InvalidTransactionException("Amount of the transfert not valid");
        }

        double feeTransfert = newTransfertDTO.getAmount() * fee;
        double amountTransfert = newTransfertDTO.getAmount() - feeTransfert;

        Transfert transfert = Transfert.builder()
                .author(userAuthor)
                .recipient(userRecipient)
                .amount(amountTransfert)
                .fee(feeTransfert)
                .description(newTransfertDTO.getDescription())
                .transactionDate(LocalDateTime.now())
                .build();

        return addTransfert(transfert);
    }

}
