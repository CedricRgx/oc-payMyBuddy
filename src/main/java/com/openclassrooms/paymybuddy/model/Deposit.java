package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deposit_id")
    private int depositId;

    @Column(name="amount")
    private double amount;

    @Column(name="transaction_date")
    private LocalDateTime transactionDate;

    @Column(name="fee")
    private double fee;

    @ManyToOne
    @JoinColumn(name="depositor_id")
    private User user;
}
