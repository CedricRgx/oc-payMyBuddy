package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testEqualsSymmetry() {
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        assertTrue(appAccount.equals(anotherAppAccount) && anotherAppAccount.equals(appAccount), "Equals should be symmetric.");
    }

    @Test
    public void testEqualsTransitivity() {
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

        assertTrue(firstAccount.equals(secondAccount) && secondAccount.equals(thirdAccount) && firstAccount.equals(thirdAccount),
                "Equals should be transitive.");
    }

    @Test
    public void testEqualsConsistency() {
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        assertTrue(appAccount.equals(anotherAppAccount) && appAccount.equals(anotherAppAccount),
                "Equals should be consistent across multiple calls.");
    }

    @Test
    public void testEqualsAllFieldsIdentical() {
        AppAccount identicalAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();

        assertTrue(appAccount.equals(identicalAccount), "Equals should return true for identically constructed objects.");
    }

    @Test
    public void testHashCodeConsistency() {
        int initialHashCode = appAccount.hashCode();
        assertEquals(initialHashCode, appAccount.hashCode(), "Hash code should remain consistent across multiple calls.");
    }

    @Test
    public void testHashCodeEqualityConsistency() {
        AppAccount anotherAppAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(mockUser)
                .build();
        assertTrue(appAccount.equals(anotherAppAccount), "Both accounts should be equal.");
        assertEquals(appAccount.hashCode(), anotherAppAccount.hashCode(), "Hash codes must be equal for equal objects.");
    }

    @Test
    public void testHashCodeDifference() {
        AppAccount differentAppAccount = AppAccount.builder()
                .balance(200.0)
                .appOwner(new User())
                .build();
        assertNotEquals(appAccount.hashCode(), differentAppAccount.hashCode(), "Hash codes should ideally be different for non-equal objects.");
    }

    @Test
    public void testHashCodeWithNullFields() {
        AppAccount accountWithNullOwner = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();
        assertDoesNotThrow(accountWithNullOwner::hashCode, "Hash code calculation should handle null fields without throwing an exception.");
    }

}
