package com.bezkoder.spring.datajpa.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("http://localhost:4200")  // Allow requests from Angular's default development server
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specified HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Allow sending cookies
                .maxAge(3600); // Cache preflight requests for 3600 seconds (1 hour)
    }
}