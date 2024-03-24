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

    @NotNull(message = "{email.notnull}")
    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.email}")
    private String email;

    @NotNull(message = "{password.notnull}")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String password;

    @NotNull(message = "{firstname.notnull}")
    @NotEmpty(message = "{firstname.notempty}")
    @Size(min=2, max=250, message = "{firstname.size}")
    private String firstname;

    @NotNull(message = "{lastname.notnull}")
    @NotEmpty(message = "{lastname.notempty}")
    @Size(min=2, max=250, message = "{lastname.size}")
    private String lastname;

    @NotNull(message = "{birthdate.notnull}")
    @ValidBirthdate
    private LocalDate birthdate;

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = BirthdateValidator.class)
    public @interface ValidBirthdate {
        String message() default "{birthdate.valid}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @NotNull(message = "{address.notnull}")
    @NotEmpty(message = "{address.notempty}")
    @Size(min=2, max=250, message = "{address.size}")
    private String address;

    @NotNull(message = "{phone.notnull}")
    @NotEmpty(message = "{phone.notempty}")
    @Pattern(regexp="^[0-9]+$", message ="{phone.pattern}")
    private String phone;

}