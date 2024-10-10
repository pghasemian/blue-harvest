package com.assignment.blueharvest.controller;

import com.assignment.blueharvest.model.Customer;
import com.assignment.blueharvest.response.CustomerResponse;
import com.assignment.blueharvest.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for managing customer-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    /**
     * Service for managing customer operations.
     */
    private final CustomerService customerService;

    /**
     * Creates a new customer.
     *
     * @param firstName the first name of the customer.
     * @param surName   the surname of the customer.
     * @return a response entity containing the status of the customer creation.
     */
    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestParam final String firstName,
            @RequestParam final String surName) {

        Customer customer = customerService.saveCustomer(firstName, surName);

        if (customer == null) {
            CustomerResponse response = new CustomerResponse("failed",
                    "Error creating customer", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        CustomerResponse response = new CustomerResponse("success",
                "Account created successfully", customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
