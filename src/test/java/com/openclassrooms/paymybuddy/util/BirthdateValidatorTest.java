package com.openclassrooms.paymybuddy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
class BirthdateValidatorTest {

    private BirthdateValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new BirthdateValidator();
    }

    @Test
    public void testIsValid_ShouldReturnFalse_ForAgeMoreThan18Years() {
        // Given
        LocalDate birthdateMoreThan18Years = LocalDate.now().minusYears(19).minusDays(1);

        // When Then
        assertTrue(validator.isValid(birthdateMoreThan18Years, null));
    }

    @Test
    public void testIsValid_ShouldReturnFalse_ForAgeLessThan18Years() {
        // Given
        LocalDate birthdateLessThan18Years = LocalDate.now().minusYears(17).minusDays(1);

        // When Then
        assertFalse(validator.isValid(birthdateLessThan18Years, null));
    }

    @Test
    public void testIsValid_ShouldReturnFalse_ForAgeEquals18Years() {
        // Given
        LocalDate birthdateEquals18Years = LocalDate.now().minusYears(18).minusDays(1);

        // When Then
        assertTrue(validator.isValid(birthdateEquals18Years, null));
    }

    @Test
    public void testIsValid_ShouldReturnFalse_ForNullValue() {
        // When Then
        assertFalse(validator.isValid(null, null));
    }
}
