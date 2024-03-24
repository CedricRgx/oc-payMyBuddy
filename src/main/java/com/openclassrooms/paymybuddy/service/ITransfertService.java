package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.Transfert;

import java.util.List;
import java.util.Optional;

public interface ITransfertService {

    public Iterable<Transfert> getTransferts();

    public Optional<Transfert> getTransfertById(Long id);

    public Transfert addTransfert(Transfert transfert);

    public void deleteTransfertById(Long id);

    public List<String> getListOfConnections(Long userId);

    public List<TransfertDTO> getListOfTransferts(Long userId, int page, int size);

    public int countTransferts(Long userId);

    public Transfert addNewTransfert(NewTransfertDTO newTransfertDTO) throws Exception;

}
