package com.openclassrooms.paymybuddy.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private ProfileService profileService;

    private User user;
    private ProfileDTO expectedProfile;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setBirthdate(LocalDate.of(1985, 5, 20));
        user.setPhone("1234567890");
        user.setAddress("123 Main St");

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("john.doe@example.com");
        user.setUserAccount(userAccount);

        expectedProfile = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1985, 5, 20))
                .phone("1234567890")
                .address("123 Main St")
                .build();
    }

    @Test
    public void testGetProfile_UserExists() {
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        ProfileDTO result = profileService.getProfile(1L);

        assertAll(
                () -> assertNotNull(result, "Profile should not be null"),
                () -> assertEquals(expectedProfile.getEmail(), result.getEmail(), "Emails should match"),
                () -> assertEquals(expectedProfile.getFirstname(), result.getFirstname(), "First names should match"),
                () -> assertEquals(expectedProfile.getLastname(), result.getLastname(), "Last names should match"),
                () -> assertEquals(expectedProfile.getBirthdate(), result.getBirthdate(), "Birthdates should match"),
                () -> assertEquals(expectedProfile.getPhone(), result.getPhone(), "Phones should match"),
                () -> assertEquals(expectedProfile.getAddress(), result.getAddress(), "Addresses should match")
        );
    }

    @Test
    public void testGetProfile_UserNotFound() {
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            profileService.getProfile(1L);
        });

        assertTrue(exception.getMessage().contains("No value present"), "Should throw NoSuchElementException");
    }

    @Test
    public void testSaveProfile_Success() {
        // Given
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email("email@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .phone("1234567890")
                .address("1234 Street Name").build();
        UserAccount userAccount = new UserAccount();
        User user = new User();

        // When
        when(userAccountService.findByEmail(anyString())).thenReturn(Optional.of(userAccount));
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        User result = profileService.saveProfile(profileDTO);

        // Then
        verify(userAccountService).addUserAccount(any(UserAccount.class));
        verify(userService).addUser(any(User.class));
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
    }

    @Test
    public void testSaveProfile_UserAccountNotFound() {
        // Given
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email("wrongemail@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .phone("1234567890")
                .address("1234 Street Name").build();

        // When
        when(userAccountService.findByEmail(anyString())).thenReturn(Optional.empty());

        // Then
        assertThrows(UsernameNotFoundException.class, () -> {
            profileService.saveProfile(profileDTO);
        });
    }

    @Test
    public void testSaveProfile_UserNotFound() {
        // Given
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email("email@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .phone("1234567890")
                .address("1234 Street Name").build();
        UserAccount userAccount = new UserAccount();

        // When
        when(userAccountService.findByEmail(anyString())).thenReturn(Optional.of(userAccount));
        when(userService.getUserIdByEmail(anyString())).thenReturn(1L);
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(UsernameNotFoundException.class, () -> {profileService.saveProfile(profileDTO);});
    }
}
