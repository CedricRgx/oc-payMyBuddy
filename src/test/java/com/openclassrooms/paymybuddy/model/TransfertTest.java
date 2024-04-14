package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the transfert class.
 */
@Slf4j
public class TransfertTest {

    private Transfert transfert;
    private User mockAuthor;
    private User mockRecipient;

    @BeforeEach
    public void setUp() {
        mockAuthor = new User();
        mockRecipient = new User();
        transfert = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.now())
                .fee(5.0)
                .author(mockAuthor)
                .recipient(mockRecipient)
                .build();
    }

    @Test
    public void testGetRecipient() {
        // When
        User actualRecipient = transfert.getRecipient();

        // Then
        assertEquals(mockRecipient, actualRecipient);
    }

    @Test
    public void testSetRecipient() {
        // Given
        User newRecipient = new User();

        // When
        transfert.setRecipient(newRecipient);

        // Then
        assertEquals(newRecipient, transfert.getRecipient());
    }

    @Test
    public void testInheritance() {
        // When
        Long actualTransactionId = transfert.getTransactionId();
        double actualAmount = transfert.getAmount();
        String actualDescription = transfert.getDescription();
        LocalDateTime actualTransactionDate = transfert.getTransactionDate();
        double actualFee = transfert.getFee();
        User actualAuthor = transfert.getAuthor();

        // Then
        assertEquals(null, actualTransactionId);
        assertEquals(100.0, actualAmount);
        assertEquals("Test transfert", actualDescription);
        assertTrue(actualTransactionDate.isBefore(LocalDateTime.now()) || actualTransactionDate.isEqual(LocalDateTime.now()));
        assertEquals(5.0, actualFee);
        assertEquals(mockAuthor, actualAuthor);
    }
}