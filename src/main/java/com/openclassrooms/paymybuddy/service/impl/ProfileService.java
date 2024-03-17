package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProfileService {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    public ProfileDTO getProfile(Long userId){
        log.info("getProfile from the userId");
        Optional<User> user = userService.getUserById(userId);
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email(user.get().getUserAccount().getEmail())
                //.password(user.get().getUserAccount().getPassword())
                .firstname(user.get().getFirstname())
                .lastname(user.get().getLastname())
                .birthdate(user.get().getBirthdate())
                .phone(user.get().getPhone())
                .address(user.get().getAddress())
                .build();
        return profileDTO;
    }
}
