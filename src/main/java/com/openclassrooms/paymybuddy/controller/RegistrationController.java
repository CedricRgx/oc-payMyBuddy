package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.AppAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class RegistrationController {

    private UserAccountRepository userAccountRepository;
    private UserAccountService userAccountService;
    private UserService userService;
    private AppAccountService appAccountService;

    @GetMapping("/registrationForm")
    public String viewRegistrationForm(){
        log.info("registrationForm template");
        return "registrationForm";
    }

    /*
    @PostMapping("addUser")
    public String addUser(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registrationForm";
        }
        userService.addUser(user);
        return "registrationForm";
    }*/

    @PostMapping("addUser")
    public String addUser(RegisterDTO registerDTO){
        AppAccount newAppAccount = AppAccount.builder()
                .balance(0.00)
                .build();
        appAccountService.addAppAccount(newAppAccount);
        Long appAccountId = newAppAccount.getAppAccountId();

        UserAccount newUserAccount = UserAccount.builder()
                .isActive(true)
                .lastConnectionDate(LocalDateTime.now())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .role("USER")
                .build();
        userAccountService.addUserAccount(newUserAccount);
        Long userAccountId = newUserAccount.getUserAccountId();

        User newUser = User.builder()
                .firstname(registerDTO.getFirstname())
                .lastname(registerDTO.getLastname())
                .birthdate(registerDTO.getBirthdate())
                .address(registerDTO.getAddress())
                .phone(registerDTO.getPhone())
                .build();

        userService.addUser(newUser);

        return "registrationForm";
    }

}
