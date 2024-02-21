package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.UserAccount;

import java.util.Optional;

public interface IUserAccountService {

    public Iterable<UserAccount> getUserAccounts();

    public Optional<UserAccount> getUserAccountById(Long id);

    public UserAccount addUserAccount(UserAccount userAccount);

}
