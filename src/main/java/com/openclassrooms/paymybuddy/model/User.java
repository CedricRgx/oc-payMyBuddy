package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * Represents a user in the PayMyBuddy application.
 * User can have associated account, friends, and financial transactions.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@Table(name="user")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    /**
     * The first name of the user.
     */
    @Column(name="firstname")
    private String firstname;

    /**
     * The last name of the user.
     */
    @Column(name="lastname")
    private String lastname;

    /**
     * The account of the user.
     */
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private UserAccount userAccount;

    /**
     * The app account of the user.
     */
    @OneToOne(mappedBy = "appOwner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private AppAccount appAccount;

    /**
     * The list of friends associated with the user.
     */
    @ManyToMany
    @JoinTable(name="assoc_user_friend",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="friend_id"))
    private List<User> friends;

    /**
     * The list of transfers sent by the user.
     */
    @OneToMany(mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Transfert> sentTransferts;

    /**
     * The list of transfers received by the user.
     */
    @OneToMany(mappedBy = "recipient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Transfert> receivedTransferts;

    /**
     * The list of deposits made by the user.
     */
    @OneToMany(mappedBy = "depositor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Deposit> deposits;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor with essential fields.
     *
     * @param firstname The first name of the user
     * @param lastname  The last name of the user
     */
    @Builder
    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
