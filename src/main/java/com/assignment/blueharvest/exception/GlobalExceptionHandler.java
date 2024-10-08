package com.assignment.blueharvest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for managing application-wide exceptions.
 * <p>
 * This class provides centralized exception handling across all
 * controllers using Spring's {@link ControllerAdvice}. It handles
 * validation exceptions and provides meaningful error messages.
 * </p>
 */
@ControllerAdvice
public final class GlobalExceptionHandler {

    /**
     * Handles validation exceptions thrown when method arguments
     * are not valid. It collects error messages from the exception
     * and returns them in a structured format.
     *
     * @param ex the exception that was thrown.
     * @return a response entity containing a map of field errors
     *         with corresponding messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>
    handleValidationException(final MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
