package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public Iterable<Deposit> getDeposits(){
        return depositRepository.findAll();
    }
}
