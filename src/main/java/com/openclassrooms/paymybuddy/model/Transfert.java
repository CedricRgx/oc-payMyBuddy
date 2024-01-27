package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents an transfert to an user to another.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@Table(name="transfert")
public class Transfert {

    /**
     * The unique identifier for the transfert.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transfert_id")
    private int transfertId;

    /**
     * The amount of the transfert.
     */
    @Column(name="amount")
    private double amount;

    /**
     * The date of transaction.
     */
    @Column(name="transaction_date")
    private LocalDateTime transactionDate;

    /**
     * The fee of the transfert.
     */
    @Column(name="fee")
    private double fee;

    /**
     * The identifier of the sender.
     */
    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    /**
     * The identifier of the recipient.
     */
    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User recipient;

    /**
     * Default constructor
     */
    public Transfert() {
    }

    /**
     * Constructor with essential fields.
     *
     * @param amount The amount of the transfert
     * @param transactionDate The date of transaction
     * @param fee The fee of the transfert
     * @param sender The identifier of the sender
     * @param recipient The identifier of the recipient
     */
    @Builder
    public Transfert(double amount, LocalDateTime transactionDate, double fee, User sender, User recipient){
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.fee = fee;
        this.sender = sender;
        this.recipient = recipient;
    }
}
