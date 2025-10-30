package com.nirban.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        httpSecurity
                // 1. Authorize requests using a lambda
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                // 2. Configure OAuth2 Login with defaults
                .oauth2Login(withDefaults())
                // 3. Configure OAuth2 Resource Server with defaults
                .oauth2ResourceServer(server -> server
                        .jwt(withDefaults())
                );

        return httpSecurity.build();
    }
}
