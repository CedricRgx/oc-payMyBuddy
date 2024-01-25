package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppAccountService {

    @Autowired
    private AppAccountRepository appAccountRepository;

    public Iterable<AppAccount> getAppAccounts(){
        return appAccountRepository.findAll();
    }

}
