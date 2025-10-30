package com.nirban.hotel.HotelService.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private SecurityFilterChain filterChain(HttpSecurity security){
        try {
            security.authorizeHttpRequests(auth-> auth.anyRequest().authenticated()).oauth2ResourceServer(oauth->oauth.jwt(withDefaults()));
            return security.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
