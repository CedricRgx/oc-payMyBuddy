package com.openclassrooms.paymybuddy.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.paymybuddy.exceptions.EmailAlreadyUsedException;
import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RegisterServiceTest {

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private UserService userService;

    @Mock
    private AppAccountService appAccountService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterService registerService;

    @Test
    public void testAddUser_Success() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder()
                .email("email@example.com")
                .password("password")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Street")
                .phone("1234567890").build();

        // When
        when(userAccountService.isEmailUnique(anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        User result = registerService.addUser(registerDTO);

        // Then
        assertNotNull(result);
        verify(userAccountService).addUserAccount(any(UserAccount.class));
        verify(appAccountService).addAppAccount(any(AppAccount.class));
        verify(userService).addUser(any(User.class));
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
        assertEquals("encodedPassword", result.getUserAccount().getPassword());
    }

    @Test
    public void testAddUser_EmailAlreadyUsed() {
        // Given
        RegisterDTO registerDTO = RegisterDTO.builder()
                .email("email@example.com")
                .password("password")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Street")
                .phone("1234567890").build();

        // When
        when(userAccountService.isEmailUnique(anyString())).thenReturn(false);

        // Then
        assertThrows(EmailAlreadyUsedException.class, () -> {registerService.addUser(registerDTO);});
    }
}
