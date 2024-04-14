package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the deposit class.
 */
@Slf4j
public class DepositTest {

    private Deposit deposit;
    private User mockAuthor;

    @BeforeEach
    public void setUp() {
        mockAuthor = new User();
        deposit = Deposit.builder()
                .amount(100.0)
                .description("Test deposit")
                .transactionDate(LocalDateTime.now())
                .fee(0.0) // Assuming no fee for deposits
                .author(mockAuthor)
                .build();
    }

    @Test
    public void testInheritance() {
        // When
        Long actualTransactionId = deposit.getTransactionId();
        double actualAmount = deposit.getAmount();
        String actualDescription = deposit.getDescription();
        LocalDateTime actualTransactionDate = deposit.getTransactionDate();
        double actualFee = deposit.getFee();
        User actualAuthor = deposit.getAuthor();

        // Then
        assertEquals(null, actualTransactionId);
        assertEquals(100.0, actualAmount);
        assertEquals("Test deposit", actualDescription);
        assertTrue(actualTransactionDate.isBefore(LocalDateTime.now()) || actualTransactionDate.isEqual(LocalDateTime.now()));
        assertEquals(0.0, actualFee);
        assertEquals(mockAuthor, actualAuthor);
    }
}