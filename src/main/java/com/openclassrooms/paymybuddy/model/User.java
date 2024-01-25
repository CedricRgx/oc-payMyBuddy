package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private UserAccount userAccount;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private AppAccount appAccount;

    @ManyToMany
    @JoinTable(name="assoc_user_friend",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="friend_id"))
    private List<User> friends;

    @OneToMany(mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Transfert> sentTransferts;

    @OneToMany(mappedBy = "recipient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Transfert> receivedTransferts;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Deposit> deposits;

}
