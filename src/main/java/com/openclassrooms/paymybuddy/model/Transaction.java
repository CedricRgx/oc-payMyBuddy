package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Transaction class is the parent of deposit and Transfert
 */
@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class Transaction {

    /**
     * The unique identifier for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private Long transactionId;

    /**
     * The amount of the transaction.
     */
    @Column(name="amount")
    private double amount;

    /**
     * The description of the transaction.
     */
    @Column(name="description")
    private String description;

    /**
     * The date of transaction.
     */
    @Column(name="transaction_date")
    private LocalDateTime transactionDate;

    /**
     * The fee of the transaction.
     */
    @Column(name="fee")
    private double fee;

    /**
     * The identifier of the transaction.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id", nullable = false)
    private User author;
}
