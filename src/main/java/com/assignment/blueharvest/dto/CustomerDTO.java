package com.assignment.blueharvest.dto;

import com.assignment.blueharvest.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "firstName can not be blank")
    @Size(min = 2, message = "firstName must be at least 2 character")
    private String firstName;

    /**
     * The surname of the customer.
     */
    @NotBlank(message = "surName can not be blank")
    @Size(min = 2, message = "surName must be at least 2 character")
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
