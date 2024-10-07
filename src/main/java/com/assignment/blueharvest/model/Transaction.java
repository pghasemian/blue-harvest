package com.assignment.blueharvest.model;

import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
