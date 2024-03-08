package com.openclassrooms.paymybuddy.util;

import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthdateValidator implements ConstraintValidator<RegisterDTO.ValidBirthdate, LocalDate> {

    private static final int MINIMUM_AGE = 18;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value==null){
            return false;
        }
        LocalDate today = LocalDate.now();
        return value.isBefore(today.minusYears(MINIMUM_AGE));
    }
}