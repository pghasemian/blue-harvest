package com.assignment.blueharvest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for account creation requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    /**
     * The unique identifier for the customer.
     * This field is required and cannot be null.
     */
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    /**
     * The initial credit amount for the account.
     * This value must be 0 or greater.
     */
    @Min(value = 0, message = "Initial credit must be 0 or greater")
    private Double initialCredit;
}
