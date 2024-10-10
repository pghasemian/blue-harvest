/**
 * This package contains the repository interfaces for managing
 * the persistence of entities in the Blue Harvest application.
 * <p>
 * The repositories in this package are responsible for performing
 * CRUD (Create, Read, Update, Delete) operations on the database
 * using Spring Data JPA. These include:
 * <ul>
 *   <li>{@code AccountRepository} - Manages account entities
 *   and allows retrieval of accounts by customer.</li>
 *   <li>{@code CustomerRepository} - Manages customer entities.</li>
 *   <li>{@code TransactionRepository} - Manages transaction
 *   entities and provides methods
 *       to retrieve transactions based on the customer
 *       associated with an account.</li>
 * </ul>
 * <p>
 * By leveraging Spring Data JPA, these repositories abstract away the
 * complexity of database operations, allowing for easy interaction with
 * the underlying database through method names.
 */
package com.assignment.blueharvest.repository;
