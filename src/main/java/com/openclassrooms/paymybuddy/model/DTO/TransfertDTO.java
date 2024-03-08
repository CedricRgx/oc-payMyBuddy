package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransfertDTO {

    private String recipientFirstname;
    private String recipientLastname;
    private String description;
    private double amount;
}
