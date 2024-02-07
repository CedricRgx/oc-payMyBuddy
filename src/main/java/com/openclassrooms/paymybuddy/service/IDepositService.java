package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.Deposit;

import java.util.Optional;

public interface IDepositService {

    public Iterable<Deposit> getDeposits();

    public Optional<Deposit> getDepositById(Long id);

    public Deposit addDeposit(Deposit deposit);

    public void deleteDepositById(Long id);

}
