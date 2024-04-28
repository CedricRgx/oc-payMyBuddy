package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an transfert to an user to another.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name="transfert")
public class Transfert extends Transaction {

    /**
     * The identifier of the recipient.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipient_id", nullable = false)
    private User recipient;

    /**
     * the recipient of the transfert
     * @param recipient
     * @return
     */
    public Transfert setRecipient(User recipient) {
        this.recipient = recipient;
        return this;
    }

}
