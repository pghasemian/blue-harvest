package com.assignment.blueharvest.dto;

import com.assignment.blueharvest.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object for customer account information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    /**
     * The first name of the customer.
     */
    private String firstName;

    /**
     * The surname of the customer.
     */
    private String surName;

    /**
     * The current balance of the customer's account.
     */
    private Double balance;

    /**
     * A list of transactions associated with the customer's account.
     */
    private List<Transaction> transactions;
}
