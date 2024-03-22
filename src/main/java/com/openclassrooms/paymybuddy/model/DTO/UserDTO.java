package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String firstname;
    private String lastname;
    private String address;
    private String phone;

}
