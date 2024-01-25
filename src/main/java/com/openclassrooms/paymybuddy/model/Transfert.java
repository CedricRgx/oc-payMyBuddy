package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="transfert")
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transfert_id")
    private int transfertId;

    @Column(name="amount")
    private double amount;

    @Column(name="transaction_date")
    private LocalDateTime transactionDate;

    @Column(name="fee")
    private double fee;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User recipient;
}
