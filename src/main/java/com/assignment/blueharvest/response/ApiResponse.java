package com.assignment.blueharvest.response;

import lombok.Getter;

/**
 * Response object for API responses.
 */
@Getter
public class ApiResponse {
    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}
