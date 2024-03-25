package com.openclassrooms.paymybuddy.util;

import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Validates that a given birthdate meets the application's minimum age requirement.
 * This validator is linked to the RegisterDTO.ValidBirthdate annotation to provide custom validation
 * logic that ensures a user's age is at least 18 years at the time of registration.
 */
public class BirthdateValidator implements ConstraintValidator<RegisterDTO.ValidBirthdate, LocalDate> {

    // The application's minimum age requirement.
    private static final int MINIMUM_AGE = 18;

    /**
     * Checks if the provided LocalDate value represents an age that meets or exceeds the application's
     * minimum age requirement of 18 years.
     *
     * @param value The date to validate.
     * @param context Provides contextual data and operation when applying the validator.
     * @return True if the given date is at least 18 years in the past from today's date, indicating
     *         that the user meets the age requirement, False otherwise.
     */
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value==null){
            return false;
        }
        LocalDate today = LocalDate.now();
        return value.isBefore(today.minusYears(MINIMUM_AGE));
    }
}