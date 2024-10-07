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
    private String firstName;
    private String surName;
    private Double balance;
    private List<Transaction> transactions;
}
