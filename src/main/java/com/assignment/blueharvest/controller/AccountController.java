package com.assignment.blueharvest.controller;

import com.assignment.blueharvest.dto.AccountDTO;
import com.assignment.blueharvest.dto.CustomerDTO;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.response.AccountResponse;
import com.assignment.blueharvest.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing account-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    /**
     * Service for managing account operations.
     */
    private final AccountService accountService;

    /**
     * Creates a new account for the specified customer.
     *
     * @param request the account creation request containing
     *                customer ID and initial credit.
     * @return a response entity containing the status of the account creation.
     */
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody final AccountDTO request) {
        Account account = accountService.createAccount(request.getCustomerId(),
                request.getInitialCredit());
        if (account == null) {
            AccountResponse response = new AccountResponse("failed",
                    "Error creating account", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        AccountResponse response = new AccountResponse("success",
                "Account created successfully", account);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves account information for a specific customer.
     *
     * @param customerId the ID of the customer.
     * @return a response entity containing the customer's account information.
     */
    @GetMapping("/info/{customerId}")
    public ResponseEntity<CustomerDTO> getAccountInfo(
            @PathVariable final Long customerId) {
        CustomerDTO info = accountService.getCustomerAccountInfo(customerId);
        return ResponseEntity.ok(info);
    }
}
