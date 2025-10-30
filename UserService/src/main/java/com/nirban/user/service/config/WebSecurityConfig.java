package com.nirban.user.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // this is used to use security in methods level/ methods. hasRole(), hasAuthority() etc can be used in method level.
//prePostEnabled = true is optional cause by default it is true
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        try {
            httpSecurity
                    // 1. Authorize requests using a lambda
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().authenticated()
                    )
                    // 2. Configure OAuth2 Login with defaults
//                    .oauth2Login(withDefaults())
                    // 3. Configure OAuth2 Resource Server with defaults
                    .oauth2ResourceServer(oauth2Server -> oauth2Server
                            .jwt(withDefaults())
                    );
            return httpSecurity.build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
