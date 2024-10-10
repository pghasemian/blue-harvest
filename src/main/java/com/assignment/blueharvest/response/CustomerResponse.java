package com.assignment.blueharvest.response;

import com.assignment.blueharvest.model.Customer;
import lombok.Getter;
import lombok.Setter;

/**
 * Response object for customer-related API responses.
 */
@Setter
@Getter
public class CustomerResponse extends ApiResponse {

    /**
     * The customer information associated with the response.
     */
    private Customer customer;

    /**
     * Constructs a new CustomerResponse with the specified status,
     * message, and customer.
     *
     * @param status   the status of the response
     * @param message  a message providing additional info about the response
     * @param customerInfo the customer information associated with the response
     */
    public CustomerResponse(final String status, final String message,
                            final Customer customerInfo) {
        super(status, message);
        this.customer = customerInfo; // Use the renamed parameter here
    }
}
