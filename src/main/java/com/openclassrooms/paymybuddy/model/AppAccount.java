package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="app_account")
public class AppAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_account_id")
    private int appAccountId;

    @Column(name="balance")
    private double balance;

    @OneToOne
    @JoinColumn(name="app_owner_id",
            unique = true,
            nullable = false) //each AppAccount is linked to only one User, and it value cannot be nullable
    private User user;
}
