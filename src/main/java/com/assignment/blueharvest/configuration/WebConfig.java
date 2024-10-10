package com.assignment.blueharvest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up CORS and resource handling in the app.
 * <p>
 * This class configures CORS mappings and resource handlers for serving static
 * resources. It is designed for extension. When overriding methods, ensure
 * that any additional resource handlers or CORS mappings maintain compatibility
 * with the overall application structure.
 * </p>
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds resource handlers for serving static resources.
     * <p>
     * This method can be overridden to provide additional resource locations
     * or modify existing ones. When doing so, ensure to call this method to
     * retain the existing configurations.
     * </p>
     *
     * @param registry the resource handler registry to configure
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/static");
    }

    /**
     * Adds CORS mappings to the application.
     * <p>
     * This method configures which origins are allowed to access resources
     * in the application. It can be overridden to add more origins or
     * customize allowed methods.
     * </p>
     *
     * @param registry the CORS registry to configure
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // React app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
