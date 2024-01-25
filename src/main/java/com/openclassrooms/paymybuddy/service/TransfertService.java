package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    public Iterable<Transfert> getTransferts(){
        return transfertRepository.findAll();
    }
}
