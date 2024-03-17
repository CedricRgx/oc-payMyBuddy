package com.openclassrooms.paymybuddy.model.DTO;

import com.openclassrooms.paymybuddy.util.BirthdateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Email
    @NotEmpty
    private String email;

    @NotNull
    @Size(min=2, max=250)
    private String firstname;

    @NotNull
    @Size(min=2, max=250)
    private String lastname;

    @NotNull
    @RegisterDTO.ValidBirthdate
    private LocalDate birthdate;

    @NotNull
    @Size(min=2, max=250)
    private String address;

    @NotNull
    @Pattern(regexp="^[0-9]+$")
    private String phone;

}
