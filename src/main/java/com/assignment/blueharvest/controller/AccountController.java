package com.assignment.blueharvest.controller;

import com.assignment.blueharvest.dto.AccountCreationRequest;
import com.assignment.blueharvest.dto.CustomerAccInfo;
import com.assignment.blueharvest.model.Account;
import com.assignment.blueharvest.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountCreationRequest request) {
        Account account = accountService.createAccount(request.getCustomerId(), request.getInitialCredit());
        return ResponseEntity.ok(account);
    }

    @GetMapping("/info/{customerId}")
    public ResponseEntity<CustomerAccInfo> getAccountInfo(@PathVariable Long customerId) {
        CustomerAccInfo info = accountService.getCustomerAccountInfo(customerId);
        return ResponseEntity.ok(info);
    }
}

