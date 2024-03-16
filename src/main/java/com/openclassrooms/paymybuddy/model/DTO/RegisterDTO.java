package com.openclassrooms.paymybuddy.model.DTO;

import com.openclassrooms.paymybuddy.model.UserAccount;
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
import java.time.LocalDateTime;

@Data
@Builder
public class RegisterDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=4, max=50)
    private String password;

    @NotNull
    @Size(min=2, max=250)
    private String firstname;

    @NotNull
    @Size(min=2, max=250)
    private String lastname;

    @NotNull
    @ValidBirthdate
    private LocalDate birthdate;

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = BirthdateValidator.class)
    public @interface ValidBirthdate {
        String message() default "Invalid birthdate (must be at least 18 years old)";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @NotNull
    @Size(min=2, max=250)
    private String address;

    @NotNull
    @Pattern(regexp="^[0-9]+$")
    private String phone;

}
