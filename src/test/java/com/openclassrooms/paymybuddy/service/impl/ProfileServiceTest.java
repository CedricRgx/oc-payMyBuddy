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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
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
        user = User.builder()
                .firstname("John")
                .lastname("Doe")
                        .birthdate(LocalDate.of(1985, 5, 20))
                .phone("1234567890")
                .address("123 Main St")
                .build();

        UserAccount userAccount = UserAccount.builder()
                .email("john.doe@example.com")
                .build();
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
        // When
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));
        ProfileDTO result = profileService.getProfile(1L);

        // Then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedProfile.getEmail(), result.getEmail()),
                () -> assertEquals(expectedProfile.getFirstname(), result.getFirstname()),
                () -> assertEquals(expectedProfile.getLastname(), result.getLastname()),
                () -> assertEquals(expectedProfile.getBirthdate(), result.getBirthdate()),
                () -> assertEquals(expectedProfile.getPhone(), result.getPhone()),
                () -> assertEquals(expectedProfile.getAddress(), result.getAddress())
        );
    }

    @Test
    public void testGetProfile_UserNotFound() {
        // When
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            profileService.getProfile(1L);
        });
        assertTrue(exception.getMessage().contains("No value present"));
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
        User user = User.builder().build();

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
