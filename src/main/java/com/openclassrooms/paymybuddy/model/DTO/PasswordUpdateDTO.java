package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordUpdateDTO {

    @NotNull(message = "{password.notnull")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String currentPassword;

    @NotNull(message = "{password.notnull")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String newPassword;

    @NotNull(message = "{password.notnull")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String confirmPassword;

}
