package com.assignment.blueharvest.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Response object for API responses.
 */
@Getter
@Setter
public abstract class ApiResponse {

    /**
     * The status of the API response (e.g., "success" or "error").
     */
    private String status;

    /**
     * A message providing additional information about the response.
     */
    private String message;

    /**
     * The timestamp when the response was generated.
     */
    private LocalDateTime timestamp;

    /**
     * Constructs a new ApiResponse with the specified status and message.
     *
     * @param responseStatus  the status of the response
     * @param responseMsg a msg providing additional info about the response
     */
    public ApiResponse(final String responseStatus, final String responseMsg) {
        this.status = responseStatus;
        this.message = responseMsg;
        this.timestamp = LocalDateTime.now();
    }
}
