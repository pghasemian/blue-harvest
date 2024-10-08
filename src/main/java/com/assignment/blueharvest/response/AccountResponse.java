package com.assignment.blueharvest.response;

import com.assignment.blueharvest.model.Account;
import lombok.Getter;
import lombok.Setter;

/**
 * Response object for account-related API responses.
 */
@Setter
@Getter
public class AccountResponse extends ApiResponse {

    /**
     * The account information associated with the response.
     */
    private Account account;

    /**
     * Constructs a new AccountResponse with the specified status,
     * message, and account.
     *
     * @param status  the status of the response
     * @param message a message providing additional info about the response
     * @param accountInfo the account information associated with the response
     */
    public AccountResponse(final String status, final String message,
                           final Account accountInfo) {
        super(status, message);
        this.account = accountInfo;
        // Assigning the constructor parameter to the class field

    }
}
