package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) for creating new transfers.
 * This DTO is used to capture user input when initiating a transfer.
 */
@Data
@Builder
public class NewTransfertDTO {

    /**
     * The ID of the recipient user. It must not be null.
     */
    @NotNull
    private Long recipientId;

    /**
     * The description of the transfer. It must not be null, not empty, and must have a length between 2 and 250 characters.
     */
    @NotNull(message = "{description.notnull}")
    @NotEmpty(message = "{description.notempty}")
    @Size(min=2, max=250, message = "{description.size}")
    private String description;

    /**
     * The amount to be transferred. It must not be null and must be a positive integer.
     */
    @NotNull(message = "{amount.notnull}")
    @Min(value = 1, message = "{amount.min}")
    private double amount;
}
