package com.assignment.blueharvest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a bank account.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    /**
     * Unique identifier for the bank account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The current balance of the bank account.
     */
    private Double balance;

    /**
     * The customer associated with this bank account.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
