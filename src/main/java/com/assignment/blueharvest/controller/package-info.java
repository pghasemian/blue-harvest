/**
 * Contains classes for handling customer and account-related operations
 * through RESTful APIs in the Blue Harvest application.
 * <p>
 * This package includes:
 * <ul>
 *   <li>AccountController - Manages account creation
 *   and retrieval operations.</li>
 *   <li>CustomerController - Handles customer creation
 *   and account information retrieval.</li>
 *   <li>DTOs (Data Transfer Objects) for facilitating communication between the
 *       controllers and the client applications,
 *       such as {@code AccountDTO} and {@code CustomerDTO}.</li>
 *   <li>Response classes that encapsulate the results of
 *   operations, such as {@code AccountResponse}
 *       and {@code CustomerResponse}.</li>
 * </ul>
 * <p>
 * Each controller is responsible for exposing endpoints that interact with
 * the {@code AccountService} and {@code CustomerService}
 * to manage the business logic of
 * customer and account operations, while adhering
 * to the microservice architecture.
 * <p>
 * OpenAPI annotations are used to document the endpoints, providing
 * clear API documentation for consumers of the services.
 */
package com.assignment.blueharvest.controller;
