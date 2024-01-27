package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents an app account of an user in the PayMyBuddy application.
 * App account is associated with one user.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@Table(name="app_account")
public class AppAccount {

    /**
     * The unique identifier for the app account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_account_id")
    private int appAccountId;

    /**
     * The balance of the transfert.
     */
    @Column(name="balance")
    private double balance;

    /**
     * The identifier of the app owner.
     */
    @OneToOne
    @JoinColumn(name="app_owner_id",
            unique = true,
            nullable = false) //each AppAccount is linked to only one User, and it value cannot be nullable
    private User appOwner;

    /**
     * Default constructor
     */
    public AppAccount() {
    }

    /**
     * Constructor with essential fields.
     *
     * @param balance The amount of the transfert
     * @param appOwner The date of transaction
     */
    @Builder
    public AppAccount(double balance, User appOwner){
        this.balance = balance;
        this.appOwner = appOwner;
    }
}
