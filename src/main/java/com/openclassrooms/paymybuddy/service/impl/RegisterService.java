package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.exceptions.EmailAlreadyUsedException;
import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.IRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service implementation for user registration in the PayMyBuddy application.
 */
@Slf4j
@Service
public class RegisterService implements IRegisterService {

    @Autowired
    private UserService userService;

    @Autowired
    private AppAccountService appAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user based on the provided registration details.
     * This process includes verifying the uniqueness of the email address, creating a new AppAccount,
     * a new UserAccount with encrypted password, and finally creating the User entity with all associated details.
     *
     * @param registerDTO A DTO containing the registration details provided by the user.
     * @return The newly created User entity.
     * @throws EmailAlreadyUsedException if the provided email address is already in use.
     */
    @Override
    public User addUser(RegisterDTO registerDTO) {
        log.info("Register of an user");

        log.info("Check if the email address is unique");
        if(!userService.isEmailUnique(registerDTO.getEmail())){
            log.error("Email {} is already in use", registerDTO.getEmail());
            throw new EmailAlreadyUsedException(registerDTO.getEmail());
        }

        log.info("Creating new AppAccount");
        AppAccount newAppAccount = AppAccount.builder()
                .balance(0.00)
                .iban(registerDTO.getIban())
                .build();
        appAccountService.addAppAccount(newAppAccount);
        Long appAccountId = newAppAccount.getAppAccountId();
        log.info("AppAccount created with ID: {}", appAccountId);

        log.info("Creating new User");
        User newUser = User.builder()
                .firstname(registerDTO.getFirstname())
                .lastname(registerDTO.getLastname())
                .birthdate(registerDTO.getBirthdate())
                .address(registerDTO.getAddress())
                .phone(registerDTO.getPhone())
                .appAccount(newAppAccount)
                .isActive(true)
                .lastConnectionDate(LocalDateTime.now())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role("USER")
                .build();
        userService.addUser(newUser);
        Long userId = newUser.getUserId();
        log.info("User created with ID: {}", userId);

        return newUser;
    }
}
