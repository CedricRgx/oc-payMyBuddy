package com.openclassrooms.paymybuddy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class BirthdateValidatorTest {

    private BirthdateValidator validator;

    @BeforeEach
    void setUp() {
        validator = new BirthdateValidator();
    }

    @Test
    void isValid_ShouldReturnFalse_ForAgeMoreThan18Years() {
        // Arrange
        LocalDate birthdateMoreThan18Years = LocalDate.now().minusYears(19).minusDays(1);

        // Act  Assert
        assertTrue(validator.isValid(birthdateMoreThan18Years, null), "Should return true for more than 18 years old.");
    }

    @Test
    void isValid_ShouldReturnFalse_ForAgeLessThan18Years() {
        // Arrange
        LocalDate birthdateLessThan18Years = LocalDate.now().minusYears(17).minusDays(1);

        // Act  Assert
        assertFalse(validator.isValid(birthdateLessThan18Years, null), "Should return false for less than 18 years old.");
    }

    @Test
    void isValid_ShouldReturnFalse_ForAgeEquals18Years() {
        // Arrange
        LocalDate birthdateEquals18Years = LocalDate.now().minusYears(18).minusDays(1);

        // Act  Assert
        assertTrue(validator.isValid(birthdateEquals18Years, null), "Should return true for equals 18 years old.");
    }

    @Test
    void isValid_ShouldReturnFalse_ForNullValue() {
        // Act  Assert
        assertFalse(validator.isValid(null, null), "Should return false for null value.");
    }
}
