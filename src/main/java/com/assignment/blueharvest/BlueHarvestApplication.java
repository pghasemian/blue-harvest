package com.assignment.blueharvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Blue Harvest application.
 * <p>
 * This class is responsible for bootstrapping the Spring Boot application.
 * The {@link SpringBootApplication} annotation enables auto-configuration,
 * component scanning, and allows the application to be run
 * as a standalone application.
 */

@SpringBootApplication
public class BlueHarvestApplication {

    /**
     * The main method that serves as the entry point
     * for the Spring Boot application.
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(BlueHarvestApplication.class, args);
    }
}
