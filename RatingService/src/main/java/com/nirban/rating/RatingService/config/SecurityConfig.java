package com.nirban.rating.RatingService.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security){
        try {
            security
                    // 1. Authorize requests using a lambda
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().authenticated()
                    )
                    // 2. Configure OAuth2 Resource Server with defaults
                    .oauth2ResourceServer(oauth2Server -> oauth2Server
                            .jwt(withDefaults()));

                    return security.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
