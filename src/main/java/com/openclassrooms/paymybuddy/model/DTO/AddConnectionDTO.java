package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddConnectionDTO {

    private String firstname;
    private String lastname;
    private boolean isActive;
}
