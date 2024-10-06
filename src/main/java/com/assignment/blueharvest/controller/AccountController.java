package com.assignment.blueharvest.controller;

import com.assignment.blueharvest.dto.AccountDTO;
import com.assignment.blueharvest.dto.CustomerDTO;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.response.ApiResponse;
import com.assignment.blueharvest.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount(@Valid @RequestBody AccountDTO request) {
        Account account = accountService.createAccount(request.getCustomerId(), request.getInitialCredit());
        if (account == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error creating account", false));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("account created successfully ", true));
    }

    @GetMapping("/info/{customerId}")
    public ResponseEntity<CustomerDTO> getAccountInfo(@PathVariable Long customerId) {
        CustomerDTO info = accountService.getCustomerAccountInfo(customerId);
        return ResponseEntity.ok(info);
    }
}

