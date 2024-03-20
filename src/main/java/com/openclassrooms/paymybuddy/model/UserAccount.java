package com.openclassrooms.paymybuddy.model;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an account of an user in the PayMyBuddy application.
 * Account is associated with one user.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="user_account")
public class UserAccount implements Serializable {

    /**
     * The unique identifier for the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_account_id")
    private Long userAccountId;

    /**
     * The email of the user (associated with account).
     */
    @Column(name="email")
    private String email;

    /**
     * The password of the user (associated with account).
     */
    @Column(name="password")
    private String password;

    /**
     * The date of the last connection on the application.
     */
    @Column(name="last_connection_date")
    private LocalDateTime lastConnectionDate;

    /**
     * The status of the account (true if the account is active).
     */
    @Column(name="is_active")
    private Boolean isActive;

    /**
     * The role of the user
     */
    @Column(name="role")
    private String role;

    /**
     * Constructor with essential fields.
     *
     * @param email The email of the user
     * @param password  The password of the user
     * @param lastConnectionDate The last date of connection on the application
     * @param isActive The status of the account
     * @param role The role of the user
     */
    @Builder
    public UserAccount(String email, String password, LocalDateTime lastConnectionDate, Boolean isActive, String role){
        this.email = email;
        this.password = password;
        this.lastConnectionDate = lastConnectionDate;
        this.isActive = isActive;
        this.role = role;
    }

}
