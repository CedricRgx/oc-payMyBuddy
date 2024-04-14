package com.openclassrooms.paymybuddy.model.DTO;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}