package com.openclassrooms.paymybuddy.model;

import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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

    @Test
    public void testTransactionDetails() {
        // Assert initial state is correct
        assertAll("Ensure all transaction details are correctly initialized",
                () -> assertEquals(100.0, transfert.getAmount(), "Amount should be initialized correctly"),
                () -> assertEquals("Test transfert", transfert.getDescription(), "Description should be initialized correctly"),
                () -> assertNotNull(transfert.getTransactionDate(), "Transaction date should not be null"),
                () -> assertEquals(5.0, transfert.getFee(), "Fee should be initialized correctly"),
                () -> assertEquals(mockAuthor, transfert.getAuthor(), "Author should match the mockAuthor"),
                () -> assertEquals(mockRecipient, transfert.getRecipient(), "Recipient should match the mockRecipient")
        );
    }

    @Test
    public void testTransactionDateBehavior() {
        // Given a transaction date set to the exact current time
        LocalDateTime now = LocalDateTime.now();
        transfert.setTransactionDate(now);

        // When/Then
        assertTrue(transfert.getTransactionDate().isEqual(now), "Transaction date should exactly match the set value");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        Transfert anotherTransfert = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(transfert.getTransactionDate())
                .fee(5.0)
                .author(mockAuthor)
                .recipient(mockRecipient)
                .build();

        // When/Then
        assertAll("Test equals and hash code",
                () -> assertEquals(transfert, anotherTransfert, "Transfert should be equal to anotherTransfert with the same attributes"),
                () -> assertEquals(transfert.hashCode(), anotherTransfert.hashCode(), "Hash codes should be equal for equal objects")
        );
    }
    @Test
    public void testAllGettersAndSetters() {
        // Set new values to all fields and check if getters retrieve them correctly
        User anotherUser = new User();
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        transfert.setAmount(200.0);
        transfert.setDescription("Updated description");
        transfert.setTransactionDate(newDate);
        transfert.setFee(10.0);
        transfert.setAuthor(anotherUser);
        transfert.setRecipient(anotherUser);

        assertAll(
                () -> assertEquals(200.0, transfert.getAmount(), "Should get the correct amount"),
                () -> assertEquals("Updated description", transfert.getDescription(), "Should get the correct description"),
                () -> assertEquals(newDate, transfert.getTransactionDate(), "Should get the correct transaction date"),
                () -> assertEquals(10.0, transfert.getFee(), "Should get the correct fee"),
                () -> assertEquals(anotherUser, transfert.getAuthor(), "Should get the correct author"),
                () -> assertEquals(anotherUser, transfert.getRecipient(), "Should get the correct recipient")
        );
    }

    @Test
    public void testEquals() {
        Transfert identicalTransfert = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(transfert.getTransactionDate())
                .fee(5.0)
                .author(mockAuthor)
                .recipient(mockRecipient)
                .build();

        assertEquals(transfert, identicalTransfert, "Two transfers with the same attributes should be equal");
    }


    @Test
    public void testHashCode() {
        Transfert sameAttributesTransfert = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(transfert.getTransactionDate())
                .fee(5.0)
                .author(mockAuthor)
                .recipient(mockRecipient)
                .build();

        assertEquals(transfert.hashCode(), sameAttributesTransfert.hashCode(), "Hash code should be the same for identical attributes");
    }


    @Test
    public void testEquals_NullAndNonNullAmount() {
        Transfert dto1 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(User.builder().build())
                .build();
        Transfert dto2 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(User.builder().build())
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullTransactionDate() {
        Transfert dto1 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(null)
                .fee(5.0)
                .author(User.builder().build())
                .recipient(User.builder().build())
                .build();
        Transfert dto2 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(User.builder().build())
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullAuthor() {
        Transfert dto1 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(null)
                .recipient(User.builder().build())
                .build();
        Transfert dto2 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(User.builder().build())
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullRecipient() {
        Transfert dto1 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(null)
                .build();
        Transfert dto2 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(null)
                .build();

        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullRecipient() {
        Transfert dto1 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(null)
                .build();
        Transfert dto2 = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(LocalDateTime.of(1990, 1, 1, 20, 20))
                .fee(5.0)
                .author(User.builder().build())
                .recipient(User.builder().build())
                .build();
        assertFalse(dto1.equals(dto2));
    }

}