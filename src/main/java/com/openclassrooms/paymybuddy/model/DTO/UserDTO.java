package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for conveying user information within the PayMyBuddy application.
 */
@Data
@Builder
public class UserDTO {

    /**
     * The firstname of the user.
     */
    private String firstname;

    /**
     * The lastname of the user.
     */
    private String lastname;

    /**
     * The address of the user.
     */
    private String address;

    /**
     * The phone number of the user.
     */
    private String phone;

    /**
     * The balance of the app account of the user.
     */
    private String balance;

}
