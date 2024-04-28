package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {

    @Autowired
    private UserAccount userAccount;

    @BeforeEach
    public void setUp() {
        userAccount = new UserAccount();
    }

    @Test
    public void testUserAccountIdGetterAndSetter() {
        // Given
        Long userAccountId = 123L;

        // When
        userAccount.setUserAccountId(userAccountId);

        // Then
        assertEquals(userAccountId, userAccount.getUserAccountId());
    }

    @Test
    public void testEmailGetterAndSetter() {
        // Given
        String email = "test@example.com";

        // When
        userAccount.setEmail(email);

        // Then
        assertEquals(email, userAccount.getEmail());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        // Given
        String password = "password";

        // When
        userAccount.setPassword(password);

        // Then
        assertEquals(password, userAccount.getPassword());
    }

    @Test
    public void testLastConnectionDateGetterAndSetter() {
        // Given
        LocalDateTime lastConnectionDate = LocalDateTime.now();

        // When
        userAccount.setLastConnectionDate(lastConnectionDate);

        // Then
        assertEquals(lastConnectionDate, userAccount.getLastConnectionDate());
    }

    @Test
    public void testIsActiveGetterAndSetter() {
        // Given
        boolean isActive = true;

        // When
        userAccount.setIsActive(isActive);

        // Then
        assertEquals(isActive, userAccount.getIsActive());
    }

    @Test
    public void testRoleGetterAndSetter() {
        // Given
        String role = "USER";

        // When
        userAccount.setRole(role);

        // Then
        assertEquals(role, userAccount.getRole());
    }

    @Test
    public void testBuilder() {
        // Given
        String email = "test@example.com";
        String password = "password";
        LocalDateTime lastConnectionDate = LocalDateTime.now();
        boolean isActive = true;
        String role = "USER";

        // When
        UserAccount builtUserAccount = UserAccount.builder()
                .email(email)
                .password(password)
                .lastConnectionDate(lastConnectionDate)
                .isActive(isActive)
                .role(role)
                .build();

        // Then
        assertNotNull(builtUserAccount);
        assertEquals(email, builtUserAccount.getEmail());
        assertEquals(password, builtUserAccount.getPassword());
        assertEquals(lastConnectionDate, builtUserAccount.getLastConnectionDate());
        assertEquals(isActive, builtUserAccount.getIsActive());
        assertEquals(role, builtUserAccount.getRole());
    }
}
