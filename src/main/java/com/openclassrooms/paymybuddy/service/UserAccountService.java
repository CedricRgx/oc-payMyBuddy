package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Iterable<UserAccount> getUserAccounts(){
        return userAccountRepository.findAll();
    }
}
