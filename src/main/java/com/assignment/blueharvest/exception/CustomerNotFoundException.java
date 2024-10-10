package com.assignment.blueharvest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a customer is not found in the system.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new CustomerNotFoundException with
     * the specified detail message.
     *
     * @param message the detail message explaining
     *                the reason for the exception.
     */
    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
