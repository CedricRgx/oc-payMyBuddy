package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object for handling password updates.
 * This class encapsulates the current password, new password, and confirmation of the new password.
 */
@Data
@Builder
public class PasswordUpdateDTO {

    /**
     * The user's current password. It cannot be null or empty and must be between 4 and 50 characters in length.
     */
    @NotNull(message = "{password.notnull")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String currentPassword;

    /**
     * The user's new password. It cannot be null or empty and must be between 4 and 50 characters in length.
     * This should be different from the current password for successful password change operations.
     */
    @NotNull(message = "{password.notnull")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String newPassword;

    /**
     * Confirmation of the new password. It cannot be null or empty and must be exactly the same as the new password.
     * This field is used to verify that the new password is entered correctly by the user.
     */
    @NotNull(message = "{password.notnull")
    @NotEmpty(message = "{password.notempty}")
    @Size(min=4, max=50, message = "{password.size}")
    private String confirmPassword;

}
