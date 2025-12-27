package com.eazybytes.eazystore.security;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class EazyStoreSecurityConfig {
   private final PublicPathConfig publicPathConfig;

    @Bean

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       return  http.cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
               .csrf(csrfConfig->csrfConfig.disable())
               .authorizeHttpRequests((requests) ->{
                   publicPathConfig.publicPaths().forEach(path ->
                   requests.requestMatchers(path).permitAll());

           requests.anyRequest().authenticated();
       })

                       .formLogin(withDefaults()).httpBasic(withDefaults()).build();

    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow specific origins (update with your frontend URL)
        config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));

        // Allow all HTTP methods
        config.setAllowedMethods(Collections.singletonList("*"));

        // Allow all headers
        config.setAllowedHeaders(Collections.singletonList("*"));

        // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);

        // Set max age of the CORS pre-flight request
         config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
