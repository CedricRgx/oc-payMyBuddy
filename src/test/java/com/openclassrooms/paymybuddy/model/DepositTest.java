package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepositTest {

    @Mock
    private Deposit deposit;

    @Mock
    private User mockAuthor;

    @BeforeEach
    public void setUp() {
        mockAuthor = new User();
        deposit = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.of(2020,10,10, 10,10))
                .fee(0.0)
                .author(mockAuthor)
                .build();
    }

    @Test
    public void testInheritance() {
        // Given
        Long actualTransactionId = deposit.getTransactionId();
        double actualAmount = deposit.getAmount();
        String actualDescription = deposit.getDescription();
        LocalDateTime actualTransactionDate = deposit.getTransactionDate();
        double actualFee = deposit.getFee();
        User actualAuthor = deposit.getAuthor();

        // When Then
        assertEquals(null, actualTransactionId);
        assertEquals(100.0, actualAmount);
        assertEquals("Test deposit", actualDescription);
        assertTrue(actualTransactionDate.isEqual(LocalDateTime.of(2020,10,10, 10,10)));
        assertEquals(0.0, actualFee);
        assertEquals(mockAuthor, actualAuthor);
    }

    @Test
    public void testEquals_BothObjectsHaveNullDescription() {
        // Given
        Deposit dto1 = Deposit.builder()
                .amount(100.0)
                .description(null)
                .transactionDate(LocalDateTime.of(1990, 1, 1, 1, 1))
                .fee(0.0)
                .author(User.builder().build())
                .build();
        Deposit dto2 = Deposit.builder()
                .amount(100.0)
                .description(null)
                .transactionDate(LocalDateTime.of(1990, 1, 1, 1, 1))
                .fee(0.0)
                .author(User.builder().build())
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullAuthor() {
        // Given
        Deposit dto1 = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 1, 1))
                .fee(0.0)
                .author(null)
                .build();
        Deposit dto2 = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 1, 1))
                .fee(0.0)
                .author(null)
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEqualsReflexivity() {
        // When Then
        assertTrue(deposit.equals(deposit));
    }

    @Test
    public void testEqualsSymmetry() {
        // Given
        Deposit other = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.of(2020, 10, 10, 10, 10))
                .fee(0.0)
                .author(mockAuthor)
                .build();

        // When Then
        assertTrue(deposit.equals(other) && other.equals(deposit));
    }

    @Test
    public void testEqualsTransitivity() {
        // Given
        Deposit deposit1 = deposit;
        Deposit deposit2 = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.of(2020, 10, 10, 10, 10))
                .fee(0.0)
                .author(mockAuthor)
                .build();
        Deposit deposit3 = deposit2;

        // When Then
        assertTrue(deposit1.equals(deposit2) && deposit2.equals(deposit3) && deposit1.equals(deposit3));
    }

    @Test
    public void testEqualsConsistency() {
        // Given
        Deposit other = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.of(2020, 10, 10, 10, 10))
                .fee(0.0)
                .author(mockAuthor)
                .build();

        // When Then
        assertTrue(deposit.equals(other));
        assertTrue(deposit.equals(other));
    }

    @Test
    public void testEqualsNullComparison() {
        // When Then
        assertFalse(deposit.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        // When Then
        assertFalse(deposit.equals(new Object()));
    }

}