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

/**
 * Data Transfer Object (DTO) for user registration in the PayMyBuddy application.
 * This class captures all the necessary information for registering a new user.
 */
@Data
@Builder
public class RegisterDTO {

    /**
     * The email address of the user, which must be unique across the application.
     * It is validated to ensure it is not null, not empty, and adheres to a valid email format.
     */
    @NotNull(message = "{email.notnull}")
    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.email}")
    private String email;

    /**
     * The password for the user's account.
     * It is validated for length to ensure security standards are met.
     */
    @NotNull(message = "{password.notnull}")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String password;

    /**
     * The user's firstname, validated for presence and length.
     */
    @NotNull(message = "{firstname.notnull}")
    @NotEmpty(message = "{firstname.notempty}")
    @Size(min=2, max=250, message = "{firstname.size}")
    private String firstname;

    /**
     * The user's lastname, validated for presence and length.
     */
    @NotNull(message = "{lastname.notnull}")
    @NotEmpty(message = "{lastname.notempty}")
    @Size(min=2, max=250, message = "{lastname.size}")
    private String lastname;

    /**
     * The user's birthdate, validated to ensure it is a valid date.
     * The custom ValidBirthdate annotation uses BirthdateValidator for validation.
     */
    @NotNull(message = "{birthdate.notnull}")
    @ValidBirthdate
    private LocalDate birthdate;

    /**
     * Custom annotation for validating birthdates
     * It uses the BirthdateValidator class to perform the actual validation logic.
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = BirthdateValidator.class)
    public @interface ValidBirthdate {
        /**
         * Default message to be used when this constraint is violated.
         */
        String message() default "{birthdate.valid}";
        /**
         * Used to define the groups the constraint belongs to.
         *
         * @return the groups the constraint belongs to
         */
        Class<?>[] groups() default {};
        /**
         * Can be used by clients of the Bean Validation API to assign custom payload objects to a
         * constraint
         *
         * @return the payload associated with the constraint
         */
        Class<? extends Payload>[] payload() default {};
    }

    /**
     * The user's address, validated for presence and length.
     */
    @NotNull(message = "{address.notnull}")
    @NotEmpty(message = "{address.notempty}")
    @Size(min=2, max=250, message = "{address.size}")
    private String address;

    /**
     * The user's phone number, validated to ensure it contains only numbers.
     */
    @NotNull(message = "{phone.notnull}")
    @NotEmpty(message = "{phone.notempty}")
    @Pattern(regexp="^[0-9]+$", message ="{phone.pattern}")
    private String phone;

}