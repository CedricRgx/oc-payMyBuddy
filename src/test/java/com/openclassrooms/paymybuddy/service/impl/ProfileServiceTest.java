package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private UserService userService;

    @Test
    public void testSaveProfile() {
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email("test@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.now())
                .phone("1234567890")
                .address("Test Address")
                .iban("FR1234567890123456789012345")
                .build();

        User user = User.builder()
                .email("test@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.now())
                .phone("1234567890")
                .address("Test Address")
                .appAccount(AppAccount.builder().build())
                .build();
        user.getAppAccount().setIban("FR1234567890123456789012345");

        when(userService.findByEmail(anyString())).thenReturn(Optional.of(user));

        profileService.saveProfile(profileDTO);

        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void testSaveProfile_UserNotFoundException() {
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email("test@example.com")
                .firstname("First")
                .lastname("Last")
                .birthdate(LocalDate.now())
                .phone("1234567890")
                .address("Test Address")
                .iban("FR1234567890123456789012345")
                .build();

        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());

        try {
            profileService.saveProfile(profileDTO);
        } catch (Exception e) {
            assert(e instanceof UsernameNotFoundException);
        }

        verify(userService, times(0)).addUser(any(User.class));
    }
}