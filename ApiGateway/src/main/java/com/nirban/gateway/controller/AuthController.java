package com.nirban.gateway.controller;


import com.nirban.gateway.models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client, @AuthenticationPrincipal OidcUser user, Model model){
        logger.info("user email id : {} ", user.getEmail());

        //creating auth response object
        AuthResponse authResponse = new AuthResponse();

        //setting email to authresponse
        authResponse.setUserId(user.getEmail());

        //setting toke to auth response
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());

        logger.info("AToken val : {}",client.getAccessToken().getTokenValue());


//        authResponse.setRefreshToken(Objects.requireNonNull(client.getRefreshToken()).getTokenValue());
//        logger.info("RToken val : {}",client.getRefreshToken().getTokenValue());

        // Check if the refresh token exists before trying to use it
        if (client.getRefreshToken() != null) {
            authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        } else {
            // Handle the case where there is no refresh token
            // You could set it to null, an empty string, or log a warning
            authResponse.setRefreshToken(null);
            logger.warn("No refresh token was issued for user: {}", user.getEmail());
        }



        authResponse.setExpiredAt(Objects.requireNonNull(client.getAccessToken().getExpiresAt()).getEpochSecond());

        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());


        authResponse.setAuthorities(authorities);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
