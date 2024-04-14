package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * unit tests for the transaction class.
 */
@Slf4j
public class TransactionTest {

    private Transaction transaction;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        transaction = Transaction.builder()
                .amount(100.0)
                .description("Test transaction")
                .transactionDate(LocalDateTime.now())
                .fee(5.0)
                .author(mockUser)
                .build();
    }

    @Test
    public void testGetTransactionId() {
        // Given
        Long expectedId = null; // Transaction's id is generated automatically.

        // When
        Long actualId = transaction.getTransactionId();

        // Then
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetTransactionId() {
        // Given
        Long newId = 123L;

        // When
        transaction.setTransactionId(newId);

        // Then
        assertEquals(newId, transaction.getTransactionId());
    }

    @Test
    public void testGetAmount() {
        // Given
        double expectedAmount = 100.0;

        // When
        double actualAmount = transaction.getAmount();

        // Then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void testSetAmount() {
        // Given
        double newAmount = 200.0;

        // When
        transaction.setAmount(newAmount);

        // Then
        assertEquals(newAmount, transaction.getAmount());
    }

    @Test
    public void testGetDescription() {
        // Given
        String expectedDescription = "Test transaction";

        // When
        String actualDescription = transaction.getDescription();

        // Then
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testSetDescription() {
        // Given
        String newDescription = "New test transaction";

        // When
        transaction.setDescription(newDescription);

        // Then
        assertEquals(newDescription, transaction.getDescription());
    }

    @Test
    public void testGetTransactionDate() {
        // When
        LocalDateTime actualDate = transaction.getTransactionDate();

        // Then
        assertTrue(actualDate.isBefore(LocalDateTime.now()) || actualDate.isEqual(LocalDateTime.now()));
    }

    @Test
    public void testSetTransactionDate() {
        // Given
        LocalDateTime newDate = LocalDateTime.of(2022, 5, 1, 12, 0);

        // When
        transaction.setTransactionDate(newDate);

        // Then
        assertEquals(newDate, transaction.getTransactionDate());
    }

    @Test
    public void testGetFee() {
        // Given
        double expectedFee = 5.0;

        // When
        double actualFee = transaction.getFee();

        // Then
        assertEquals(expectedFee, actualFee);
    }

    @Test
    public void testSetFee() {
        // Given
        double newFee = 10.0;

        // When
        transaction.setFee(newFee);

        // Then
        assertEquals(newFee, transaction.getFee());
    }

    @Test
    public void testGetAuthor() {
        // When
        User actualAuthor = transaction.getAuthor();

        // Then
        assertEquals(mockUser, actualAuthor);
    }

    @Test
    public void testSetAuthor() {
        // Given
        User newAuthor = new User();

        // When
        transaction.setAuthor(newAuthor);

        // Then
        assertEquals(newAuthor, transaction.getAuthor());
    }
}
