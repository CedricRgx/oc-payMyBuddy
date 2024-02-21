package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an app account of an user in the PayMyBuddy application.
 * App account is associated with one user.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="app_account")
public class AppAccount implements Serializable {

    /**
     * The unique identifier for the app account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_account_id")
    private Long appAccountId;

    /**
     * The balance of the transfert.
     */
    @Column(name="balance")
    private double balance;

    /**
     * Constructor with essential fields.
     *
     * @param balance The balance of the account
     * @param appOwner The owner of app account
     */
    @Builder
    public AppAccount(double balance, User appOwner){
        this.balance = balance;
    }
}
