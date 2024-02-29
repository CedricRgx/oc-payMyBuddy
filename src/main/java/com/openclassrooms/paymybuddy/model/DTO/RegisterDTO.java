package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegisterDTO {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String address;
    private String phone;

}
