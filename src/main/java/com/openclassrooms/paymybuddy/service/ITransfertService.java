package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.Transfert;

import java.util.Optional;

public interface ITransfertService {

    public Iterable<Transfert> getTransferts();

    public Optional<Transfert> getTransfertById(Long id);

    public Transfert addTransfert(Transfert transfert);

    public void deleteTransfertById(Long id);

}
