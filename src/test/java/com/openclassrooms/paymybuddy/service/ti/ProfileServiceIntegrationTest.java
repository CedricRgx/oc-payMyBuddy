package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.service.impl.ProfileService;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfileServiceIntegrationTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    private User user;
    private UserAccount userAccount;

    @BeforeEach
    public void setup() {
        userAccount = UserAccount.builder().build();
        userAccount.setEmail("test@example.com");
        userAccount.setPassword("password");
        userAccount.setRole("USER");
        userAccountService.addUserAccount(userAccount);

        user = User.builder().build();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setBirthdate(LocalDate.of(1990, 1, 1));
        user.setPhone("1234567890");
        user.setAddress("123 Main St");
        user.setUserAccount(userAccount);
        userService.addUser(user);
    }

    @Test
    public void testGetProfile() {
        // Given
        ProfileDTO profile = profileService.getProfile(user.getUserId());

        // When Then
        assertNotNull(profile);
        assertEquals("John", profile.getFirstname());
        assertEquals("Doe", profile.getLastname());
    }

}