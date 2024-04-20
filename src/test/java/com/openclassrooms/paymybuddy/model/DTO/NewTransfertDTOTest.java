package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the NewTransfertDTO class.
 */
@Slf4j
public class NewTransfertDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRecipientIdSetter() {
        // Given
        Long recipientId = 123L;
        NewTransfertDTO dto = new NewTransfertDTO(recipientId, "Test description", 100.0);

        // When
        dto.setRecipientId(recipientId);

        // Then
        assertEquals(recipientId, dto.getRecipientId());
    }

    @Test
    public void testRecipientIdGetter() {
        // Given
        Long recipientId = 123L;
        NewTransfertDTO dto = new NewTransfertDTO(recipientId, "Test description", 100.0);

        // When
        Long retrievedRecipientId = dto.getRecipientId();

        // Then
        assertEquals(recipientId, retrievedRecipientId);
    }

    @Test
    public void testDescriptionSetter() {
        // Given
        String description = "Test description";
        NewTransfertDTO dto = new NewTransfertDTO(123L, description, 100.0);

        // When
        dto.setDescription(description);

        // Then
        assertEquals(description, dto.getDescription());
    }

    @Test
    public void testDescriptionGetter() {
        // Given
        String description = "Test description";
        NewTransfertDTO dto = new NewTransfertDTO(123L, description, 100.0);

        // When
        String retrievedDescription = dto.getDescription();

        // Then
        assertEquals(description, retrievedDescription);
    }

    @Test
    public void testAmountSetter() {
        // Given
        double amount = 100.0;
        NewTransfertDTO dto = new NewTransfertDTO(123L, "Test description", amount);

        // When
        dto.setAmount(amount);

        // Then
        assertEquals(amount, dto.getAmount());
    }

    @Test
    public void testAmountGetter() {
        // Given
        double amount = 100.0;
        NewTransfertDTO dto = new NewTransfertDTO(123L, "Test description", amount);

        // When
        double retrievedAmount = dto.getAmount();

        // Then
        assertEquals(amount, retrievedAmount);
    }

    @Test
    public void testRecipientIdNotNull() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .description("Test description")
                .amount(100.0)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "recipientId".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testDescriptionNotNull() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .amount(100.0)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "description".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testDescriptionNotEmpty() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("")
                .amount(100.0)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "description".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testDescriptionSize() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("A")
                .amount(100.0)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "description".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testAmountNotNull() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test description")
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "amount".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testAmountMin() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test description")
                .amount(0.0)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "amount".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void hashCode_shouldReturnSameValueForEqualObjects() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();

        // When/Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void hashCode_shouldReturnDifferentValueForDifferentObjects() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(2L)
                .description("Another description")
                .amount(200.0)
                .build();

        // When/Then
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void toString_shouldReturnCorrectStringRepresentation() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();

        // When
        String result = dto.toString();

        // Then
        String expected = "NewTransfertDTO(recipientId=1, description=Transfer description, amount=100.0)";
        assertEquals(expected, result);
    }

    @Test
    public void equals_shouldReturnTrueWhenComparingWithSameInstance() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();

        // When
        boolean result = dto.equals(dto);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnTrueWhenComparingWithEqualInstance() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalseWhenComparingWithDifferentInstance() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(2L)
                .description("Another description")
                .amount(200.0)
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalseWhenComparingWithNull() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();

        // When
        boolean result = dto.equals(null);

        // Then
        assertFalse(result);
    }
}
