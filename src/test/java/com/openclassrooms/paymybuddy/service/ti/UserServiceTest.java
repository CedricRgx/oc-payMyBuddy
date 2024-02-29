package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.relational.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class UserServiceTest {

    UserService userService;

    @BeforeEach
    public void setup() {

    }
/*
    @Test
    public void testGetUserById() {
        log.info("Running testGetUserById() test in UserServiceTest class");
        // GIVEN
        User userTest = User.builder()
                .firstname("Jean-Baptise")
                .lastname("Poquelin")
                .phone("1234567890")
                .address("8 Main Street, 56098 Chemin")
                .birthdate(LocalDate.of(1990, 7, 6))
                .build();
        // WHEN
        userService.addUser(userTest);
        Optional<User> userFound = userService.getUserById(userTest.getUserId());

        // THEN
        assertNotNull(userFound);
    }
    */


    @Test
    public void testAddUser() {
        log.info("Running testAddUser() test in UserServiceTest class");
        // GIVEN
        User userTest = User.builder()
                .firstname("Jean-Baptise")
                .lastname("Poquelin")
                .phone("1234567890")
                .address("8 Main Street, 56098 Chemin")
                .birthdate(LocalDate.of(1990, 7, 6))
                .build();

        //WHEN
        userService.addUser(userTest);
        Optional<User> addedUser = userService.getUserById(userTest.getUserId());

        //THEN
        assertNotNull(addedUser);
    }


    @Test
    public void testDeleteUserById() {
        log.info("Running testDeleteUserById() test in UserServiceTest class");
        // GIVEN
        User userTest = User.builder()
                .firstname("Jean-Baptise")
                .lastname("Poquelin")
                .phone("1234567890")
                .address("8 Main Street, 56098 Chemin")
                .birthdate(LocalDate.of(1990, 7, 6))
                .build();

        //WHEN
        userService.addUser(userTest);
        userService.deleteUserById(userTest.getUserId());

        // THEN
        assertEquals(Optional.empty(), userService.getUserById(userTest.getUserId()));
    }



}
