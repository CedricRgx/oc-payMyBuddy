package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTransfertDTO {

    @NotNull
    private Long recipientId;

    @NotNull(message = "{description.notnull}")
    @NotEmpty(message = "{description.notempty}")
    @Size(min=2, max=250, message = "{description.size}")
    private String description;

    @NotNull(message = "{amount.notnull}")
    @Min(value = 1, message = "{amount.min}")
    private int amount;
}
