package com.openclassrooms.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a deposit.
 * This class is annotated as a JPA entity and utilizes Lombok.
 */
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name="deposit")
public class Deposit extends Transaction {

}
