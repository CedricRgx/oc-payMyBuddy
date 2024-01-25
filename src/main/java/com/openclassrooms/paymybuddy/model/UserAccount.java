package com.openclassrooms.paymybuddy.model;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_account_id")
    private int userAccountId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="last_connection_date")
    private LocalDateTime lastConnectionDate;

    @Column(name="is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name="user_id",
            unique = true,
            nullable = false) //each UserAccount is linked to only one User, and it value cannot be nullable
    private User user;

}
