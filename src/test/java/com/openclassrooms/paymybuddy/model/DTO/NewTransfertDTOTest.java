package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
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
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(recipientId)
                .description("Test description")
                .amount(100.0).build();

        // When
        dto.setRecipientId(recipientId);

        // Then
        assertEquals(recipientId, dto.getRecipientId());
    }

    @Test
    public void testRecipientIdGetter() {
        // Given
        Long recipientId = 123L;
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(recipientId)
                .description("Test description")
                .amount(100.0).build();

        // When
        Long retrievedRecipientId = dto.getRecipientId();

        // Then
        assertEquals(recipientId, retrievedRecipientId);
    }

    @Test
    public void testDescriptionSetter() {
        // Given
        String description = "Test description";
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(123L)
                .description("Test description")
                .amount(100.0).build();

        // When
        dto.setDescription(description);

        // Then
        assertEquals(description, dto.getDescription());
    }

    @Test
    public void testDescriptionGetter() {
        // Given
        String description = "Test description";
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(123L)
                .description("Test description")
                .amount(100.0).build();

        // When
        String retrievedDescription = dto.getDescription();

        // Then
        assertEquals(description, retrievedDescription);
    }

    @Test
    public void testAmountSetter() {
        // Given
        double amount = 100.0;
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(123L)
                .description("Test description")
                .amount(amount).build();

        // When
        dto.setAmount(amount);

        // Then
        assertEquals(amount, dto.getAmount());
    }

    @Test
    public void testAmountGetter() {
        // Given
        double amount = 100.0;
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(123L)
                .description("Test description")
                .amount(amount).build();

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
    public void hashCode_shouldBeConsistent() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();

        // When
        int initialHashCode = dto.hashCode();

        // Then
        assertEquals(initialHashCode, dto.hashCode());
    }

    @Test
    public void hashCode_shouldDifferWhenFieldsDiffer() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(200.0)
                .build();

        // When/Then
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void hashCode_withNullFields() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(null)
                .description("Transfer description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description(null)
                .amount(100.0)
                .build();

        // Then
        assertDoesNotThrow(dto1::hashCode);
        assertDoesNotThrow(dto2::hashCode);
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

    @Test
    public void equals_shouldReturnFalseForDifferentClass() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Transfer description")
                .amount(100.0)
                .build();
        Object other = new Object();

        // When
        boolean result = dto.equals(other);

        // Then
        assertFalse(result);
    }

    @Test
    public void hashCode_shouldReflectChangesInEveryField() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Description")
                .amount(100.0)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Description modified")
                .amount(100.0)
                .build();
        NewTransfertDTO dto3 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Description")
                .amount(200.0)
                .build();

        // Then
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    public void testAmountPositive() {
        // Given
        NewTransfertDTO dto = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Valid description")
                .amount(-1.0)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "amount".equals(v.getPropertyPath().toString()) && v.getMessage().contains("must be greater than 0")));
    }

    @Test
    public void performanceTestForHashCodeAndEquals() {
        // Given
        List<NewTransfertDTO> dtos = IntStream.range(0, 100000)
                .mapToObj(i -> NewTransfertDTO.builder()
                        .recipientId((long) i)
                        .description("Description " + i)
                        .amount(100.0 + i)
                        .build())
                .collect(Collectors.toList());

        // When
        long startTime = System.nanoTime();
        dtos.forEach(dto -> dtos.get(0).equals(dto));
        long endTime = System.nanoTime();

        // Then
        System.out.println("Duration for 100,000 equals operations: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    @Test
    public void equals_withAllNullFields() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(null)
                .description(null)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(null)
                .description(null)
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_withMixedNullFields() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(null)
                .description("description")
                .amount(100.00)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description(null)
                .amount(100.00)
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_symmetry() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();

        // Then
        assertTrue(dto1.equals(dto2) && dto2.equals(dto1));
    }

    @Test
    public void equals_transitivity() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();
        NewTransfertDTO dto3 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();

        // Then
        assertTrue(dto1.equals(dto2) && dto2.equals(dto3) && dto1.equals(dto3));
    }

    @Test
    public void equals_consistency() {
        // Given
        NewTransfertDTO dto1 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();
        NewTransfertDTO dto2 = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("Test")
                .amount(100.00)
                .build();

        // Then
        assertTrue(dto1.equals(dto2) && dto1.equals(dto2));
    }

    @Test
    public void equals_whenOnlyOneFieldDiffers_eachFieldTested() {
        // Given
        NewTransfertDTO base = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("description")
                .amount(100.00)
                .build();
        NewTransfertDTO diffRecipientId = NewTransfertDTO.builder()
                .recipientId(2L)
                .description("description")
                .amount(100.00)
                .build();
        NewTransfertDTO diffDescription = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("different")
                .amount(100.00)
                .build();
        NewTransfertDTO diffAmount = NewTransfertDTO.builder()
                .recipientId(1L)
                .description("description")
                .amount(200.00)
                .build();

        // When Then
        assertFalse(base.equals(diffRecipientId));
        assertFalse(base.equals(diffDescription));
        assertFalse(base.equals(diffAmount));
    }

}
