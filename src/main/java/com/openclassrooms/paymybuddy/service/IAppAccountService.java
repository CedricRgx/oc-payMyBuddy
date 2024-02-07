package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.AppAccount;

import java.util.Optional;

public interface IAppAccountService {

    public Iterable<AppAccount> getAppAccounts();

    public Optional<AppAccount> getAppAccountById(Long id);

    public AppAccount addAppAccount(AppAccount appAccount);

    public void deleteAppAccountById(Long id);

}
