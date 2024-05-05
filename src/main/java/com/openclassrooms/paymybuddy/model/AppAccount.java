package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Represents an app account of an user in the PayMyBuddy application.
 * App account is associated with one user.
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
     * The iban of the transfert.
     */
    @Column(name= "iban1")
    private String iban;

    /**
     * Constructor with essential fields.
     *
     * @param balance The balance of the account
     * @param iban The iban of the bank account of the user
     */
    @Builder
    public AppAccount(double balance, String iban){
        this.balance = balance;
        this.iban = iban;
    }
}
