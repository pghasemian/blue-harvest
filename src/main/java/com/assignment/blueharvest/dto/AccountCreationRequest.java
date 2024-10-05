package com.assignment.blueharvest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationRequest {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Min(value = 0, message = "Initial credit must be 0 or greater")
    private Double initialCredit;
}