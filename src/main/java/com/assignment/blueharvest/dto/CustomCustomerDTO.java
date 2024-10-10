package com.assignment.blueharvest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object (DTO) for representing customer information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CustomCustomerDTO {

    /**
     * The first name of the customer.
     */
    @NotBlank(message = "firstName can not be blank")
    @Size(min = 2, message = "firstName must be at least 2 characters")
    private String firstName;

    /**
     * The surname of the customer.
     */
    @NotBlank(message = "surName can not be blank")
    @Size(min = 2, message = "surName must be at least 2 characters")
    private String surName;

    /**
     * Gets the first name of the customer.
     *
     * @return the first name of the customer
     */
    public String firstName() {
        return firstName;
    }

    /**
     * Gets the surname of the customer.
     *
     * @return the surname of the customer
     */
    public String surName() {
        return surName;
    }
}
