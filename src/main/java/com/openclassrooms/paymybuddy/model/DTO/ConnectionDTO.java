package com.openclassrooms.paymybuddy.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectionDTO {
    private String firstname;
    private String lastname;
}
