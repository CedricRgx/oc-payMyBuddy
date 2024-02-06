package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an deposit.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="deposit")
public class Deposit extends Transaction {

}
