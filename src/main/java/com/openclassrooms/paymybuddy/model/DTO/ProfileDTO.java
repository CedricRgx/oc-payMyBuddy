package com.openclassrooms.paymybuddy.model.DTO;

import com.openclassrooms.paymybuddy.util.BirthdateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Data
@Builder
public class ProfileDTO {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String address;
    private String phone;

}
