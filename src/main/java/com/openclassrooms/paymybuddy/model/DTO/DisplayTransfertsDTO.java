package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisplayTransfertsDTO {

    private String recipientFirstname;
    private String recipientLastname;
    private String description;
    private int amount;
}
