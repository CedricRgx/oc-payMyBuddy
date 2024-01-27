package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents an deposit.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@Table(name="deposit")
public class Deposit {

    /**
     * The unique identifier for the deposit.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deposit_id")
    private int depositId;

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
     * The fee of the deposit.
     */
    @Column(name="fee")
    private double fee;

    /**
     * The identifier of the depositor.
     */
    @ManyToOne
    @JoinColumn(name="depositor_id")
    private User depositor;

    /**
     * Default constructor
     */
    public Deposit() {
    }

    /**
     * Constructor with essential fields.
     *
     * @param amount The amount of the transfert
     * @param transactionDate The date of transaction
     * @param fee The fee of the transfert
     * @param depositor The identifier of the depositor
     */
    @Builder
    public Deposit(double amount, LocalDateTime transactionDate, double fee, User depositor){
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.fee = fee;
        this.depositor = depositor;
    }
}
