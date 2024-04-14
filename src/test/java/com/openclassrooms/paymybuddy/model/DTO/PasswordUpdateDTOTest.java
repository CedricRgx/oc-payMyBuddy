package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * unit tests for the PasswordUpdateDTO class.
 */
@Slf4j
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

}
