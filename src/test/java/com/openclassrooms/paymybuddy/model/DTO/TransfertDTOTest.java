package com.openclassrooms.paymybuddy.model.DTO;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the TransfertDTO class.
 */
@Slf4j
public class TransfertDTOTest {

    private TransfertDTO dto = TransfertDTO.builder()
            .recipientFirstname("John")
            .recipientLastname("Doe")
            .description("test description")
            .amount("50.00").build();

    @Test
    public void testRecipientFirstnameGetter() {
        // Given
        String firstname = "John";

        // When
        String retrievedFirstname = dto.getRecipientFirstname();

        // Then
        assertEquals(firstname, retrievedFirstname);
    }

    @Test
    public void testRecipientFirstnameSetter() {
        // Given
        String firstname = "Mary";

        // When
        dto.setRecipientFirstname(firstname);

        // Then
        assertEquals(firstname, dto.getRecipientFirstname());
    }

    @Test
    public void testRecipientLastnameGetter() {
        // Given
        String lastname = "Doe";

        // When
        String retrievedLastname = dto.getRecipientLastname();

        // Then
        assertEquals(lastname, retrievedLastname);
    }

    @Test
    public void testRecipientLastnameSetter() {
        // Given
        String lastname = "Shelley";

        // When
        dto.setRecipientLastname(lastname);

        // Then
        assertEquals(lastname, dto.getRecipientLastname());
    }

    @Test
    public void testDescriptionGetter() {
        // Given
        String description = "test description";

        // When
        String retrievedDescription = dto.getDescription();

        // Then
        assertEquals(description, retrievedDescription);
    }

    @Test
    public void testDescriptionSetter() {
        // Given
        String description = "new test description";

        // When
        dto.setDescription(description);

        // Then
        assertEquals(description, dto.getDescription());
    }

    @Test
    public void testAmountGetter() {
        // Given
        String amount = "50.00";

        // When
        String retrievedAmount = dto.getAmount();

        // Then
        assertEquals(amount, retrievedAmount);
    }

    @Test
    public void testAmountSetter() {
        // Given
        String amount = "999.00";

        // When
        dto.setAmount(amount);

        // Then
        assertEquals(amount, dto.getAmount());
    }

    @Test
    public void hashCode_shouldBeEqualForEqualObjects() {
        // Given
        TransfertDTO dto1 = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        TransfertDTO dto2 = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_shouldBeDifferentForDifferentObjects() {
        // Given
        TransfertDTO dto1 = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        TransfertDTO dto2 = TransfertDTO.builder()
                .recipientFirstname("Jane")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void toString_shouldReturnExpectedString() {
        // Given
        TransfertDTO dto = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        String expectedString = "TransfertDTO(recipientFirstname=John, recipientLastname=Doe, description=Expense reimbursement, amount=50.00)";

        // When
        String actualString = dto.toString();

        // Then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void equals_shouldReturnTrueWhenObjectsAreEqual() {
        // Given
        TransfertDTO dto1 = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        TransfertDTO dto2 = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalseWhenObjectsAreNotEqual() {
        // Given
        TransfertDTO dto1 = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        TransfertDTO dto2 = TransfertDTO.builder()
                .recipientFirstname("Jane")
                .recipientLastname("Smith")
                .description("Loan repayment")
                .amount("100.00")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalseWhenObjectIsNull() {
        // Given
        TransfertDTO dto = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        // When
        boolean result = dto.equals(null);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalseWhenObjectIsOfDifferentType() {
        // Given
        TransfertDTO dto = TransfertDTO.builder()
                .recipientFirstname("John")
                .recipientLastname("Doe")
                .description("Expense reimbursement")
                .amount("50.00")
                .build();

        String differentTypeObject = "This is a string";

        // When
        boolean result = dto.equals(differentTypeObject);

        // Then
        assertFalse(result);
    }

}