package com.assignment.blueharvest.controller;

import com.assignment.blueharvest.dto.CustomCustomerDTO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing customer-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public final class CustomerController {

    /**
     * Service for managing customer operations.
     */
    private final CustomerService customerService;

    /**
     * Creates a new customer.
     *
     * @param request the customer details from the request body
     * @return a ResponseEntity containing the customer creation response
     */
    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody final CustomCustomerDTO request) {
        Customer customer = customerService.saveCustomer(
                request.firstName(), request.surName());
        if (customer == null) {
            CustomerResponse response = new CustomerResponse(
                    "failed", "Error creating customer", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        CustomerResponse response = new CustomerResponse(
                "success", "Customer created successfully", customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
