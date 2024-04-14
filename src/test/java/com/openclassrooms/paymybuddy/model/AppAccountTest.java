package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the app account class.
 */
@Slf4j
public class AppAccountTest {

    private AppAccount appAccount;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        appAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
    }

    @Test
    public void testGetAppAccountId() {
        // Given
        Long expectedId = null; // AppAccount's id is generated automatically.

        // When
        Long actualId = appAccount.getAppAccountId();

        // Then
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetAppAccountId() {
        // Given
        Long newId = 123L;

        // When
        appAccount.setAppAccountId(newId);

        // Then
        assertEquals(newId, appAccount.getAppAccountId());
    }

    @Test
    public void testGetBalance() {
        // Given
        double expectedBalance = 100.0;

        // When
        double actualBalance = appAccount.getBalance();

        // Then
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testSetBalance() {
        // Given
        double newBalance = 200.0;

        // When
        appAccount.setBalance(newBalance);

        // Then
        assertEquals(newBalance, appAccount.getBalance());
    }

    @Test
    public void testEqualsSameInstance() {
        // Given
        AppAccount sameInstance = appAccount;

        // When
        boolean result = appAccount.equals(sameInstance);

        // Then
        assertEquals(true, result);
    }

    @Test
    public void testEqualsSameValues() {
        // Given
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When
        boolean result = appAccount.equals(anotherAppAccount);

        // Then
        assertEquals(true, result);
    }

    @Test
    public void testEqualsDifferentValues() {
        // Given
        AppAccount differentAppAccount = AppAccount.builder()
                .balance(200.0)
                .appOwner(new User())
                .build();

        // When
        boolean result = appAccount.equals(differentAppAccount);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testEqualsNullObject() {
        // Given
        AppAccount nullAppAccount = null;

        // When
        boolean result = appAccount.equals(nullAppAccount);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testEqualsDifferentClass() {
        // Given
        Object differentObject = new Object();

        // When
        boolean result = appAccount.equals(differentObject);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testHashCode() {
        // Given
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When
        int hashCode1 = appAccount.hashCode();
        int hashCode2 = anotherAppAccount.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

}
