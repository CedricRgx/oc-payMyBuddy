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

    @NotNull(message = "{email.notnull}")
    @NotEmpty(message = "{email.notempty}")
    @Email(message = "{email.email}")
    private String email;

    @NotNull(message = "{firstname.notnull}")
    @NotEmpty(message = "{firstname.notempty}")
    @Size(min=2, max=250, message = "{firstname.size}")
    private String firstname;

    @NotNull(message = "{lastname.notnull}")
    @NotEmpty(message = "{lastname.notempty}")
    @Size(min=2, max=250, message = "{lastname.size}")
    private String lastname;

    @NotNull(message = "{birthdate.notnull}")
    @RegisterDTO.ValidBirthdate
    private LocalDate birthdate;

    @NotNull(message = "{address.notnull}")
    @NotEmpty(message = "{address.notempty}")
    @Size(min=2, max=250, message = "{address.size}")
    private String address;

    @NotNull(message = "{phone.notnull}")
    @NotEmpty(message = "{phone.notempty}")
    @Pattern(regexp="^[0-9]+$", message ="{phone.pattern}")
    private String phone;

}
