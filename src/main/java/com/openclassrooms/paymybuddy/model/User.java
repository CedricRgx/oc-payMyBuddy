package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Represents a user in the PayMyBuddy application.
 * User can have associated account, friends, and financial transactions.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="user")
public class User implements Serializable {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    /**
     * The firstname of the user.
     */
    @Column(name="firstname")
    private String firstname;

    /**
     * The lastname of the user.
     */
    @Column(name="lastname")
    private String lastname;

    /**
     * The birthdate of the user.
     */
    @Column(name="birthdate")
    private LocalDate birthdate;

    /**
     * The phone of the user.
     */
    @Column(name="phone")
    private String phone;

    /**
     * The address of the user.
     */
    @Column(name="address")
    private String address;

    /**
     * The account of the user.
     */
    @OneToOne
    @JoinColumn(name="user_account_id")
    private UserAccount userAccount;

    /**
     * The app account of the user.
     */
    @OneToOne
    @JoinColumn(name="app_account_id")
    private AppAccount appAccount;

    /**
     * The list of friends associated with the user.
     */
    @ManyToMany
    @JoinTable(name="assoc_user_friend",
            joinColumns = @JoinColumn(name="user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="friend_id", nullable = false))
    private List<User> friends;

    /**
     * The list of transfers sent by the user.
     */
    @OneToMany(mappedBy = "author",
            orphanRemoval = true)
    private List<Deposit> sourceTransac;

    /**
     * The list of transfers received by the user.
     */
    @OneToMany(mappedBy = "recipient",
            orphanRemoval = true)
    private List<Transfert> receivedTransferts;


    /**
     * Constructor with essential fields.
     *
     * @param firstname The first name of the user
     * @param lastname The last name of the user
     * @param birthdate The birthdate of the user
     * @param phone The phone of the user
     * @param address The address of the user
     */
    @Builder
    public User(String firstname, String lastname, LocalDate birthdate, String phone, String address, AppAccount appAccount, UserAccount userAccount){
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.address = address;
        this.appAccount = appAccount;
        this.userAccount = userAccount;
    }

}