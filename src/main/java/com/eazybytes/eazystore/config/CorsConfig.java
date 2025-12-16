package com.eazybytes.eazystore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow specific origins (update with your frontend URL)
        config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
        
        // Allow all HTTP methods
        config.setAllowedMethods(Arrays.asList("*"));
        
        // Allow all headers
        config.setAllowedHeaders(Collections.singletonList("*"));
        
        // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);
        
        // Set max age of the CORS pre-flight request


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
