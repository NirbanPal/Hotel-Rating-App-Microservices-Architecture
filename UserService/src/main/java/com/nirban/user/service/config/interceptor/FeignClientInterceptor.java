package com.nirban.user.service.config.interceptor;

import com.netflix.discovery.converters.Auto;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;


    @Override
    public void apply(RequestTemplate requestTemplate) {


        String token= Objects.requireNonNull(oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())).getAccessToken().getTokenValue();

        requestTemplate.header("Authorization", "Bearer "+token);
    }
}
