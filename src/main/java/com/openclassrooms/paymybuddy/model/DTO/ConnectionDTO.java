package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) for carrying user connection data.
 * It encapsulates the information of a user connection, including the user's ID, first name, and last name.
 */
@Data
@Builder
public class ConnectionDTO{

    /**
     * The ID of the user.
     */
    private Long userId;

    /**
     * The firstname of the user.
     */
    private String firstname;

    /**
     * The lastname of the user.
     */
    private String lastname;

}
