package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an transfert to an user to another.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="transfert")
public class Transfert extends Transaction {

    /**
     * The identifier of the recipient.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipient_id", nullable = false)
    private User recipient;

}
