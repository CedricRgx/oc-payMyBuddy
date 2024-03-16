package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.exceptions.EmailAlreadyUsedException;
import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.service.IRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class RegisterService implements IRegisterService {
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppAccountService appAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(RegisterDTO registerDTO) {
        log.info("Register of an user");

        log.info("Check if the email address is unique");
        if(!userAccountService.isEmailUnique(registerDTO.getEmail())){
            log.error("Email {} is already in use", registerDTO.getEmail());
            throw new EmailAlreadyUsedException(registerDTO.getEmail());
        }

        log.info("Creating new AppAccount");
        AppAccount newAppAccount = AppAccount.builder()
                .balance(0.00)
                .build();
        appAccountService.addAppAccount(newAppAccount);
        Long appAccountId = newAppAccount.getAppAccountId();
        log.info("AppAccount created with ID: {}", appAccountId);

        log.info("Creating new UserAccount");
        UserAccount newUserAccount = UserAccount.builder()
                .isActive(true)
                .lastConnectionDate(LocalDateTime.now())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role("USER")
                .build();
        userAccountService.addUserAccount(newUserAccount);
        Long userAccountId = newUserAccount.getUserAccountId();
        log.info("UserAccount created with ID: {}", userAccountId);

        log.info("Creating new User");
        User newUser = User.builder()
                .firstname(registerDTO.getFirstname())
                .lastname(registerDTO.getLastname())
                .birthdate(registerDTO.getBirthdate())
                .address(registerDTO.getAddress())
                .phone(registerDTO.getPhone())
                .appAccount(newAppAccount)
                .userAccount(newUserAccount)
                .build();
        userService.addUser(newUser);
        log.info("User created");
        return newUser;
    }
}
