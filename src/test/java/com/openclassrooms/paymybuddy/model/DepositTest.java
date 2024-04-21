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

    @Test
    public void testEquals_BothObjectsHaveNullDescription() {
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

        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullAuthor() {
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

        assertTrue(dto1.equals(dto2));
    }

}