package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransfertTest {

    private Transfert transfert;
    private User mockAuthor;
    private User mockRecipient;

    @BeforeEach
    public void setUp() {
        mockAuthor = User.builder().build();
        mockRecipient = User.builder().build();
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
        //assertTrue(actualTransactionDate.isBefore(LocalDateTime.now()) || actualTransactionDate.isEqual(LocalDateTime.now()));
        assertEquals(5.0, actualFee);
        assertEquals(mockAuthor, actualAuthor);
    }

    @Test
    public void testTransactionDetails() {
        // Then
        assertAll("Ensure all transaction details are correctly initialized",
                () -> assertEquals(100.0, transfert.getAmount()),
                () -> assertEquals("Test transfert", transfert.getDescription()),
                () -> assertNotNull(transfert.getTransactionDate()),
                () -> assertEquals(5.0, transfert.getFee()),
                () -> assertEquals(mockAuthor, transfert.getAuthor()),
                () -> assertEquals(mockRecipient, transfert.getRecipient())
        );
    }

    @Test
    public void testTransactionDateBehavior() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        transfert.setTransactionDate(now);

        // When Then
        assertTrue(transfert.getTransactionDate().isEqual(now));
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

        // When Then
        assertAll("Test equals and hash code",
                () -> assertEquals(transfert, anotherTransfert),
                () -> assertEquals(transfert.hashCode(), anotherTransfert.hashCode())
        );
    }
    @Test
    public void testAllGettersAndSetters() {
        // Given
        User anotherUser = User.builder().build();
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        transfert.setAmount(200.0);
        transfert.setDescription("Updated description");
        transfert.setTransactionDate(newDate);
        transfert.setFee(10.0);
        transfert.setAuthor(anotherUser);
        transfert.setRecipient(anotherUser);

        // When Then
        assertAll(
                () -> assertEquals(200.0, transfert.getAmount()),
                () -> assertEquals("Updated description", transfert.getDescription()),
                () -> assertEquals(newDate, transfert.getTransactionDate()),
                () -> assertEquals(10.0, transfert.getFee()),
                () -> assertEquals(anotherUser, transfert.getAuthor()),
                () -> assertEquals(anotherUser, transfert.getRecipient())
        );
    }

    @Test
    public void testEquals() {
        // Given
        Transfert identicalTransfert = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(transfert.getTransactionDate())
                .fee(5.0)
                .author(mockAuthor)
                .recipient(mockRecipient)
                .build();

        // When Then
        assertEquals(transfert, identicalTransfert);
    }

    @Test
    public void testHashCode() {
        // Given
        Transfert sameAttributesTransfert = Transfert.builder()
                .amount(100.0)
                .description("Test transfert")
                .transactionDate(transfert.getTransactionDate())
                .fee(5.0)
                .author(mockAuthor)
                .recipient(mockRecipient)
                .build();

        // When Then
        assertEquals(transfert.hashCode(), sameAttributesTransfert.hashCode());
    }

    @Test
    public void testEquals_NullAndNonNullAmount() {
        // Given
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

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullTransactionDate() {
        // Given
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

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullAuthor() {
        // Given
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

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullRecipient() {
        // Given
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

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullRecipient() {
        // Given
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

        // When Then
        assertFalse(dto1.equals(dto2));
    }

}