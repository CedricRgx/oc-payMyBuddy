package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

    @Mock
    private Transaction transaction;

    @Mock
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

    @Test
    public void hashCode_SameObject_ReturnsSameHashCode() {
        // Given
        LocalDateTime transactionDate = LocalDateTime.now();
        User author = new User();

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Test transaction")
                .transactionDate(transactionDate)
                .fee(10.0)
                .author(author)
                .build();

        // When
        int hashCode1 = transaction1.hashCode();
        int hashCode2 = transaction1.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_DifferentObjectsWithSameProperties_ReturnsSameHashCode() {
        // Given
        LocalDateTime transactionDate = LocalDateTime.now();
        User author = new User();

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Test transaction")
                .transactionDate(transactionDate)
                .fee(10.0)
                .author(author)
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Test transaction")
                .transactionDate(transactionDate)
                .fee(10.0)
                .author(author)
                .build();

        // When
        int hashCode1 = transaction1.hashCode();
        int hashCode2 = transaction2.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_DifferentObjectsWithDifferentProperties_ReturnsDifferentHashCodes() {
        // Given
        LocalDateTime transactionDate = LocalDateTime.now();
        User author1 = new User();
        User author2 = new User();

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Test transaction")
                .transactionDate(transactionDate)
                .fee(10.0)
                .author(author1)
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionId(2L)
                .amount(150.0)
                .description("Another test transaction")
                .transactionDate(transactionDate)
                .fee(20.0)
                .author(author2)
                .build();

        // When
        int hashCode1 = transaction1.hashCode();
        int hashCode2 = transaction2.hashCode();

        // Then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void equals_ReturnsTrue_WhenSameObject() {
        // Given
        Transaction transaction = Transaction.builder().build();

        // When Then
        assertTrue(transaction.equals(transaction));
    }

    @Test
    public void equals_ReturnsFalse_WhenNullObject() {
        // Given
        Transaction transaction = Transaction.builder().build();

        // When Then
        assertFalse(transaction.equals(null));
    }

    @Test
    public void equals_ReturnsFalse_WhenDifferentClass() {
        // Given
        Transaction transaction = Transaction.builder().build();
        Object obj = new Object();

        // When Then
        assertFalse(transaction.equals(obj));
    }

    @Test
    public void equals_ReturnsTrue_WhenSameId() {
        // Given
        Long transactionId = 1L;
        Transaction transaction1 = Transaction.builder().transactionId(transactionId).build();
        Transaction transaction2 = Transaction.builder().transactionId(transactionId).build();

        // When Then
        assertTrue(transaction1.equals(transaction2));
    }

    @Test
    public void equals_ReturnsFalse_WhenDifferentId() {
        // Given
        Transaction transaction1 = Transaction.builder().transactionId(1L).build();
        Transaction transaction2 = Transaction.builder().transactionId(2L).build();

        // When Then
        assertFalse(transaction1.equals(transaction2));
    }

    @Test
    public void equals_ReturnsTrue_WhenBothIdsNull() {
        // Given
        Transaction transaction1 = Transaction.builder().build();
        Transaction transaction2 = Transaction.builder().build();

        // When Then
        assertTrue(transaction1.equals(transaction2));
    }

    @Test
    public void testEquals_SameValues_AssertTrue() {
        // Given
        User author = new User();
        author.setUserId(1L);

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Payment")
                .transactionDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .fee(5.0)
                .author(author)
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Payment")
                .transactionDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .fee(5.0)
                .author(author)
                .build();

        // When Then
        assertTrue(transaction1.equals(transaction2));
    }

    @Test
    public void testEquals_DifferentValues_AssertFalse() {
        // Given
        User author1 = new User();
        author1.setUserId(1L);
        User author2 = new User();
        author2.setUserId(2L);

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Payment")
                .transactionDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .fee(5.0)
                .author(author1)
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Payment")
                .transactionDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .fee(5.0)
                .author(author2)
                .build();

        // When Then
        assertFalse(transaction1.equals(transaction2));
    }

    @Test
    public void testHashCode_ConsistencyCheck() {
        // Given
        User author = new User();
        author.setUserId(1L);

        Transaction transaction = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Payment")
                .transactionDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .fee(5.0)
                .author(author)
                .build();

        int hashCode1 = transaction.hashCode();
        int hashCode2 = transaction.hashCode();

        // When Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testHashCode_DifferentObjects_AssertNotEquals() {
        // Given
        User author = new User();
        author.setUserId(1L);

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .amount(100.0)
                .description("Payment")
                .transactionDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .fee(5.0)
                .author(author)
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionId(2L)
                .amount(150.0)
                .description("Refund")
                .transactionDate(LocalDateTime.of(2023, 10, 2, 12, 0))
                .fee(2.0)
                .author(author)
                .build();

        // When Then
        assertNotEquals(transaction1.hashCode(), transaction2.hashCode());
    }

}
