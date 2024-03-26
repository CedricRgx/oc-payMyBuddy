package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for transfer operations in the PayMyBuddy application.
 * It encapsulates data related to a transfer, including recipient details, transfer description, and amount.
 */
@Data
@Builder
public class TransfertDTO {

    /**
     * The firstname of the transfer recipient.
     */
    private String recipientFirstname;

    /**
     * The lastname of the transfer recipient.
     */
    private String recipientLastname;

    /**
     * A description of the transfer purpose or details.
     */
    private String description;

    /**
     * The amount of money to be transferred.
     */
    private String amount;

}
