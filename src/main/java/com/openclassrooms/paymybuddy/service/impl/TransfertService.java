package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.ITransfertService;
import com.openclassrooms.paymybuddy.util.Formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Service implementation for handling money transfers between users in the PayMyBuddy application.
 */
@Service
@Slf4j
public class TransfertService implements ITransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private UserService userService;

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
     * Retrieve the list of transferts of an user from its id
     * @param userId, page for displaying transferts, size of display
     * @param page The page of the list of transferts
     * @param size The size of the list of transferts
     * @return a list of transferts
     */
    public Page<TransfertDTO> getListOfTransferts(Long userId, int page, int size){
        log.info("getListOfTransferts");
        Formatter formatDouble = new Formatter();
        List<TransfertDTO> listOfTransfertsDTO = new ArrayList<TransfertDTO>();
        Iterable<Transfert> listTransfert = getTransferts();
        for(Transfert t : listTransfert){
            if (t.getAuthor().getUserId().equals(userId)) {
                listOfTransfertsDTO.add(TransfertDTO.builder()
                        .recipientFirstname(t.getRecipient().getFirstname())
                        .recipientLastname(t.getRecipient().getLastname())
                        .amount(formatDouble.formatDoubleToString(t.getAmount()))
                        .description(t.getDescription())
                        .transactionDate(t.getTransactionDate())
                        .build());
            }
        }
        listOfTransfertsDTO.sort(Comparator.comparing(TransfertDTO::getTransactionDate, Comparator.reverseOrder()));

        Pageable pageRequest = PageRequest.of(page, size);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), listOfTransfertsDTO.size());
        List<TransfertDTO> pageContent = listOfTransfertsDTO.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, listOfTransfertsDTO.size());
    }


    /**
     * Adds a new transfer based on the provided transfer DTO.
     *
     * @param newTransfertDTO The DTO containing the details of the new transfer.
     * @return true if the transfer is successfully added, false otherwise.
     */
    public boolean addNewTransfert(NewTransfertDTO newTransfertDTO){
        log.info("addNewTransfert service");
        double fee = 0.05;
        String emailAuthor = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userAuthorId = userService.findByEmail(emailAuthor).get().getUserId();
        Optional<User> userAuthorOptional = userService.getUserById(userAuthorId);
        if (!userAuthorOptional.isPresent()) {
            log.info("Author user not found");
            return false;
        }
        User userAuthor = userAuthorOptional.get();

        Double balance = userService.getUserBalance(userAuthorId);
        if (balance == null || newTransfertDTO.getAmount() <= 0) {
            log.info("Amount of the transfert not valid");
            return false;
        } else if (balance < newTransfertDTO.getAmount()) {
            log.info("Insufficient balance to make the transfer");
            return false;
        }

        Optional<User> userRecipientOptional = userService.getUserById(newTransfertDTO.getRecipientId());
        if (!userRecipientOptional.isPresent()) {
            log.info("Recipient user not found");
            return false;
        }
        User userRecipient = userRecipientOptional.get();

        double amountToTransfert = newTransfertDTO.getAmount();
        double feeTransfert = amountToTransfert * fee;
        double amountTransfert = amountToTransfert - feeTransfert;

        double newBalance = balance - newTransfertDTO.getAmount();
        userService.updateUserBalance(userAuthorId, newBalance);

        Transfert transfert = Transfert.builder()
                .author(userAuthor)
                .recipient(userRecipient)
                .amount(amountTransfert)
                .fee(feeTransfert)
                .description(newTransfertDTO.getDescription())
                .transactionDate(LocalDateTime.now())
                .build();

        addTransfert(transfert);
        return true;
    }

}
