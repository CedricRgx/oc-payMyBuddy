package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for user profile information.
 */
@Data
@Builder
public class ProfileDTO {

    /**
     * The email address of the user. It must not be null or empty and must be a valid email format.
     */
    @NotNull(message = "{email.notnull}")
    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.email}")
    private String email;

    /**
     * The firstname of the user. It must not be null or empty and must have a length between 2 and 250 characters.
     */
    @NotNull(message = "{firstname.notnull}")
    @NotEmpty(message = "{firstname.notempty}")
    @Size(min=2, max=250, message = "{firstname.size}")
    private String firstname;

    /**
     * The lastname of the user. It must not be null or empty and must have a length between 2 and 250 characters.
     */
    @NotNull(message = "{lastname.notnull}")
    @NotEmpty(message = "{lastname.notempty}")
    @Size(min=2, max=250, message = "{lastname.size}")
    private String lastname;

    /**
     * The birthdate of the user. It must not be null and must be a valid date before the current date.
     */
    @NotNull(message = "{birthdate.notnull}")
    @RegisterDTO.ValidBirthdate
    private LocalDate birthdate;

    /**
     * The address of the user. It must not be null or empty and must have a length between 2 and 250 characters.
     */
    @NotNull(message = "{address.notnull}")
    @NotEmpty(message = "{address.notempty}")
    @Size(min=2, max=250, message = "{address.size}")
    private String address;

    /**
     * The phone number of the user. It must not be null or empty and must match a numeric pattern.
     */
    @NotNull(message = "{phone.notnull}")
    @NotEmpty(message = "{phone.notempty}")
    @Pattern(regexp="^[0-9]{10}$", message ="{phone.pattern}")
    private String phone;

    /**
     * The user's iban, validated to ensure it contains only 27 characters.
     */
    @NotNull(message = "{iban.notnull}")
    @NotEmpty(message = "{iban.notempty}")
    @Pattern(regexp="^FR\\d{25}$", message ="{iban.pattern}")
    private String iban;

}
