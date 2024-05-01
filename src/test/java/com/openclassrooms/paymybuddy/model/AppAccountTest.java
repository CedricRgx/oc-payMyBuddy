package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppAccountTest {

    @Mock
    private AppAccount appAccount;

    @Mock
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
        Long expectedId = null;

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

    @Test
    public void testHashCodeWithDifferentBalances() {
        // Given
        AppAccount account1 = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
        AppAccount account2 = AppAccount.builder()
                .balance(200.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertNotEquals(account1.hashCode(), account2.hashCode());
    }

    @Test
    public void testHashCodeWithNullAppOwner() {
        // Given
        AppAccount accountWithNullOwner = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();

        // When Then
        assertDoesNotThrow(accountWithNullOwner::hashCode);
    }

    @Test
    public void testHashCodeEffectOfEachField() {
        // Given
        AppAccount baseAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
        AppAccount changedBalance = AppAccount.builder()
                .balance(200.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertNotEquals(baseAccount.hashCode(), changedBalance.hashCode());
    }

    @Test
    public void testEqualsSymmetry() {
        // Given
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertTrue(appAccount.equals(anotherAppAccount) && anotherAppAccount.equals(appAccount));
    }

    @Test
    public void testEqualsTransitivity() {
        // Given
        AppAccount firstAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
        AppAccount secondAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
        AppAccount thirdAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertTrue(firstAccount.equals(secondAccount) && secondAccount.equals(thirdAccount) && firstAccount.equals(thirdAccount));
    }

    @Test
    public void testEqualsConsistency() {
        // Given
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertTrue(appAccount.equals(anotherAppAccount) && appAccount.equals(anotherAppAccount));
    }

    @Test
    public void testEqualsAllFieldsIdentical() {
        // Given
        AppAccount identicalAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertTrue(appAccount.equals(identicalAccount));
    }

    @Test
    public void testHashCodeConsistency() {
        // Given
        int initialHashCode = appAccount.hashCode();

        // When Then
        assertEquals(initialHashCode, appAccount.hashCode());
    }

    @Test
    public void testHashCodeEqualityConsistency() {
        // Given
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertTrue(appAccount.equals(anotherAppAccount));
        assertEquals(appAccount.hashCode(), anotherAppAccount.hashCode());
    }

    @Test
    public void testHashCodeDifference() {
        // Given
        AppAccount differentAppAccount = AppAccount.builder()
                .balance(200.0)
                .appOwner(new User())
                .build();

        // When Then
        assertNotEquals(appAccount.hashCode(), differentAppAccount.hashCode());
    }

    @Test
    public void testHashCodeWithNullFields() {
        // Given
        AppAccount accountWithNullOwner = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();

        // When Then
        assertDoesNotThrow(accountWithNullOwner::hashCode);
    }

    @Test
    public void testEqualsDifferentBalances() {
        // Given
        AppAccount account1 = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
        AppAccount account2 = AppAccount.builder()
                .balance(200.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertFalse(account1.equals(account2));
    }

    @Test
    public void testEqualsWithAllFieldsNull() {
        // Given
        AppAccount account1 = new AppAccount();
        AppAccount account2 = new AppAccount();

        // When Then
        assertTrue(account1.equals(account2));
    }

    @Test
    public void testSetAndGetNegativeBalance() {
        // Given
        double negativeBalance = -50.0;
        appAccount.setBalance(negativeBalance);

        // When Then
        assertEquals(negativeBalance, appAccount.getBalance(), "Balance should be set to negative when explicitly set.");
    }

    @Test
    public void testZeroBalanceInitialization() {
        // Given
        AppAccount newAccount = AppAccount.builder()
                .balance(0.0)
                .appOwner(mockUser)
                .build();

        // When Then
        assertEquals(0.0, newAccount.getBalance(), "Newly initialized account should allow zero balance.");
    }

}
