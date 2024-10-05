package com.assignment.blueharvest.dto;

import com.assignment.blueharvest.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccInfo {
    private String firstName;
    private String surName;
    private Double balance;
    private List<Transaction> transactions;
}
