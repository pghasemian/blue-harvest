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
 * Entity representing a transaction associated with a bank account.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The amount of money involved in the transaction.
     */
    private Double amount;

    /**
     * The account associated with this transaction.
     */
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
