package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PasswordUpdateDTOTest {

    private Validator validator;

    private PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
            .currentPassword("currentPassword")
            .newPassword("newPassword")
            .confirmPassword("confirmPassword")
            .build();

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCurrentPasswordGetter() {
        // Given
        String currentPassword = "currentPassword";

        // When
        String retrievedCurrentPassword = dto.getCurrentPassword();

        // Then
        assertEquals(currentPassword, retrievedCurrentPassword);
    }

    @Test
    public void testCurrentPasswordSetter() {
        // Given
        String currentPassword = "newCurrentPassword";

        // When
        dto.setCurrentPassword(currentPassword);

        // Then
        assertEquals(currentPassword, dto.getCurrentPassword());
    }

    @Test
    public void testNewPasswordGetter() {
        // Given
        String newPassword = "newPassword";

        // When
        String retrievedNewPassword = dto.getNewPassword();

        // Then
        assertEquals(newPassword, retrievedNewPassword);
    }

    @Test
    public void testNewPasswordSetter() {
        // Given
        String newPassword = "newNewPassword";

        // When
        dto.setNewPassword(newPassword);

        // Then
        assertEquals(newPassword, dto.getNewPassword());
    }

    @Test
    public void testConfirmPasswordGetter() {
        // Given
        String confirmPassword = "confirmPassword";

        // When
        String retrievedConfirmPassword = dto.getConfirmPassword();

        // Then
        assertEquals(confirmPassword, retrievedConfirmPassword);
    }

    @Test
    public void testConfirmPasswordSetter() {
        // Given
        String confirmPassword = "newConfirmPassword";

        // When
        dto.setConfirmPassword(confirmPassword);

        // Then
        assertEquals(confirmPassword, dto.getConfirmPassword());
    }

    @Test
    public void testCurrentPasswordNotNull() {
        // Given
        PasswordUpdateDTO dtoNotNull = new PasswordUpdateDTO(null, "newPassword", "newPassword");

        // When
        var violations = validator.validate(dtoNotNull);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "currentPassword".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testNewPasswordNotNull() {
        // Given
        PasswordUpdateDTO dtoNewNotNull = new PasswordUpdateDTO("currentPassword", null, "newPassword");

        // When
        var violations = validator.validate(dtoNewNotNull);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "newPassword".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testConfirmPasswordNotNull() {
        // Given
        PasswordUpdateDTO dtoConfirmNotNull = new PasswordUpdateDTO("currentPassword", "newPassword", null);

        // When
        var violations = validator.validate(dtoConfirmNotNull);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "confirmPassword".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testConfirmPasswordEqualsNewPassword() {
        // Given
        String currentPassword = "currentPassword";
        String newPassword = "newPassword";
        String confirmPassword = "newPassword";
        PasswordUpdateDTO dtoConfirmPassword = new PasswordUpdateDTO(currentPassword, newPassword, confirmPassword);

        // When
        var violations = validator.validate(dtoConfirmPassword);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void hashCode_shouldReturnEqualHashCodeForEqualObjects() {
        // Given
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_shouldReturnDifferentHashCodeForDifferentObjects() {
        // Given
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password456")
                .newPassword("newPassword456")
                .confirmPassword("newPassword456")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void toString_shouldReturnCorrectStringRepresentation() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        // When
        String expected = "PasswordUpdateDTO(currentPassword=password123, newPassword=newPassword123, confirmPassword=newPassword123)";
        String actual = dto.toString();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void equals_shouldReturnTrueWhenComparingWithSameInstance() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        // When/Then
        assertTrue(dto.equals(dto));
    }

    @Test
    public void equals_shouldReturnTrueWhenComparingWithEqualInstance() {
        // Given
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        // When/Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void equals_shouldReturnFalseWhenComparingWithNull() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        // When/Then
        assertFalse(dto.equals(null));
    }

    @Test
    public void equals_shouldReturnFalseWhenComparingWithDifferentClass() {
        // Given
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();

        // When/Then
        assertFalse(dto.equals("password123"));
    }

    @Test
    public void equals_shouldReturnFalseWhenComparingWithDifferentInstance() {
        // Given
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("differentPassword")
                .confirmPassword("differentPassword")
                .build();

        // When/Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullCurrentPassword() {
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword(null)
                .newPassword("newPassword123")
                .confirmPassword("differentPassword")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword(null)
                .newPassword("newPassword123")
                .confirmPassword("differentPassword")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullCurrentPassword() {
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword(null)
                .newPassword("differentPassword")
                .confirmPassword("differentPassword")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullNewPassword() {
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword(null)
                .confirmPassword("differentPassword")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword(null)
                .confirmPassword("differentPassword")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullNewPassword() {
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("differentPassword")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword(null)
                .confirmPassword("differentPassword")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullConfirmPassword() {
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword(null)
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword(null)
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullConfirmPassword() {
        PasswordUpdateDTO dto1 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();
        PasswordUpdateDTO dto2 = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword(null)
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testHashCode_MutationAffectsHashCode() {
        PasswordUpdateDTO dto = PasswordUpdateDTO.builder()
                .currentPassword("password123")
                .newPassword("newPassword123")
                .confirmPassword("newPassword123")
                .build();
        int originalHashCode = dto.hashCode();
        dto.setNewPassword("newnewPassword123");
        int mutatedHashCode = dto.hashCode();
        assertNotEquals(originalHashCode, mutatedHashCode);
    }

}
